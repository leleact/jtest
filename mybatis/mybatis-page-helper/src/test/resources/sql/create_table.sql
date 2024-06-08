create table `T1`
(
    `id`          int          NOT NULL auto_increment,
    `name`        varchar(128) NOT NULL comment 'name',
    `age`         int      NOT NULL default 0 comment 'age',
    `birthday`    char(8)      NOT NULL comment 'birthday',
    `create_time` timestamp    NOT NULL default now() comment 'createTime',
    PRIMARY KEY (`id`)
);
