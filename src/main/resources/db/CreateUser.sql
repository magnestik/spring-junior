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

create sequence if not exists ad.bank_book_id_seq;

create table if not exists ad.bank_book
(
    id       int unique not null default nextval('ad.bank_book_id_seq'),
    user_id  int        not null ,
    number   varchar    not null,
    amount   numeric    not null ,
    currency varchar    not null,
    primary key (id)
)