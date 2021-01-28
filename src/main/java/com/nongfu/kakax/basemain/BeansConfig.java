package com.nongfu.kakax.basemain;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

//@Configuration
public class BeansConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/**/*Dao.xml");
        bean.setMapperLocations(resources);
        bean.setDataSource(dataSource);
        return bean;
    }
}
