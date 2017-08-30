package org.powerstone.acegi.util;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.intercept.web.FilterInvocationDefinitionSource;
import org.acegisecurity.vote.AccessDecisionVoter;

public class AuthenticationUtil {
    AccessDecisionVoter accessDecisionVoter;
    FilterInvocationDefinitionSource filterInvocationDefinitionSource;

    public String getCurrentUser() {
        Authentication currentUser = getCurrentUserAuthentication();
        if (currentUser != null) {
            return currentUser.getName();
        }

        return null;
    }

    public boolean isAccessableTo(String accessPattern) {
        Authentication userAuthentication=getCurrentUserAuthentication();
        ConfigAttributeDefinition configAttributeDefinition=filterInvocationDefinitionSource
        .getAttributes(accessPattern);
        System.out.println(">>>>>>>>"+configAttributeDefinition);
        System.out.println(">>>>>>>>"+userAuthentication);
        if(configAttributeDefinition==null){
            return true;
        }
        return AccessDecisionVoter.ACCESS_GRANTED == 
            accessDecisionVoter.vote(userAuthentication, null,configAttributeDefinition );
    }

    public Authentication getCurrentUserAuthentication() {
        Authentication currentUser = null;

        SecurityContext context = SecurityContextHolder.getContext();

        if (null != context) {
            currentUser = context.getAuthentication();
        }
        return currentUser;
    }

    public void setAccessDecisionVoter(AccessDecisionVoter accessDecisionVoter) {
        this.accessDecisionVoter = accessDecisionVoter;
    }

    public void setFilterInvocationDefinitionSource(FilterInvocationDefinitionSource filterInvocationDefinitionSource) {
        this.filterInvocationDefinitionSource = filterInvocationDefinitionSource;
    }
}
