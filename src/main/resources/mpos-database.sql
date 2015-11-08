--数据初始化
use oracle;

--初始化用户表
drop table if exists mpos_manger_user;
create table if not exists mpos_manger_user(
    id int(30) primary key AUTO_INCREMENT comment '主健',
    username varchar(20) not null comment '登陆用户',
    email varchar(60) comment '邮箱信息',
    password varchar(50) comment '密码'
)ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
create index index_mpos_manger_user_username on mpos_manger_user(username);

--短信发送信息记录表
drop table if exists mpos_message;
create table if not exists mpos_message(
    id int(30) primary key AUTO_INCREMENT comment '主健',
    cellphone varchar(20) not null comment '手机号',
    sendtime timestamp default current_timestamp comment '发送时间',
    content varchar(200) comment '短信内容',
    status int(2) default 0 comment '短信状态0-初始化;1-发送成功;2-发送失败'
)ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
create index index_mpos_message_cellphone on mpos_message(cellphone);
