
package cz.cvut.fel.stankmic.wa2.data.config;

import cz.cvut.fel.stankmic.wa2.data.entities.EntityScanMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
class DataSourceConfig {

    private static final boolean REINIT_DB_ON_START = false;
    private static final String PSQL_HOST = "localhost";
    private static final int PSQL_PORT = 5432;
    private static final String PSQL_DB = "wa2";
    private static final String PSQL_USER = "postgres";
    private static final String PSQL_PASS = "postgres";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan(EntityScanMarker.class.getPackage().getName());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        emf.setJpaProperties(additionalProperties());
        return emf;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://"+PSQL_HOST+":"+PSQL_PORT+"/"+PSQL_DB);
        dataSource.setUsername(PSQL_USER);
        dataSource.setPassword(PSQL_PASS);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        if (REINIT_DB_ON_START) {
            properties.setProperty("hibernate.hbm2ddl.auto", "create");
        } else {
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
        }
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.default_schema", "public");
        return properties;
    }

}
