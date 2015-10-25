--数据初始化
use oracle;

--初始化用户表
drop table if exists mpos_user;
create table if not exists mpos_user(
    id int(30) primary key AUTO_INCREMENT comment '主健',
    mobile varchar(20) not null comment '手机号',
    email varchar(60) comment '邮箱信息',
    password varchar(50) comment '密码'
)ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
create index index_mpos_user_mobile on mpos_user(mobile);
