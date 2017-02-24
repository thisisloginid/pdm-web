package com.neo.pdm.core.loader;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.neo.pdm.core.model.ModuleInfo;
import com.neo.pdm.core.model.ResourceInfo;

public abstract class Loader implements ILoader {
    private static final Logger logger = LoggerFactory.getLogger(Loader.class);
    
    private static final ResourceInfo resources = ResourceInfo.getInstance();
    protected Map<String, Object> in;
    protected Model out;
    
    static{
        AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:pdm/XmlBeans.xml");
        for( String id : context.getBeanDefinitionNames()){
            try{
                resources.getResources().putAll(((ResourceInfo)context.getBean(id)).getResources());
            }catch(ClassCastException cce){
                if( cce.getMessage().indexOf("PropertySourcesPlaceholderConfigurer") != -1 ){
                    logger.debug("common :: resources exception cause : " + cce.getMessage());    
                } else {
                    throw cce;
                }
            }
        }
        context.close();
    }
    
    public Loader(Map<String, Object> in, Model out){
        this.in = in;
        this.out = out;
    }
    
    /**
     * task
     * @return Map<String, Object>
     * @throws Exception 
     */
    private Map<String, Object> getTask(String taskName) throws Exception{
        //task
        Map<String, Map<String, Object>> resourceMaps = resources.getResources();
        Map<String, Object> task = resourceMaps.get(taskName);
        if( task == null ){
            throw new Exception("Task is null. please checke task. : " + taskName);
        }
        
        return task;
    }
    
    /**
     * modules
     * @return List<ModuleModel>
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public List<ModuleInfo> getModules(String screenid, String actionid) throws Exception{
        Map<String, Object> task = this.getTask(screenid+"."+actionid);

        //modules
        List<ModuleInfo> modules = (List<ModuleInfo>)task.get("MODULES");
        if( modules == null ){
            throw new Exception("Modules is null. please checke modules of task. : " + screenid+"."+actionid);
        }
        
        return modules;
    }
    
    /**
     * result page
     * @return Map<String, String>
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getResultPage(String screenid, String actionid) throws Exception{
        Map<String, Object> task = this.getTask(screenid+"."+actionid);
        //resultPage
        Map<String, String> resultPage = (Map<String, String>)task.get("RESULTPAGE");
        if( resultPage == null ){
            throw new Exception("result page is null. please checke result page of task. : " + screenid+"."+actionid);
        }
        return resultPage;
    }
}
