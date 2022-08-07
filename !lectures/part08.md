# Spring Boot. Security
## Spring Security. Аутентификация и авторизация
**Spring Security** - framework, предоставляющий механизмы построения систем аутентификации и авторизации, а также другие 
возможности обеспечения безопасности для корпоративных приложений, созданных с помощью Spring Framework.

**Аутентификация** - процесс проверки подлинности путём сравнения введенных имени пользователя и пароля с хранящимися в 
базе.

**Авторизация** - процесс проверки прав доступ к тому или иному ресурсу.

![img29.png](assets/img29.png)

По сути Spring Security - прослойка между клиентом и сервлетом. Перед тем как наш запрос попадет в сервлет он проходит 
определённые фильтры. Один из фильтров FilterChainProxy и SpringSecurityFilter, в этих фильтрах происходят 
дополнительные проверки аутентификации и авторизации.

Проверяем:
1. Существует ли пользователь
2. Правильные ли ввел логин/пароль
3. Есть ли у пользователя доступ к запрашиваемой информации

Это делается с помощью SpringSecurityContextHolder, который хранит в себе всю информацию по пользователю и по
SecurityContext

![img30.png](assets/img30.png)

Разделение идет по URL, у каждого могут быть свои фильтры.

## Spring Security. Подключение
Для того чтобы добавить Security в свой проект необходимо добавить зависимость:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
Т.к. никакие фильтры не реализованы то все доступы станут заблокированы по-умолчанию. Необходимо реализовать 
UserDetailService и его метод:
```java
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // загрузка пользователя из хранилища
    }
}
```
Тут мы можем написать реализацию in-memory (создать in-memory хранилище, hashMap), загрузить пользователей из конфига, 
БД.

Далее настраиваем конфигурацию, расширяя класс WebSecurityConfigurerAdapter и его методы:
```java
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth); // настройки действия Security
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http); // тут наш реализованный UserDetailService
    }
}
```

В новой версии Spring Boot ~~WebSecurityConfigurerAdapter~~ объявлен **deprecated**. Для конфигурирования нужно объявить бины:
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
```

Также можно на методы вешать аннотацию `@PreAuthorize("hasAnyAuthority(role1, role2)")`.

## Spring Security. JWT
**JSON Web Token (JWT)** - это открытый стандарт (RFC 7519) для создания токенов доступа, основанный на формате JSON.

Фактически это просто строка символов (закодированная и подписанная определёнными алгоритмами) с некоторой структурой,
содержащая полезные данные пользователя, например ID, имя, уровень доступа и так далее. И эта строчка передается клиентом 
приложению при каждом запросе, когда есть необходимость идентифицировать и понять кто прислал этот запрос.

![img31.png](assets/img31.png)

У нас есть клиент и сервер. Клиент выполняет операцию _login_ передавая свои username и password. Сервер проверяет, есть ли 
этот пользователь в системе, если да то генерирует и возвращает клиенту JWT. При следующих запросах клиент отправляет 
данный токен вместо логина/пароля, и сервер проверяет валидность данного JWT.

JWT состоит из трёх частей, разделенных через точку:
* Заголовок (header). Содержит в себе определённую мета-информацию (по типу подписи, типу JWT...)
* Полезные данные (payload). Содержит в себе информацию о пользователе, идентификатор, жизненный цикл JWT...
* Подпись (signature)

![img32.png](assets/img32.png)

**Заголовок** - первая часть токена. Она служит прежде всего для хранения информации о токене, которая должна рассказать 
о том, как нам прочитать дальнейшие данные, передаваемые JWT. Заголовок предоставлен в виде JSON объекта, закодированного 
в BASE64-URL. 
Например `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9 = {"alg":"HS256","typ":"JWT"}`. 
Для работы с этим алгоритмом у нас 1 ключ, который хранится на сервере. Также существуют и асимметричные алгоритмы 
которые можно использовать в JWT, для работы с ними уже требуются 2 ключа (открытый и закрытый).

**Полезные данные** - вторая часть токена. Опять же - это JSON объект, который для удобства и безопасности передачи 
представляется строкой, закодированной в base64. Например 
`eyJ1c2VyX2lkIjoxLCJleHAiOjE1ODEzNTcwMzl9 = {"user_id":1,"exp":1581357039}`

**Подпись** - третья часть токена. Происходит следующее: наше приложение при прохождении пользователем процедуры 
подтверждения, что он тот за кого себя выдает, генерирует этот самый токен, определяет поля которые нужны, записывает 
туда данные которые характеризуют данного пользователя, а дальше с помощью заранее выбранного алгоритма (который 
отмечается в заголовке в поле alg токена), например HMAC-SHA256, и с помощью своего приватного ключа (или некой 
секретной фразы, которая находится только на серверах приложения) все данные токена подписываются. И затем сформированная 
подпись добавляется, также в формате base64, в конец токена.

`S9Z8uEGGTVVtLggFTizCsMtwOJnRhjaQ2BMUQhcY = HMACSHA256(base64UrlEncode(header) + "." +base64UrlEncode(payload),SECRET_KEY)`

