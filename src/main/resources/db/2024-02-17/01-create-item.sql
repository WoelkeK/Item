--liquibase formatted sql
--changeset kwoelke:1

create table item (
                         id bigint not null auto_increment PRIMARY KEY,
                         index varchar(255),
                         name varchar(255) not null,
                         category varchar(255) not null,
                         description text not null,
                         unit varchar(255) not null,
                         weight decimal(9,2),
                         quantity decimal(9,2) not null ,
                         netPrice decimal(9,2) not null,
                         grossPrice decimal(9,2) not null,
                         vat decimal(9,2) not null ,
                         currency varchar(3) not null,
                         availableStock decimal(9,2),
                         minStockLevel decimal(9,2),
                         maxStockLevel decimal(9,2),
);