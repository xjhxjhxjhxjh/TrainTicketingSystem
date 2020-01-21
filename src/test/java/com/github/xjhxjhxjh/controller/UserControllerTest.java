package com.github.xjhxjhxjh.controller;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.DispatcherServletCustomizer;
import org.springframework.web.servlet.DispatcherServlet;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class UserControllerTest {

    @Test
    public void index() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.start();
        System.out.println(System.currentTimeMillis());
        System.out.println(new Timestamp(System.currentTimeMillis()).getTime());
        new DispatcherServlet();
    }
}