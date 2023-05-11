# HotelAdvisors Test
## Развёртывание проекта

В MySQL выполнить следующие скрипты (в случае необходимости адаптировать под своё окружение):
```SQL
CREATE DATABASE hoteladvisors CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL PRIVILEGES ON hoteladvisors.* TO 'root'@'localhost' WITH GRANT OPTION;
```

Скачать wildfly-24.0.1.Final. Для установки JDBC-драйвера MySQL скопировать содержимое проектной папки modules в
такую же папку на сервере. В файле wildfly-24.0.1.Final\standalone\configuration\standalone.xml в раздел _\<datasources>_
добавить блок:
```XML
<datasource jndi-name="java:jboss/datasources/testAppDS" pool-name="testAppDS" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
    <connection-url>jdbc:mysql://localhost:3306/hoteladvisors</connection-url>
    <driver-class>com.mysql.jdbc.Driver</driver-class>
    <driver>MySQLDriver</driver>
    <security>
        <user-name>root</user-name>
        <password>password</password>
    </security>
    <validation>
        <validate-on-match>false</validate-on-match>
        <background-validation>false</background-validation>
    </validation>
</datasource>
```
Адаптировать под своё окружение. Пример в проектной папке wildfly/standalone/configuration в файле standalone.xml.

В раздел _\<datasources> -> \<drivers>_ добавить:
```XML
<driver name="MySQLDriver" module="com.mysql"/>
```

Осуществить сборку проекта - находясь в папке с проектом выполнить команду:
mvn package

Файл собранного приложения /test-ear/target/test-app.ear скопировать в wildfly-24.0.1.Final\standalone\deployments.

Запустить сервер приложений WildFly, открыть в браузере http://localhost:8080