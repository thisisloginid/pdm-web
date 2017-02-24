package com.neo.pdm.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neo.pdm.core.util.Util;

/**
 * preHandle : Controller 가 수행되기 전에 실행됩니다. 여기서는 이후 Controller를 수행할지 여부를 boolean 으로 return 하게 됩니다.
 * postHandle : Controller 가 수행된후 View 를 호출하기 전 상태입니다.
 * afterCompletion : View 작업까지 완료된 후 호출 됩니다. responseBody 를 이용할 경우 UI 에 이미 값을 전달후 해당 부분이 호출됩니다.
 */

public class SesseionInterceptor extends HandlerInterceptorAdapter{
    private static final Logger logger = LoggerFactory.getLogger(SesseionInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sScreenid = (String)request.getParameter("screenid");
        String sActionid = (String)request.getParameter("actionid");
        
        if( Util.isNullOrEmpty(sScreenid) || Util.isNullOrEmpty(sActionid)){
            response.sendRedirect("/Action.action?screenid=SCMLO0001&actionid=I01");
            return false;
        }
        
        /*
        if( "SCMLO0001I01".equals(sScreenid+sActionid) ){
            return true;
        }
        
        SessionManager sessionManager = new SessionManager(request);
        UserInfo userinfo = sessionManager.getSession();
        if( userinfo == null ){
            response.sendRedirect("/Action.action?screenid=SCMLO0001&actionid=I01");
            return false;
        }*/
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        /*
        UserInfo userinfo = (UserInfo)request.getSession().getAttribute(CoreConstant.KEY_USER_INFO);
        if( userinfo != null ){
            userinfo.setUserMap(null);
        }*/
    }

}
