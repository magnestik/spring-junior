create schema if not exists ad;

create sequence if not exists ad.user_id_seq;
create sequence if not exists ad.address_id_seq;

create table if not exists ad.address
(
    id      integer unique not null default nextval('ad.address_id_seq'),
    country varchar,
    city    varchar,
    street  varchar,
    home    varchar,
    primary key (id)
);

create table if not exists ad.users
(
    id      integer unique not null default nextval('ad.user_id_seq'),
    name    varchar        not null,
    email   varchar,
    address integer,
    primary key (id),
    foreign key (address) references ad.address (id)
);

create schema if not exists dict;

create sequence if not exists dict.currency_id_seq;

create table if not exists dict.currency
(
    id   integer unique not null default nextval('dict.currency_id_seq'),
    name varchar unique not null,
    primary key (id)
);

insert into dict.currency(name)
values ('USD'),
       ('EUR'),
       ('RUB'),
       ('GBR');

create schema if not exists bank;
create sequence if not exists bank.bank_book_id_seq;

create table if not exists bank.bank_book
(
    id       integer unique not null default nextval('bank.bank_book_id_seq'),
    user_id  integer        not null,
    number   varchar        not null,
    amount   numeric        not null,
    currency integer        not null,
    primary key (id),
    foreign key (user_id) references ad.users (id),
    foreign key (currency) references dict.currency (id)
);