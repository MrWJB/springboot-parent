package com.springboot.datasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 在@MapperScan注解中指定Mapper接口所在位置，同时指定SqlSessionFactory的实例名，则该位置下
 * 的Mapper将使用SqlSessionFactory实例。
 */
@Configuration
@MapperScan(value = "com.springboot.datasource.mapper1",sqlSessionFactoryRef = "sqlSessionFactory1")
public class MyBatisConfigOne {

    @Qualifier(value = "dsOne")
    DataSource dsOne;

    /**
     * 提供SqlSessionFactory实例，直接创建出来，同时将DataSource的实例设置给SqlSessionFactory，
     * 这里创建的SqlSessionFactory实例也就是@MapperScan注解中sqlSessionFactoryRef参数指定的实例。
     * @return
     * @throws Exception
     */
    @Bean
    SqlSessionFactory sqlSessionFactoryBean1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dsOne);
        return factoryBean.getObject();
    }

    /**
     * 提供一个SqlSessionTemplate实例。这是一个线程安全类，主要用来管理MyBatis中的SqlSession操作。
     * @return
     * @throws Exception
     */
    @Bean
    SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryBean1());
    }
}
