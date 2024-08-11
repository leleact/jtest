create table if not exists t_order_0
(
    id bigint not null,
    user_id varchar(32) not null,
    order_id varchar(32) not null,
    status char(2),
    create_time datetime not null,
    last_update_time datetime not null,
    primary key (id)
);

create table if not exists t_order_1
(
    id bigint not null,
    user_id varchar(32) not null,
    order_id varchar(32) not null,
    status char(2),
    create_time datetime not null,
    last_update_time datetime not null,
    primary key (id)
);
