package com.controller;

import com.bean.RestfulResult;
import com.bean.ServiceInfo;
import com.feign.TestFeign;
import com.util.CommUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController // 重要，如果用Controller会404
@RequestMapping(value = "service")
public class ServiceController {
    @Autowired
    private TestFeign testFeign;
    @RequestMapping(value = "hello")
    public void login(HttpServletRequest request, HttpServletResponse response,
                      @RequestBody ServiceInfo serviceInfo) {

        RestfulResult restfulResult = new RestfulResult();

        try {
            restfulResult.setData("Service1:Welcome " + serviceInfo.getName() + "!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        CommUtils.printDataJason(response, restfulResult);
    }

    @RequestMapping(value = "rest")
    public String rest(@RequestBody ServiceInfo serviceInfo){

        return "Service1:Welcome " + serviceInfo.getName() + " !";
    }
    @RequestMapping(value = "feign")
    public String feign(@RequestBody ServiceInfo serviceInfo){

        return testFeign.feigntest(serviceInfo);
    }
}