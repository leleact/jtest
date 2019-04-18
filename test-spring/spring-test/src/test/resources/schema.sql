drop TABLE IF EXISTS person;

create table if not exists person
(
    id int auto_increment primary key,
    name varchar(64) null,
    age  int         null
);

insert into person (name, age)
values ('a', 1),
       ('b', 2),
       ('c', 3);