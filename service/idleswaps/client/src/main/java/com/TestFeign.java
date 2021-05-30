package com;

import com.bean.ServiceInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "springbootService2")
public interface TestFeign {

    @RequestMapping(value = "/service/feigntest")
    public String feigntest(@RequestBody ServiceInfo serviceInfo);
}
