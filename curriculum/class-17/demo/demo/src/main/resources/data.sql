DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `password`   varchar(255) DEFAULT NULL,
    `username`   varchar(255) DEFAULT NULL UNIQUE,
    `department` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY          `department` (`department`),
    CONSTRAINT `Department_fk_1` FOREIGN KEY (`department`) REFERENCES `Department` (`id`)
);

CREATE TABLE `Role`
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name`    varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `account_role`
(
    `account_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    KEY       `account_id` (`account_id`),
    KEY       `role_id` (`role_id`),
    CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`),
    CONSTRAINT `account_fk` FOREIGN KEY (`account_id`) REFERENCES `Account` (`id`)
);

INSERT INTO `Department` (`name`)
VALUES ('ASAC');
INSERT INTO `Department` (`name`)
VALUES ('Hospitality');

-- INSERT INTO `Account` (`username`, `password`, `department`)
-- VALUES ('jason', '$2a$10$Z5FccVXkJb3Hy1zofcopmeaNyo3mv3cGk4UBclicYvy0BwhyyEXhq', '1');
INSERT INTO `Account` (`username`, `password`, `department`)
VALUES ('oneal', '$2a$10$Z5FccVXkJb3Hy1zofcopmeaNyo3mv3cGk4UBclicYvy0BwhyyEXhq', '1');

INSERT INTO `Role` (`name`) VALUES ('STUDENT'); -- id -> 1
INSERT INTO `Role` (`name`) VALUES ('ADMIN'); -- id -> 2

-- INSERT INTO `account_role` (`account_id`, `role_id`) VALUES (1, 1); -- account jason has Student Account
INSERT INTO `account_role` (`account_id`, `role_id`) VALUES (1, 2); -- account oneal has Admin Account
