drop table if exists demo.wx_user;

create table wx_user
(
    uid  varchar(255)                       not null
        primary key
        unique,
    name varchar(255) default null comment 'uid, 即openID',
    role varchar(64)  default 'NORMAL_USER' not null
)
    comment '微信用户表'
    engine = InnoDB
    default charset = utf8mb4;

