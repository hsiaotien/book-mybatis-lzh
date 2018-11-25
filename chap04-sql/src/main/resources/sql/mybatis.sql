
-- 2018/11/25 修改列的默认值
ALTER TABLE `sys_user` MODIFY COLUMN `user_email`
VARCHAR ( 50 ) NULL DEFAULT 'test@mybatis.tk' COMMENT '邮箱';