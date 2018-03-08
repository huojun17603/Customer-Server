package com.example.customer.controller;

import org.omg.CORBA.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CoreController {

    /**
     * 错误统一处理方案
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler
    public @ResponseBody Object exceptionHandler(Exception exception, HttpServletRequest request, HttpServletResponse response){
        /*判断请求类型是不是ajax的*/
        if(isAjaxRequest(request)) {
          /*如果是Ajax请求将错误信息返回到ajax date*/
            return "{msg:'error'}";
        }else{

            return new ModelAndView("redirect:hello");
        }
    }
    /**
     * 判断是否ajax请求.
     * 可以看到Ajax 请求多了个 x-requested-with ，可以利用它，
     * request.getHeader("x-requested-with"); 为 null，则为传统同步请求，为 XMLHttpRequest，则为Ajax 异步请求。
     * 注意：JSONP不会被判断为AJAX
     * @param  request
     * @return 是否ajax请求.
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xr = request.getHeader("X-Requested-With");
        return(xr!=null&&"XMLHttpRequest".equalsIgnoreCase(xr));
    }
}
