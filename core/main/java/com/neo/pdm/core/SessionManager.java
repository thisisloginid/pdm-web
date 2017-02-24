package com.neo.pdm.core;

import javax.servlet.http.HttpSession;

import com.neo.pdm.core.model.DefaultUserInfo;
import com.neo.pdm.core.properties.CoreConstant;

public class SessionManager {
    HttpSession session;
    DefaultUserInfo userInfo;
    
    public SessionManager(HttpSession session){
        this.session = session;
        this.userInfo = (DefaultUserInfo)session.getAttribute(CoreConstant.KEY_USER_INFO);
    }
    
    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
    
    public DefaultUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(DefaultUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
