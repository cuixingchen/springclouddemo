package com.cuixingchen.springcloud.config.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by cuipengfei on 17-6-28.
 */
@ConfigurationProperties(prefix = "spring.dataSource")
public class MybatisProperties {

    private String driverClassName;

    private WriteConfig writeConfig;

    private ReadConfig readConfig;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public WriteConfig getWriteConfig() {
        return writeConfig;
    }

    public void setWriteConfig(WriteConfig writeConfig) {
        this.writeConfig = writeConfig;
    }

    public ReadConfig getReadConfig() {
        return readConfig;
    }

    public void setReadConfig(ReadConfig readConfig) {
        this.readConfig = readConfig;
    }

    public static class WriteConfig {

        private String url;
        private String username;
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class ReadConfig {

        private String url;
        private String username;
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
