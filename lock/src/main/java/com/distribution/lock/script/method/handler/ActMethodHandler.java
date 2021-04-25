package com.distribution.lock.script.method.handler;

import com.distribution.lock.script.method.ActMethod;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class ActMethodHandler {

    @Autowired
    private ApplicationContext applicationContext;

    private static Map<String, ActMethod> serviceMap = Maps.newConcurrentMap();

    @PostConstruct
    public void init() {
        Map<String, ActMethod> beansOfType = applicationContext.getBeansOfType(ActMethod.class);
        for (ActMethod userService : beansOfType.values()) {
            serviceMap.put(userService.getClass().getName(), userService);
        }
    }

    public ActMethod getMethodByKey(String methodKey) {
        return serviceMap.get(methodKey);
    }

}
