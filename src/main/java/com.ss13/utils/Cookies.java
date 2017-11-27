package com.ss13.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Cookies {

    public static final String COOKIE_NAME = "ps_13_forum_UUID";
    public static final int COOKIE_AGE = 2592000;

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        addCookie(request, response, name, null, 0);
    }

    public static String getCookieValue(HttpServletRequest request, String name, Logger log) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName()) && cookie.getValue() != null && cookie.getValue() != "") {
                    log.info("Cookie sent: " + cookie.getName());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}