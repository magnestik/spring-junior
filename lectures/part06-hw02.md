Добавить сущности в схеме bank:
```
Transaction {
    Integer id;
    Integer sourceBankBookId;
    Integer targerBankBookId;
    BigDecimal amount;
    LocalDateTime initiationDate;
    LocalDateTime completionDate;
    Integer status;
}

Status {
    Integer Id;
    String name;
}

Status = {"processing", "successful", "declined"}
```


Создать с помощью Liquibase в v1.0. Добавить все необходимые связи.

Так же реализовать сервис, который будет выполнять переводы между двумя bank_book (добавить проверку одинаковых валют 
и баланса) или двумя user (тут необходимо добавить проверку на наличие у пользователя счета с данной валютой и баланс).