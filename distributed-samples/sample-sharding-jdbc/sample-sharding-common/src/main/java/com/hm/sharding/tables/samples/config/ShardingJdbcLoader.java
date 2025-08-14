package com.hm.sharding.tables.samples.config;

// import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;

import org.springframework.beans.factory.InitializingBean;

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
