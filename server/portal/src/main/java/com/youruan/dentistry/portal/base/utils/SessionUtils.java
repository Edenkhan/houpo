package com.youruan.dentistry.portal.base.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    private final static String SESSION_NAME = "user.login.id";

    public static Long getAuthenticated() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        return (Long) session.getAttribute(SESSION_NAME);
    }

    public static boolean isAuthenticated() {
        return getAuthenticated() != null;
    }

    public static void login(Long userId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        session.setAttribute(SESSION_NAME, userId);
    }

    public static void logout() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        session.removeAttribute(SESSION_NAME);
    }

}
