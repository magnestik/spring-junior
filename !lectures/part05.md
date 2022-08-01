# Spring Boot. Web
## Spring Web. CookieValue
**Cookie** - часть информации, которая хранится на стороне клиента (т.е. в браузере). Клиент отправляет их на сервер с 
каждым запросом, и серверы могут сообщить клиенту, какие файлы cookie хранить.

Они обычно используются для отслеживания активности веб-сайта, настройки пользовательских сеансов и для серверов, чтобы 
распознать пользователей между запросами.

В Spring Web операция с cookie происходит с помощью аннотации `@CookieValue`.

Пример как добавить куки:

`ResponseCookie.from("anyKeyString", "anyValueString")`. У ResponseCookie есть следующие методы:
* **maxAge** - жизненный цикл нашего куки. Используется для ограничения по времени куки. По-умолчанию (когда не указали 
параметр) браузер очищает куки при закрытии сеанса
* **domain** - домен для которого будет применять наш куки (в том числе для поддоменов).
* **path** - путь, определяет в каких областях будет применен наш куки, будет применен по всем url-запросам которые 
соответствуют данному пути. По-умолчанию "/".
* **secure** - Когда храним конфиденциальную информацию и хотим что кука отправлялась только по защищенным соединениям 
https.
* **httpOnly** - клиентские скрипты не получат доступ к куке. Предоставляет защиту куков от вредоносного кода.

Чтоб прочитать куки из хидера: 
```
@GetMapping(value = { "/by-user-id"})
public List<BankBookDto> findAllByUserId(@CookieValue Integer userId) {...}
```

## Spring Web. Header
**Заголовки (Headers)** - специальные параметры, которые несут определенную служебную информацию о соединении по HTTP(S). 
Некоторые заголовки имеют лишь информационный характер для пользователя или для браузера, другие передают определенные
команды, исходя из которых, сервер или клиент будет выполнять какие-то действия. 

Формат: **Параметр:значение**

Для получения HTTP Headers в Spring Boot Web используется аннотация `@RequestHeader`. 

Например получение куков:
```
@GetMapping(value = { "/by-user-id"})
public List<BankBookDto> findAllByUserId(@CookieValue Integer userId, @RequestHeader("Cookie") String cookie) {...}
```
или получение всех заголовков:
```
@GetMapping(value = { "/by-user-id"})
public List<BankBookDto> findAllByUserId(@CookieValue Integer userId, @RequestHeader Map<String, String> headers) {...}
```

## Spring Web. Validation
Для проверка корректности данных в Spring Boot используется Bean Validation. Hibernate Validation считается эталонной 
реализацией Bean Validation. Для проверки данных используются аннотации над полями класса. Это декларативный подход, 
который не загрязняет код.

Подключение:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
Хотим валидировать данные перед тем как они попали в контроллер, т.е. например если мы знаем, что в нашем сообщении 
например `userId != null`, мы не хотим получать в контроллере параметры в которых есть null, фильтровать их раньше и 
возвращать какой-то HTTP-статус и сообщение.

У входящего HTTP-запроса возможно проверить следующие параметры:
* тело запроса
* переменные пути (например, `id` в `/user/{id}`)
* параметры запроса

**@Valid** - показывает объект для валидации.

Исключение _MethodArgumentNotValidException_ выбрасывается, когда объект не проходит проверку. По-умолчанию, Spring 
переведет это исключение в HTTP статус 400.

**@Validated** - устанаваливается на уровень класса и сообщает что в данном классе необходимо выполнять валидацию 
аргументов метода, которые помечены _@Valid_ или другими аннотациями валидации.

В отличии валидации тела запроса, при неудачной проверке параметра вместо метода MethodArgumentNotValidException будет 
выброшен ConstraintViolationException. По-умолчанию последует ответ со статусом HTTP 500 (Internal Server Error), т.к. 
Spring не регистрирует обработчик для этого исключения.

Типовые ограничения:
* **@NotNull** - аннотированный элемент не должен быть null. Принимает любой тип.
* **@Null** - аннотированный элемент должен быть null. Принимает любой тип.
* **@NotBlack** - аннотированный элемент не должен быть null и должен содержать хотя бы один непробельный символ. 
Принимает _CharSequence_.
* **@NotEmpty** - аннотированный элемент не должен быть null или пустым.
* **@Size** - размер аннотированного элемента должен быть между указанными границами, включая сами границы.

Создание кастомных аннотаций:
* Создать аннотацию:
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
public @interface Annotation {
    String message() default "{annotation.invalid}"; //ключ ValidationMessages.properties

    Class<?>[] groups() default {}; //группа проверки

    Class<? extends Payload>[] payload() default { }; //полезная нагрузка
}
```
* Реализовать класс валидатора, который имплементирует `ConstraintValidator<Annotation, FieldType>` и его метод `isValid()`.

Применение валидации по группам:
```java
public class BankBookDto {
    @Null(groups = Created.class) //Created.class - пустой интерфейс
    @NotNull(groups = Update.class)
    private Integer id;
}
```
```
    @Validated(Created.class)
    @PostMapping
    public BankBookDto create(@Valid @RequestBody BankBookDto bankBook) {
        return bankBookService.create(bankBook);
    }

    @Validated(Update.class)
    @PutMapping
    public BankBookDto update(@Valid @RequestBody BankBookDto bankBook) {
        return bankBookService.update(bankBook);
    }
```