package com.hm.springcustomize.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * aaa对接相关属性
 * 
 * @author huwenfeng
 */
@Data
@Component
@ConfigurationPropertiesScan({"com.hm"})
@EnableConfigurationProperties(value = XxxProperties.class)
@ConfigurationProperties(prefix = "aaa.link.bbb")
public class XxxProperties {
    private String maxRetry;
    private String endpoint;
    private ApiConfig authApi;
    private ApiConfig inBackApi;
    private ApiConfig outBackApi;

    public XxxProperties() {
    }

    public static class ApiConfig {
        private String url;
        private boolean enable;

        public ApiConfig() {
            this.enable = false;
        }

        public ApiConfig(String url) {
            this.enable = false;
            this.url = url;
        }

        public ApiConfig(String dataId, boolean refresh) {
            this(dataId);
            this.enable = refresh;
        }

        public String getUrl() {
            return this.url;
        }

        public ApiConfig setUrl(String url) {
            this.url = url;
            return this;
        }

        public boolean isEnable() {
            return this.enable;
        }

        public ApiConfig setEnable(boolean enable) {
            this.enable = enable;
            return this;
        }

    }
}
