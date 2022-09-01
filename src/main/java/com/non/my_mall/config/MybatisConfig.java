package com.non.my_mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.non.my_mall.mbg.mapper","com.non.my_mall.dao"})
public class MybatisConfig {
}
