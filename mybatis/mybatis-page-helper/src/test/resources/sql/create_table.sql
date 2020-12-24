create table if not exists `T1`
(
    `id`          int(11)      NOT NULL auto_increment,
    `name`        varchar(128) NOT NULL comment '姓名',
    `age`         int(11)      NOT NULL default 0 comment '年龄',
    `birthday`    char(8)      NOT NULL comment '出生日',
    `create_time` timestamp    NOT NULL default now() comment '创建时间',
    PRIMARY KEY (`id`)
);
CREATE INDEX t1_idx_001 ON T1 (`birthday`);
