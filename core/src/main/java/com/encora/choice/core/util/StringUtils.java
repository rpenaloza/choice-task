package com.encora.choice.core.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    public String getSearchTerm(String term){
        return "%"+term.trim().replaceAll("//s+","%")+"%";
    }
}
