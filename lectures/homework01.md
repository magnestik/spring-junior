# Домашнее задание 1

Создать интерфейс **ExternalService** и его реализацию **ExternalServiceImpl** с методом:

_ExternalInfo getExternalInfo(Integer id);_

Класс **ExternalInfo**:
```
Integer id;
String info;
```

**ExternalServiceImpl** должен содержать _HashMap<Integer, ExternalInfo>_, наполненный тестовыми данными 
(```[{id:1, info:null}, {id:2, info: “hasInfo”}, {id:3, info: “info”}, { id:4, info: “information”}]```) 
при инициализации бина. Так же HashMap должен очищаться перед закрытием контекста (добавить лог). 
Метод _getExternalInfo_ должен получать _ExternalInfo_ по _id_ из HashMap (добавить лог вызова метода). 
Так же метод должен быть помечен аннотацией **@CacheResult**, 
которая кеширует результат выполнения метода по _id_ (Добавить логи). 
Аннотация должна работать и для других методов.

Создать интерфейс **Process** и его реализацию **ExternalInfoProcess** с методом:

`boolean run(ExternalInfo externalInfo);`

**ExternalInfoProcess** должен вычитывать из конфига переменную _id-not-process=3_. 
Метод _run_ должен: если _externalInfo.id == id-not-process_, то возвращает `false`, иначе `true` (Добавить логи).

Создать класс **Flow**. Он должен инжектировать в себя два бина, описанные выше. 
Бин **ExternalInfoProcess** должен загружаться лениво. **Flow** должен содержать один метод:
`void run(Integer id);`

Метод _run_ должен вызывать `ExternalServiceImpl.getExternalInfo(id)`, 
далее должна быть проверка полученного **ExternalInfo**: если `info != null`, 
то вызов `ExternalInfoProcess.run(ExternalInfo)`, иначе _логгируем_ `ExternalInfoProcess.getClass()`.

Порядок выполнения Flow:

        Flow flow = applicationContext.getBean(Flow.class);
        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);

Так же создать **BeanFactoryPostProcessor**, который будет писать в лог _WARN_ если есть бин, 
который имеет _Scope=Prototype_ и аннотацию **@CacheResult**. 
Проверить работоспособность данного **BeanFactoryPostProcessor**.