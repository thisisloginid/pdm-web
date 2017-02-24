package com.neo.pdm.core.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.Model;

import com.neo.pdm.common.model.UserInfo;
import com.neo.pdm.core.model.DefaultUserInfo;
import com.neo.pdm.core.model.ModuleInfo;
import com.neo.pdm.core.properties.CoreConstant;
import com.neo.pdm.core.util.Util;

public class XmlLoader extends Loader{
    
    private static final Logger logger = LoggerFactory.getLogger(XmlLoader.class);
    
    public XmlLoader(Map<String, Object> in, Model out){
        super(in, out);
    }
    
    /**
     * task execute
     */
    public void execute(DefaultUserInfo userinfo, String sScreenid, String sActionid) throws Exception{
        //modules info
        List<ModuleInfo> modules = this.getModules(sScreenid, sActionid);
        
        if( userinfo != null ){
            userinfo.setUserMap(out.asMap());
        }
        
        Map<String, Object> values = new HashMap<String, Object>();
        
        String sClass, sMethod, sKey, sResult = "";
        Class<?> klass;   //task class, argument class
        Class<?>[] argumentTypes;
        Constructor<?> constructor; //constructor of task class 
        Object instance, result=null;  //instance of task
        Object[] arguments;  //instance of task
        Method method;  //method
        boolean isTransaction=false;

        Map<String,String> argumentInfo;
        StringBuffer sbArgument = new StringBuffer();
        Set<?> set;
        Iterator<?> iter;
        for( ModuleInfo module : modules ){
            sClass = Util.nvl(module.getKlass());
            sMethod = Util.nvl(module.getMethod());
            sResult = Util.nvl(module.getResult());
            isTransaction = module.isTransaction();
            argumentInfo = module.getArgument();
            
            if( !Util.isNull(argumentInfo) ){
                set = argumentInfo.keySet();
                
                argumentTypes = new Class[set.size()];  //class type
                arguments = new Object[set.size()]; //instance
                sbArgument = new StringBuffer();    //log string
                iter = set.iterator();
                
                //argument setting
                for( int i=0; iter.hasNext(); i++ ){
                    sKey = (String)iter.next();
                    sbArgument.append(Class.forName(argumentInfo.get(sKey)).getSimpleName()+ " " + sKey + (iter.hasNext()?", ":""));
                    argumentTypes[i] = Class.forName(argumentInfo.get(sKey));
                    
                    if( values.containsKey(sKey) ){
                        arguments[i] = values.get(sKey);
                    } else {
                        if( argumentTypes[i].equals(Map.class) ){
                            arguments[i] = in;
                        } else {
                            instance = argumentTypes[i].newInstance();
                            BeanUtils.copyProperties(instance, in);    //bean mapping
                            arguments[i] = instance;
                        }
                        values.put(sKey, arguments[i]);
                    }
                }
            } else {
                argumentTypes = null;
                arguments = null;
            }
            
            //invok method return type is void.
            if( "".equals(sResult) ){
                logger.debug("common :: invok class info   =============::\n public void " + sClass+"."+sMethod+"("+sbArgument.toString()+");");
            } else {
                logger.debug("common :: invok class info   =============::\n public return (" + sResult + ") " + sClass+"."+sMethod+"("+sbArgument.toString()+");");
            }
            
            if( values.containsKey(sResult) ){
                throw new Exception("Defined " + sResult + " is an already existing return value key.");
            }
            
            klass = Class.forName(sClass);
            constructor = klass.getConstructor(new Class[]{UserInfo.class});
            instance = constructor.newInstance(userinfo);
            method = klass.getDeclaredMethod(sMethod, argumentTypes);

            //invoke mission
            if( Util.isNullOrEmpty(sResult) != void.class.equals(method.getReturnType()) ){
                throw new Exception("Different from xml resource to executed resource. xml defined return value [" +sResult+ "], executed resource return value [" +method.getReturnType()+ "].");
            }
            
            if( !Util.isNullOrEmpty(sResult) ){
                if( !Util.isNull(arguments) ){
                    result = method.invoke(instance, arguments);
                } else {
                    result = method.invoke(instance);
                }
                
                if( !values.containsKey(sResult) ){
                    values.put(sResult, result);
                }
                
                logger.debug("common :: invok class result =============::\n " + result.toString());
                //result.addAttribute(WordConfig.MISSION_RESULT, returnObject);
            } else {
                if( !Util.isNull(arguments) ){
                    method.invoke(instance, arguments);
                } else {
                    method.invoke(instance);
                }
            }
            logger.debug("isTransaction : " + isTransaction);
        }
        out.addAttribute(sResult, result);
    }
    
    /**
     * return page
     */
    public String resultPage(String sScreenid, String sActionid) throws Exception{
        String sResultPage = (String)out.asMap().get(CoreConstant.KEY_RESULT_PAGE);
        if( Util.isNullOrEmpty(sResultPage) ){
            sResultPage = "SUCC01";
        }
        return this.getResultPage(sScreenid, sActionid).get(sResultPage);
    }
    
}
