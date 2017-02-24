package com.neo.pdm.core.properties;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreConstant extends Constant{
    private static final Logger logger = LoggerFactory.getLogger(CoreConstant.class);
    
    static{
        try {
            setProperties("classpath:/properties/core/constant.properties");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static final String LOADER_TYPE = get("LOADER.type");
    public static final String KEY_USER_INFO = get("KEY.userinfo");
    public static final String KEY_RESULT_PAGE = get("KEY.resultpage");
}
