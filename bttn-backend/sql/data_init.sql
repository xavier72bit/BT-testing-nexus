-- ----------------------------
-- 1、配置表
-- ----------------------------
drop table if exists bttn_config;
create table bttn_config(
    id bigint not null auto_increment comment '配置项ID',
    config_key varchar(255) default null comment '配置项',
    config_value varchar(255) default null comment '配置值',

    primary key (id)
) engine=innodb comment = '配置表';

insert into bttn_config(config_key, config_value) values('transaction.generation.rate.ms', '1000');

drop table if exists bttn_wallets;
create table bttn_wallets(
    id bigint not null auto_increment comment '钱包ID',
    address varchar(255) default null comment '钱包公钥地址',
    private_key varchar(255) default null comment '钱包私钥',
    api_address varchar(255) default null comment '钱包API调用地址',

    primary key (id)
) engine=innodb comment = '钱包信息表';

drop table if exists bttn_nodes;
create table bttn_nodes(
    id bigint not null auto_increment comment '节点ID',
    api_address varchar(255) default null comment '节点API地址',

    primary key (id)
) engine=innodb comment = '节点信息表';

drop table if exists bttn_miners;
create table bttn_miners(
    id bigint not null auto_increment comment '矿工ID',
    address varchar(255) default null comment '矿工钱包公钥地址',
    private_key varchar(255) default null comment '矿工钱包私钥',
    api_address varchar(255) default null comment '矿工API调用地址',

    primary key (id)
) engine=innodb comment = '矿工信息表';

