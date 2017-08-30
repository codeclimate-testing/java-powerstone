Welcome to Equinox!

==================
 QuickStart
==================
Run "ant new -Dapp.name=NAME" where NAME is your project's name. Spring is the
default web framework. The default view technology is JSP 2.0, but you can use
Velocity or FreeMarker if you'd rather.  The installations for these templating
engines is in the "extras" folder.

NOTE: If you experience any issues with the installers mentioned below, try 
running "ant fixcrlf" from the base equinox directory before running 
"ant install".

If you'd like to use JSF, Struts, Tapestry or WebWork instead of Spring MVC, 
you can navigate to the the respective directory in the "extras" folder and 
run "ant install". You can also test the installations by running "ant test".

Hibernate is the default persistence framework. If you'd prefer to use iBATIS, 
JDO (JPOX), OJB or Spring JDBC, their installations exist in the "extras" 
folder.  You can install them by navigating to their respective directory
and running "ant install".  You can test each installation using "ant test".

==================
 Build and Test
==================
1. Copy web/WEB-INF/lib/junit.jar into your $ANT_HOME/lib directory.

2. Download and install Tomcat 5.x. Make sure an "admin" user exists in 
   $CATALINA_HOME/conf/tomcat-users.xml with an "admin" password and "manager" 
   role.  These settings are used to manage Tomcat with Ant. If you already 
   have Tomcat 5.x installed, make sure you have a CATALINA_HOME environment 
   variable setup or add a "tomcat.home" property to build.properties.

3. Run "ant test-all" to verify all tests pass.

4. Run "ant deploy", start Tomcat and view the application at 
   http://localhost:8080/equinox
   
NOTE: The HSQLDB is created at the relative path "db/equinox". This means
that a "db" directory will be created from where ever you start a JVM. In order
for the tests and "ant browse" to talk to to the same database, I recommend
you start Tomcat from your project's (or the equinox) directory. For Windows,
use %CATALINA_HOME%\bin\startup.bat.  Otherwise, use 
$CATALINA_HOME/bin/startup.sh.  

If you'd prefer to use a database other than HSQLDB, simply change the settings
in web/WEB-INF/classes/jdbc.properties.  Read more at http://tinyurl.com/6d68t.

==================
 About Equinox
==================
Equinox is a lightweight version of AppFuse (http://raibledesigns.com/appfuse).
I was inspired to create it while writing Spring Live (http://springlive.com)
and looking at the struts-blank and webapp-minimal applications that ship with 
Struts and Spring, respectively. These "starter" apps were not robust enough 
for me, and I wanted something like AppFuse, only simpler.  

Much of the documentation for developing with Equinox can be found in the 
QuickStart Chapter (http://tinyurl.com/6qoqo) from Spring Live.  If you have 
issues downloading this PDF, you might try saving it to your hard drive before 
opening it.

Most of the code in 1.1+ is from Spring Live.  Chapter 11 (due for release
in January) will contain a detailed analysis of the different web frameworks
and their respective code.

Issues?

* Check the FAQ: http://confluence.sourcebeat.com/display/SPL/FAQ
* Mailing List: https://equinox.dev.java.net/servlets/ProjectMailingListList
* Project Home: https://equinox.dev.java.net

==========================
 Features/Changes in 1.3
==========================
- Added missing "validator" property to "userFormController" bean in Spring
  MVC version.
- Added <redirect/> element to success mapping to user list to prevent
  duplicate post problem.
- Moved "ctx" variable declaration from decorators/default.jsp to taglibs.jsp
  so it's available to all JSPs.
- Changed any references to UserDAO in UserWebTest.java instances to use
  UserManager instead (to prevent problems when transactions aren't used).
- Fixed install scripts in extras so they'd work on Windows from the command
  prompt.  Added "fixcrlf" target for users that encounter issues.
- Added installer for Maven in "extras/maven". This can be used to replace
  the Ant build system. 
- Dependent packages upgraded:
    * Display Tag 1.0
    * Hibernate 2.1.8
    * iBATIS 2.0.9b.550
    * JPOX 1.1.0-beta-1
    * Spring 1.1.4
    * Tapestry 3.0.2
  
=========================
 Features/Changes in 1.2
=========================
- Added CruiseControl files and documentation to extras/cruisecontrol. 
- Fixed export issue in userList.jsp for displaytag. Birthday should now
  export w/o any issues.
- Added deployment descriptor for Geronimo with Jetty.
- Added installations for Velocity and FreeMarker with Spring MVC.
- Added installations for iBATIS, JDO, OBJ and Spring JDBC.
- Dependent packages upgraded:
    * Cargo 0.4
    * DisplayTag 1.0 RC2
    * Hibernate 2.1.6
    * Spring 1.1.3
    * StrutsTestCase 2.1.3
- Dependent packages added:
    * FreeMarker 2.3
	* iBATIS 2.0.8
	* JPOX 1.1.0-alpha-4
	* OJB 1.0.1
	* Url Rewrite Filter 1.2
	* Velocity 1.4

KNOWN ISSUES:
=========================
- Hibernate 2.1.7 doesn't work.  For some reason, the HSQL database tables 
  aren't created when the JVM starts up.

=========================
 Features/Changes in 1.1
=========================
- Added CRUD feature on user table, complete with unit tests.
- Replaced Struts with Spring MVC as the default web framework.
- Added JSF, Struts, Tapestry and WebWork as alternative web framework options.
- Added "birthday" field to show how to handle/format dates.
- NOTE: I had to change the code in both of the JavaScript calendars used
  by JSF and Tapestry.  I basically just wrapped the following code snippet
  with a try/catch to workaround a bug in IE.
  
      try {
          do {
              aTag = aTag.offsetParent;
              leftpos += aTag.offsetLeft;
              toppos += aTag.offsetTop;
          } while(aTag.tagName!="BODY");
      } catch (ex) { 
          // ignore 
      }
        
=========================
 Features/Changes in 1.0
=========================
- http://raibledesigns.com/page/rd?anchor=ann_appfuse_light_1_0

