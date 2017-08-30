/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the LGPL license, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author daquanda(liyingquan@gmail.com)
 * @author kevin(diamond_china@msn.com)
 */
package org.powerstone.workflow.job;

import java.util.List;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.powerstone.workflow.service.FlowTaskManager;
import org.powerstone.ca.CADelegater;
import org.powerstone.workflow.model.FlowTask;
import org.powerstone.workflow.model.NewTask;
import org.powerstone.ca.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import java.util.ArrayList;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.io.File;
import org.springframework.mail.javamail.JavaMailSender;

public class SendTaskQuartzJob extends QuartzJobBean {
    private final Log logger = LogFactory.getLog(SendTaskQuartzJob.class);

    private JavaMailSender mailSender;

    private FlowTaskManager flowTaskManager;

    private CADelegater caDelegater;

    private String mailFrom;

    private SessionFactory sessionFactory;

    private String mailText;

    private String mailTitle;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setFlowTaskManager(FlowTaskManager flowTaskManager) {
        this.flowTaskManager = flowTaskManager;
    }

    public void setCaDelegater(CADelegater caDelegater) {
        this.caDelegater = caDelegater;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    // ------------------------------------------------------------------------------
    /**
     * 检查所有新任务，发送通知给用户
     * 
     * @param context
     *            JobExecutionContext
     */
    protected void executeInternal(JobExecutionContext context) {
        logger.info("Send tasks, scheduled by Quartz");
        // 绑定session
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory,
                new SessionHolder(s));

        ArrayList taskEmails = new ArrayList();
        // 获取邮件
        List newTasksNotEmailed = flowTaskManager.findNewTasksNotEmailed();
        for (Iterator it = newTasksNotEmailed.iterator(); it.hasNext();) {
            FlowTask ft = flowTaskManager.getFlowTask(((FlowTask) it.next())
                    .getTaskID().toString());
            TaskEmail te = new TaskEmail();
            te.setTaskID(ft.getTaskID());
            te.setTaskMemo(ft.getFlowNodeBinding().getWorkflowDriver()
                    .getFlowDriverName());

            ArrayList emails = new ArrayList();
            for (Iterator it2 = ft.getNewTasks().iterator(); it2.hasNext();) {
                NewTask nt = (NewTask) it2.next();
                try {
                    User user = caDelegater.findUserByUserID(nt
                            .getTaskCandidateUserID());
                    if (user != null) {
                        emails.add(user.getEmail());
                        logger.info("给用户[" + user.getUserName() + "]email["
                                + user.getEmail() + "]发送新任务[" + ft.getTaskID()
                                + "]通知");
                    }
                } catch (Throwable t) {
                    logger.warn(t);
                    continue;
                }
            }
            te.setEmailList(emails);
            taskEmails.add(te);
        }
        // 取消邦定
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager
                .getResource(sessionFactory);
        s = null;
        if (holder != null) {
            s = holder.getSession();
            s.flush();
            TransactionSynchronizationManager.unbindResource(sessionFactory);
            SessionFactoryUtils.closeSession(s);
        }
        // 发送任务通知,修改任务状态
        for (Iterator it = taskEmails.iterator(); it.hasNext();) {
            TaskEmail te = (TaskEmail) it.next();
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(this.mailFrom);
                // message.setTo("daquan198163@sohu.com");
                Object[] obs = te.getEmailList().toArray();
                if (obs.length > 0) {
                    String[] emails = new String[obs.length];
                    for (int i = 0; i < obs.length; i++) {
                        emails[i] = (String) obs[i];
                    }
                    message.setTo(emails);
                    message.setSubject(this.mailTitle + te.getTaskMemo());
                    message.setText(this.mailText);
                    //实验发送邮件附件
                    this.sendEmail();

                    this.mailSender.send(message);
                }
                flowTaskManager.emailTask(te.getTaskID().toString());
            } catch (Throwable t) {
                logger.warn(t);
                continue;
            }
        }
        logger.info("Next job execution at: " + context.getNextFireTime());
    }

    private void sendEmail() throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        logger.info("发送附件.......");
        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("liyingquan68941836@yahoo.com.cn");
        helper.setFrom("thanku@ss.oo");

        // use the true flag to indicate the text included is HTML
        helper.setText("<html><body><img src=\"cid:123456\"></body></html>",
                true);

        // let's include the infamous windows Sample file (this time copied to
        // c:/)
        FileSystemResource res = new FileSystemResource(new File(
                "c:/200508292128041.jpg"));
        helper.addInline("123456", res);
        helper.addAttachment("TMac.jpg", res);

        // if you would need to include the file as an attachment, use
        // addAttachment() methods on the MimeMessageHelper

        mailSender.send(message);

    }

}