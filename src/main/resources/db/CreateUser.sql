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
    name    varchar not null,
    email   varchar,
    address integer,
    primary key (id),
    foreign key (address) references ad.address (id)
);