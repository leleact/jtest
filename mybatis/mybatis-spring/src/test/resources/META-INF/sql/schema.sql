create table if not exists T1
(
    f1 varchar(32),
    f2 varchar(32),
    f3 varchar(32),
    primary key (f1)
);

create table if not exists T2
(
    f1 varchar(32),
    f2 varchar(32),
    f3 varchar(32),
    primary key (f1)
);

create table if not exists t_func_json (
    `tid` bigint not null,
    `data` json not null,
    `create_time` datetime not null,
    `last_update_time` datetime not null,
    primary key (tid)
);
