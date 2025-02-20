//package com.example.demo.conf;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class DatasourceConfiguration {
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "primary.datasource")
//    public HikariConfig primaryHikariConfig() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    @Primary
//    public DataSource primaryDataSource(@Qualifier("primaryHikariConfig") HikariConfig hikariConfig) {
//        return new HikariDataSource(hikariConfig);
//    }
//
////    @Bean(name = "primaryTransactionManager")
////    @Primary
////    public SpringTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
////        return new SpringTransactionManager(dataSource, true);
////    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "secondary.datasource")
//    public HikariConfig secondaryHikariConfig() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    public DataSource secondaryDataSource(@Qualifier("secondaryHikariConfig") HikariConfig hikariConfig) {
//        return new HikariDataSource(hikariConfig);
//    }
//
////    @Bean(name = "secondaryTransactionManager")
////    public SpringTransactionManager secondaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
////        return new SpringTransactionManager(dataSource, true);
////    }
//}
