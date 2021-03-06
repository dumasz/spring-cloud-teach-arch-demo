package com.dumas.scta.alibaba.nacos.config.listener;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.util.parse.DefaultYamlConfigParse;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Properties;

/**
 * 监听器配置
 *
 * @author dumas
 * @date 2022/04/28 4:54 PM
 */
@Component
public class LoggingSystemConfigListener {
    /**
     * 日志配置项的前缀
     */
    private static final String LOGGER_TAG = "logging.level.";

    @Resource
    private LoggingSystem loggingSystem;

    @NacosConfigListener(dataId = "${nacos.config.data-id}", type = ConfigType.YAML, timeout = 5000)
    public void onChange(String newLog) throws Exception{
        // <X> 使用 DefaultYamlConfigParse 工具类，解析配置
        Map<String, Object> properties = new DefaultYamlConfigParse().parse(newLog);
        // <Y> 遍历配置集的每个配置项，判断是否是 logging.level 配置项
        for (Object t :
                properties.keySet()) {
            String key = String.valueOf(t);
            if(key.startsWith(LOGGER_TAG)){
                // 获得日志级别
                String strLevel = (String) properties.getOrDefault(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                // 设置日志级别到 LoggingSystem 中
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
            }
        }
    }
}
