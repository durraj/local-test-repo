/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.std.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.std.api.response.StatusResponse;
import com.std.common.exception.ApplicationException;
import com.std.common.tracelog.EventLogManager;
import com.std.common.util.Constant;

import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Setting up some stuff using for all API
 *
 */
public abstract class APIUtil {

    //
    // Build setting for Gson class accept NULL value
    //
    Gson gson = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(Constant.API_FORMAT_DATE).create();

    // Mapper object is used to convert object and etc...
    public final static ObjectMapper mapper = new ObjectMapper();

    

    static {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                .setSerializationInclusion(JsonInclude.Include.ALWAYS)
                .setDateFormat(new SimpleDateFormat(Constant.API_FORMAT_DATE));
    }

    /*@Autowired
    AppConfig appConfig;*/

    //
    // Create logger
    //
    public final static EventLogManager logger = EventLogManager.getInstance();

    //
    // Write object as string using mapper
    //
    protected String writeObjectToJson(Object obj) {
        try {

            return mapper.writeValueAsString(obj);

        } catch (JsonProcessingException ex) {
            // Throw our exception
            throw new ApplicationException(ex.getCause());
        }
    }

    protected String writeObjectToJsonRemoveNullProperty(Object obj) throws ApplicationException
    {    
         try {
             // Set setting remove NULL property
             mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
             // map json
             String result=mapper.writeValueAsString(obj);
             // Reset default setting
             mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
             return  result;

        } catch (JsonProcessingException ex) {
            // Throw our exception
            throw new ApplicationException(ex.getCause());
        }        
    }
    public Cookie createCookie(String cookieName, String cookieValue, HttpServletRequest request) {
        Cookie[] allCookies = request.getCookies();
        Cookie cookie = null;
        if(allCookies!=null)
        {
        	for (int i = 0; i < allCookies.length; i++) {
        		String name = allCookies[i].getName();
        		// String value = allCookies[i].getValue();
        		if(cookieName.equals(name))
        		{
        			cookie = allCookies[i];
        			break;
        		}
        	}
        }
        if(cookie == null)
        {
        	cookie = new Cookie(cookieName, "");
        }
        String value=cookie.getValue();
        if(!value.isEmpty())
        {
        	value = value.concat(",").concat(value);
        }else
        {
        	value=value.concat(value);
        }
        cookie.setValue(value);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }
    //
    // Reponse status
    //
    public StatusResponse statusResponse = null;

    // get current user authenticated
//    public User getcurrentUser() {
////        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
////        String authToken = request.getParameter("auth_token");
////        User currentUser = null;//userSessionDao.getUserBySessionID(authToken);
////        if (currentUser == null) {
////            throw new ApplicationException(APIStatus.INVALID_ACCESS_TOKEN);
////        }
////        return currentUser;
//      return null;
//    }
}
