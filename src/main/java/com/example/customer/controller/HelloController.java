package com.example.customer.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


@RestController
public class HelloController extends CoreController{

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("hello")
    public String index(HttpServletRequest request){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        return "HelloWord!";
    }

    @RequestMapping("setName")
    public String setName(HttpServletRequest request, HttpServletResponse response, String name){
        request.getSession().setAttribute("Name",name);
        response.addCookie(new Cookie("Name",name));
        System.out.println(request.getHeader("Cookie"));
        System.out.println(request.getSession().getId());
        return "OK!Name:"+name;
    }

    @RequestMapping("getName")
    public String getName(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getSession().getId());
        System.out.println(request.getHeader("Cookie"));
        Cookie[] cookie = request.getCookies();
        if(null != cookie) {
            for (Cookie cook : cookie) {
                if (cook.getName().equalsIgnoreCase("Name")) { //获取键
                    System.out.println("Name:" + cook.getValue().toString());    //获取值
                }
            }
        }
        return (String)request.getSession().getAttribute("Name");
    }

    @RequestMapping("exp")
    public String exp(HttpServletRequest request, HttpServletResponse response){
        Random random = new Random();
        int n = random.nextInt(2);
        if(n==0) throw  new RuntimeException();
        return "OK:";
    }

}
