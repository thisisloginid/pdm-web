package com.neo.pdm.core.properties;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class Constant{
    protected static ConfigurableEnvironment env;

    protected static void setProperties(String path) throws IOException{
        ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
        MutablePropertySources prop;
        
        env = ctx.getEnvironment();
        prop = env.getPropertySources();
        
        prop.addLast(new ResourcePropertySource(path));
        ctx.close();
    }
    
    protected static String get(String sKey){
        return env.getProperty(sKey);
    }

}
