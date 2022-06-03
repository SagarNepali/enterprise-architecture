package edu.mum.cs544.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("edu.mum.cs544")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cs544?useSSL=false");
        return dataSource;
    }

    @Bean
    public Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.id.new_generator_mappings","false");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.format_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto","create-drop");

        return properties;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("edu.mum.cs544");

        emf.setJpaVendorAdapter(vendorAdapter());
        emf.setJpaProperties(properties());

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
