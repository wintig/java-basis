package com.wintig.rpc.rpc.base;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心
 */
@Service
public class RegisterService {

    // 本地可提供服务的容器
    private static final Map<String, Class> serviceCache = new ConcurrentHashMap<>();

    public void registerService(String serviceName, Class impl) {
        serviceCache.put(serviceName, impl);
    }

    public Class getLocalService(String serviceName) {
        return serviceCache.get(serviceName);
    }

}
