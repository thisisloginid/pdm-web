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

//import com.neo.pdm.core.loader.DBLoader;
import com.neo.pdm.core.loader.ILoader;
import com.neo.pdm.core.loader.XmlLoader;
import com.neo.pdm.core.properties.CoreConstant;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ViewController {
    
    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "/Action.action?screenid=SCMLO0001&actionid=I01";
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

        logger.debug("CoreConstant.LOADER_TYPE : " + CoreConstant.LOADER_TYPE);
        
        logger.debug("common :: call screen and action id : " + screenid+"."+actionid);
        
        //logger.debug("Constant : " + new Constant().get("BEAN.resource"));
        
        ILoader loader=null;
        if( "xml".equals(CoreConstant.LOADER_TYPE) ){
            loader = new XmlLoader(in, out);
        } else if( "yml".equals(CoreConstant.LOADER_TYPE) ){
            //loader = new YamlLoader(in, out);
        } else {
            //loader = new DBLoader(in, out);
        }
        
        if( !"I".equals(actionid.substring(0, 1)) ){
            //loader.execute((DefaultUserInfo)session.getAttribute(CoreConstant.KEY_USER_INFO), screenid, actionid);
        }
        logger.debug("===========================================");
        logger.debug(loader.resultPage(screenid, actionid));
        return loader.resultPage(screenid, actionid);
    }
    
}
