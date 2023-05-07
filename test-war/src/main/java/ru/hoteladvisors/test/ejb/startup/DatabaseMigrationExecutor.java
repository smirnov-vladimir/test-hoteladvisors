package ru.hoteladvisors.test.ejb.startup;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import java.sql.Connection;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class DatabaseMigrationExecutor {

    private static final String CHANGELOG_FILE = "database/db.changelog.xml";

    @Resource(lookup = "java:jboss/datasources/testAppDS")
    private DataSource dataSource;

    /**
     * Исключения не отлавливаем, т.к. не стоит допускать запуска приложения в неконсистентном состоянии - пусть
     * "падает" сразу при старте
     */
    @PostConstruct
    private void executeMigration() throws Exception {
        Connection connection = dataSource.getConnection();
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
        JdbcConnection jdbcConnection = new JdbcConnection(connection);
        Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

        Liquibase liquiBase = new Liquibase(CHANGELOG_FILE, resourceAccessor, db);
        liquiBase.update();
    }
}
