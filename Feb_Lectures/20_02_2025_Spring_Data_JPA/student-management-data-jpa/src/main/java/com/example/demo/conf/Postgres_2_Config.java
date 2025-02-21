//package com.example.demo.conf;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import jakarta.persistence.EntityManagerFactory;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//    basePackages = "com.example.demo.repos2",
//    entityManagerFactoryRef = "schoolEntityManagerFactory",
//    transactionManagerRef = "schoolTransactionManager"
//)
//public class Postgres_2_Config {
//
//	@Bean(name = "schoolDataSourceProperties")
//    @ConfigurationProperties(prefix = "spring.datasource.school")
//    public DataSourceProperties schoolDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "schoolDataSource")
//    @ConfigurationProperties("spring.datasource.school")
//    public DataSource schoolDataSource(@Qualifier("schoolDataSourceProperties") DataSourceProperties properties) {
//        return properties.initializeDataSourceBuilder().build();
//    }
//
//    @Bean(name = "schoolEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean schoolEntityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("schoolDataSource") DataSource dataSource) {
//        Map<String, String> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.put("hibernate.hbm2ddl.auto", "update");
//
//        return builder
//                .dataSource(dataSource)
//                .packages("com.example.demo.entities2") // PostgreSQL Entities
//                .persistenceUnit("school")
//                .properties(properties)
//                .build();
//    }
//
//    @Bean(name = "schoolTransactionManager")
//    public PlatformTransactionManager schoolTransactionManager(
//            @Qualifier("schoolEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}
