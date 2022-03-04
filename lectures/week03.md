# Spring Boot
**Spring Boot** - полезный проект, целью которого является упращение создания приложений на основе Spring. Он позволяет
наиболее простым способом создать web-приложение, требуя от разработчиков минимум усилий по его настройке и написанию 
кода.

Spring Boot обладает большим функционалом, но его наиболее значимыми особенностями являются: управление зависимостями,
автоматическая конфигурация и встроенные контейнеры сервлетов.

## Spring Boot. Управление зависимостями
Spring Boot неявно упаковывает необходимые сторонние зависимости для каждого типа приложения Spring и предоставляет их 
разработчику посредством так называемых spring-boot-starter-пакетов.

**Starter-пакеты** - это набор удобных дескрипторов зависимостей, которые можно включить в свое приложение. Это позволит
получить универсальное решение для всех, связанных со Spring технологий, избавляя программиста от лишнего поиска
примеров кода и загрузки их них требуемых дескрипторов зависимостей.

Например:
+ spring-boot-starter-web
+ spring-boot-starter-data-jpa

## Spring Boot. Автоматическая конфигурация
После выбора подходящего starter-пакета, Spring Boot попытается автоматически настроить Spring-приложение на основе 
добавленных jar-зависимостей.

Например, если добавите spring-boot-starter-web, то Spring Boot автоматически сконфигурирует бины (Dispatcher Servlet
(отвечает за обработку обращений к вашему web-сервису), ResourceHandlers, MessageSource). Если вы используете 
spring-boot-starter-jdbc, то Spring Boot автоматически регистрирует бины DataSource (подключение к БД), 
EntityManagerFactory (менеджер который отвечает за представление наших объектов из БД), 
TransactionManager (менеджер, который отвечает за транзакции) и считывает информацию для подключения к базе данных 
из файла application.properties (вся эта информация определена какими-то переменными, они стандартные, вы указываете 
3 поля (url, user, password) - подключение настроено). Это автоматическая конфигурация помогает благодаря тому,
что не приходится все настраивать вручную. 

Автоматическая конфигурация может быть переопределена с помощью пользовательских настроек.

## Spring Boot. Встроенная поддержка сервера приложений (контейнера сервлетов)
Каждое Spring Boot web-приложение включает встроенный web-сервер. Поддерживаемый "из коробки": Tomcat.

Если вам нужно использовать другой HTTP-сервер, для этого достаточно исключить зависимости по-умолчанию. Spring Boot
предоставляет отдельные starter-пакеты для разных HTTP-серверов. 

Разработчикам теперь не надо беспокоиться о настройке контейнера сервлетов и развертывании приложения на нем. Теперь 
приложение может запускаться само, как исполняемый jar-файл с использованием встроенного сервера. Значительно упрощает
установку и развертывание приложений.

## Spring Boot. Servlet

Сервлет - обработчик запросов на сервер
+ **spring-boot-sterter-tomcat** - дефолтный
+ **spring-boot-sterter-jetty** - альтернатива Tomcat
+ **spring-boot-sterter-undertow** - альтернатива Tomcat
+ **spring-boot-sterter-reactor-netty** - для использования Reactor Netty в качестве встроенного reactive HTTP сервера

Изменить дефолтный сервер:
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<dependensies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
</dependensies>
```
Весь Spring Web реализован поверх Servlet Api который построен вокруг 2 объектов:
+ запросы от клиента, который оборачивается в HTTP-Servlet-request
+ ответ формируется из заполненного нашим кодом который мы выполняем на сервере http-servlet-responce

Имея доступ к этим объектам вы получаете полный контроль над всей http-сессией. **spring-boot-starter-web** позволяет 
обращаться к ним напрямую.

## Spring Boot. Создание приложения
Необходимо настроить Spring Boot в файле pom.xml

Все приложения Spring Boot конфигурируются от spring-boot-starter-parent, поэтому для дальнейшего определения 
зависимостей, необходимо добавить starter-parent следующим образом:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>версия spring boot</version>
</parent>
```
Также добавляем Spring Boot plugin для Maven:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

## Spring Boot. @SpringBootApplication
**@SpringBootApplication** комбинирует в себе 3 аннотации Spring:
+ **@Configuration**
+ **@EnableAutoConfiguration** - конфигурация приложения будет выполняться с помощью Spring Boot
+ **@ComponentScan** - откуда сканировать пакеты на наличие декларированных бинов, по-умолчанию текущий пакет + подпакеты

`SpringApplication.run(SpringJuniorApplication.class, args);` - в методе находится запуск нашего application context, 
обработка жизненного цикла, при завершении приложения - запуск метода close(), удаление всех бинов, выполнение 
destroy-методов и т.д.

## Spring Boot. Основные starter

+ **spring-boot-starter** - core starter, включающий поддержку автоконфигурации, логирование и YAML;
+ **spring-boot-starter-test** - тестирование Spring Boot приложений с библиотеками JUnit, Hamcrest и Mockito;
+ **spring-boot-starter-web** - создание web-приложений (включая RESTful), используя Spring MVC. Использует Tomcat 
как встроенный контейнер по-умолчанию;
+ **spring-boot-starter-date-jpa/jdbc** - стартер для использования БД с Hibernate;
+ **spring-boot-starter-security** - стартер для использования Spring Security;
+ **spring-boot-starter-cloud-*** - набор стартеров для разработки в Cloud. 

Стартеров очень много, можно найти на странице spring boot