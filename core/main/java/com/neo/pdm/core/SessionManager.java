package com.neo.pdm.core;

import javax.servlet.http.HttpServletRequest;

import com.neo.pdm.core.model.DefaultUserInfo;
import com.neo.pdm.core.properties.CoreConstant;

public class SessionManager {
    HttpServletRequest request;
    
    public SessionManager(HttpServletRequest request){
        this.request = request;
    }
    
    public DefaultUserInfo getSession(){
        DefaultUserInfo userinfo = (DefaultUserInfo)this.request.getSession().getAttribute(CoreConstant.KEY_USER_INFO);
        
        this.request.getSession().setAttribute(CoreConstant.KEY_USER_INFO, userinfo);
        
        return userinfo;
    }
}
