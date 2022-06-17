package com.orange.email.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by mohamed on 12/02/17.
 */
//@Configuration
public class DatabaseConfig {

    @Value("${database.driver}")
    private String driver;

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.hibernate.show_sql}")
    private boolean showSql;

    @Value("${database.connection.pool.max.size}")
    private String maxConnectionPoolSize;

    @Bean
    public DataSource dataSource() throws Exception {

        Properties props = new Properties();
        props.setProperty("jdbcUrl", url);
        props.setProperty("dataSource.user", username);
        props.setProperty("dataSource.password", password);
        props.setProperty("maximumPoolSize", maxConnectionPoolSize);

        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        return ds;
    }
}
