# Домашнее задание 3

Реализовать контроллер, отвечающий за работу со счетами пользователя.
```
BankBookDto {
    Integer id;
    Integer userId;
    String number;
    BigDecimal amount;
    String currency;
}
```
Методы:

+ Возвращает список _BankBookDto_ пользователя по _BankBookDto.userId_ `GET /bank-book/by-user-id/{userId}` (_userId_ - 
обязательный, если нет, то возвращать _ErrorDto_ с информацией)

+ Возвращает _BankBookDto_ по _BankBookDto.id_ `GET /bank-book/{bankBookId}` (_bankBookId_ - обязательный, если нет, 
то возвращать _ErrorDto_ с информацией; если в нашем хранилище нет объекта с _bankBookId_, то генерируем исключение и 
обрабатываем его с результатом _ErrorDto.NOT_FOUND_)

+ Возвращает созданный лицевой счет `POST /bank-book` BODY: _BankBookDto_ (если у _BankBookDto.userId_ уже есть лицевой 
счет и номера счетов (_number_) совпадают, то проверяем тип валюты (_currency_), если они разные, то добавляем счет, 
если одинаковые, то генерируем исключение - счет с данной валютой уже имеется в хранилище: _BankBookDto.id_ и 
обрабатываем исключение перед ответом)

+ Возвращает обновленный лицевой счет `PUT /bank-book` BODY: _BankBookDto_ (по запросу обновляем счет в хранилище, но 
обновлять можем только _userId_, _amount_ и _currency_, если пытаемся изменить _number_, то генерируем исключение - 
номер менять нельзя и обрабатываем исключение перед ответом)

+ Возвращает результат удаления счета (HTTP код) `DELETE /bank-book/{bankBookId}`

+ Возвращает результат удаления счетов пользователя (HTTP код) `DELETE /bank-book/by-user-id/{userId}` (Удаляет все счета 
пользователя из хранилища по _BankBookDto.userId_)