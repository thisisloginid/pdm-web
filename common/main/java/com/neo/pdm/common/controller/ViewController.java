package com.neo.pdm.common.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neo.pdm.core.SessionManager;
//import com.neo.pdm.core.loader.DBLoader;
import com.neo.pdm.core.loader.ILoader;
import com.neo.pdm.core.loader.XmlLoader;
import com.neo.pdm.core.model.DefaultUserInfo;
import com.neo.pdm.core.properties.CoreConstant;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ViewController {
    
    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpSession session, @RequestParam Map<String, Object> in, Model out
                                                , @RequestParam(value="screenid") String screenid
                                                , @RequestParam(value="actionid") String actionid
                                                ) throws Exception {
        return this.post(session, in, out, screenid, actionid);
    }

    
    @RequestMapping(value = "/Action.action", method = RequestMethod.GET)
    public String get(HttpSession session, @RequestParam Map<String, Object> in, Model out
                                                , @RequestParam(value="screenid") String screenid
                                                , @RequestParam(value="actionid") String actionid
                                                ) throws Exception {
        return this.post(session, in, out, screenid, actionid);
    }
    
    /**
     * Simply selects the home view to render by returning its name.
     * @throws Exception 
     */
    @RequestMapping(value = "/Action.action", method = RequestMethod.POST)
    public String post(HttpSession session, @RequestParam Map<String, Object> in, Model out
                                                 , @RequestParam(value="screenid") String screenid
                                                 , @RequestParam(value="actionid") String actionid
                                                 ) throws Exception {

        logger.debug("start common :: call screen and action id : " + screenid+"."+actionid);
        
        //logger.debug("Constant : " + new Constant().get("BEAN.resource"));
        
        ILoader loader = new XmlLoader(in, out);
        if( !"I".equals(actionid.substring(0, 1)) ){
            SessionManager sessionManager = new SessionManager(session);
            DefaultUserInfo userinfo = sessionManager.getUserInfo();

            loader.execute(userinfo, screenid, actionid);
        }
        logger.debug("================================================================================================");
        logger.debug("end common :: return page : " + loader.resultPage(screenid, actionid));
        return loader.resultPage(screenid, actionid);
    }
    
}
