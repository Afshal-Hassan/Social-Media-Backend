package com.example.social.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mlEntityManagerFactory",
        transactionManagerRef = "mlTransactionManager", basePackages = {"com.example.social.repo.ml_repos"})
public class MLDatabaseConfig {


    @Bean(name = "mlDataSource")
    @ConfigurationProperties(prefix = "ml.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder , @Qualifier("mlDataSource") DataSource dataSource
    ){
        return builder.dataSource(dataSource).packages("com.example.social.entities.ml_domain").persistenceUnit("ml").build();
    }

    @Bean(name = "mlTransactionManager")
    public PlatformTransactionManager mlTransactionManager(
            @Qualifier("mlEntityManagerFactory") EntityManagerFactory mlEntityManagerFactory) {
        return new JpaTransactionManager(mlEntityManagerFactory);
    }
}
