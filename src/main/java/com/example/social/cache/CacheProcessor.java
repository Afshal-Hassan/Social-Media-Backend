package com.example.social.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;



@Component
public class CacheProcessor {

    @Autowired
    private CacheManager cacheManager;


    public void refreshAllCache() {

        for(String name : cacheManager.getCacheNames())
        {
            if( cacheManager.getCache(name) != null)
            {
                cacheManager.getCache(name).clear();
            }
            else
            {
                throw new NullPointerException("Cache is null");
            }
        }
    }
}
