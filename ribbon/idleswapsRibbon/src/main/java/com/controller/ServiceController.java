package com.controller;

import com.bean.RestfulResult;
import com.bean.ServiceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.util.CommUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController // 重要，如果用Controller会404
public class ServiceController {
    @Autowired
    RestTemplate restTemplate;
    @Value("${server.port}")
    String port;
    // 调用：localhost:6007/consumerServiceRibbon?token=1
    @RequestMapping("/consumerServiceRibbon")
    @HystrixCommand(fallbackMethod = "consumerServiceRibbonFallback")
    public String consumerServiceRibbon(@RequestBody ServiceInfo serviceInfo){
        String result = this.restTemplate.postForObject("http://springbootService/service/rest?token=1", serviceInfo, String.class);
        return result;
    }

    public String consumerServiceRibbonFallback(@RequestBody ServiceInfo serviceInfo){
        return "consumerServiceRibbon异常，端口：" + port + "，Name=" + serviceInfo.getName();
    }

}