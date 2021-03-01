# db_name: mybatis_test

# table info:
CREATE TABLE `user_info` (
                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
                             `name` varchar(20) NOT NULL COMMENT '用户名',
                             `birthday` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生日',
                             `deleteFlag` varchar(128) NOT NULL COMMENT '删除标记',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
