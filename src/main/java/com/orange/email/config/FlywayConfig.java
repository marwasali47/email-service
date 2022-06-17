package com.orange.email.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class FlywayConfig {

    private static final String MIGRATION_SCRIPTS_SCHEMA_TABLE = "migration_mail_scripts_flyway_meta_schema";
    public static final String CLEAN_MIGRATE = "cleanMigrate";
    public static final String MIGRATE = "migrate";

    @Value("${database.db.migration.strategy}")
    private String databaseMigrationStrategy;

    @Value("${flyway.migration.schema.folder}")
    private String migrationSchemaFolder;

    @Bean(name = "flywayMigrationBean")
    public Flyway flywayMigrationBean(DataSource dataSource) {
        Flyway flywayMigrationBean = new Flyway();
        flywayMigrationBean.setLocations(migrationSchemaFolder);
        flywayMigrationBean.setDataSource(dataSource);
        flywayMigrationBean.setTable(MIGRATION_SCRIPTS_SCHEMA_TABLE);
        switch (databaseMigrationStrategy) {
            case CLEAN_MIGRATE:

                flywayMigrationBean.setBaselineVersionAsString("0");
                flywayMigrationBean.clean();
                flywayMigrationBean.baseline();
                flywayMigrationBean.migrate();
                break;
            case MIGRATE:
                try{
                    flywayMigrationBean.setBaselineVersionAsString("0");
                    flywayMigrationBean.baseline();
                }catch (FlywayException e){
                    // this catch for detecting that the migration meta table already exists
                }finally {
                    flywayMigrationBean.migrate();
                }
                break;
            default:
                throw new RuntimeException("Invalid database db.development.migration strategy value " + databaseMigrationStrategy);
        }
        return flywayMigrationBean;
    }

}
