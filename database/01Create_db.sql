DROP DATABASE IF EXISTS itplatform;
-- 建立数据库
CREATE DATABASE IF NOT EXISTS itplatform DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

alter database itplatform character set utf8;
set character_set_client=utf8;
set character_set_connection=utf8;
set character_set_database=utf8;
set character_set_results=utf8;
set character_set_server=utf8;

-- 建立用户，授全部权限给betatown用户

GRANT ALL PRIVILEGES ON itplatform.* TO 'itplatform'@'%' IDENTIFIED BY 'itplatform' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON itplatform.* TO 'itplatform'@'localhost' IDENTIFIED BY 'itplatform' WITH GRANT OPTION;
FLUSH PRIVILEGES;
