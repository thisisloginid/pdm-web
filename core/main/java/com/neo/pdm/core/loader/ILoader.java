package com.neo.pdm.core.loader;

import com.neo.pdm.core.model.DefaultUserInfo;

public interface ILoader {
    public void execute(DefaultUserInfo ui, String screenid, String actionid) throws Exception;
    
    public String resultPage(String sScreenid, String sActionid) throws Exception;
}
