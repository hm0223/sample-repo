package com.hm.sharding.jdbc.samples.config;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
// import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.util.ResourceUtils.getFile;

/**
 * ShardingJdbcLoader.
 *
 * @author huwenfeng
 */
// @Configuration
public class ShardingJdbcLoader implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // ShardingSphereDataSource Factory.createDataSource(getFile("classpath:sharding-jdbc.yaml"));
    }
}
