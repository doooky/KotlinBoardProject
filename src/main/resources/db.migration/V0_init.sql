drop table if exists board_comment;
drop table if exists board;
drop table if exists category;
drop table if exists user_authority;
drop table if exists authority;
drop table if exists `user`;

CREATE TABLE `user` (
    `idx` int(11) NOT NULL AUTO_INCREMENT,
    `id` varchar(50) DEFAULT NULL,
    `pw` varchar(100) DEFAULT NULL,
    `name` varchar(50) DEFAULT NULL,
    `activated` tinyint(1) DEFAULT NULL,
    PRIMARY KEY (`idx`),
    UNIQUE KEY `user_UN` (`id`)
)
CREATE TABLE `authority` (
    `idx` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`idx`)
);
CREATE TABLE `user_authority` (
    `user_idx` int(11) NOT NULL,
    `authority_idx` int(11) NOT NULL,
    KEY `user_authority_FK` (`authority_idx`),
    KEY `user_authority_FK_1` (`user_idx`),
    CONSTRAINT `user_authority_FK` FOREIGN KEY (`authority_idx`) REFERENCES `authority` (`idx`),
    CONSTRAINT `user_authority_FK_1` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`)
);
CREATE TABLE `category` (
    `idx` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    `description` text,
    `created_at` datetime DEFAULT NULL,
    `created_user` int(11) DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    PRIMARY KEY (`idx`),
    KEY `category_FK` (`created_user`),
    CONSTRAINT `category_FK` FOREIGN KEY (`created_user`) REFERENCES `user` (`idx`)
);
CREATE TABLE `board` (
    `idx` int(11) NOT NULL AUTO_INCREMENT,
    `title` varchar(500) DEFAULT NULL,
    `content` text,
    `created_at` datetime DEFAULT NULL,
    `created_user` int(11) DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    `category_idx` int(11) DEFAULT NULL,
    PRIMARY KEY (`idx`),
    KEY `board_FK` (`category_idx`),
    KEY `board_FK_1` (`created_user`),
    CONSTRAINT `board_FK` FOREIGN KEY (`category_idx`) REFERENCES `category` (`idx`),
    CONSTRAINT `board_FK_1` FOREIGN KEY (`created_user`) REFERENCES `user` (`idx`)
);
CREATE TABLE `board_comment` (
    `idx` int(11) NOT NULL AUTO_INCREMENT,
    `content` text,
    `board_idx` int(11) DEFAULT NULL,
    `created_at` datetime DEFAULT NULL,
    `created_user` int(11) DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    PRIMARY KEY (`idx`),
    KEY `board_comment_FK` (`board_idx`),
    KEY `board_comment_FK_1` (`created_user`),
    CONSTRAINT `board_comment_FK` FOREIGN KEY (`board_idx`) REFERENCES `board` (`idx`),
    CONSTRAINT `board_comment_FK_1` FOREIGN KEY (`created_user`) REFERENCES `user` (`idx`)
);


