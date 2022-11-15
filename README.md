mysql> CREATE TABLE user (
    -> userId int(9) PRIMARY KEY,
    -> membershipId varchar(10),
    -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    -> );

mysql> CREATE TABLE membership (
    -> membershipId varchar(10),
    -> categoryId int(11) NOT NULL,
    -> point int(11) NOT NULL DEFAULT 0,
    -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    -> PRIMARY KEY(membershipId, categoryId)
    -> );

mysql> CREATE TABLE category (
    -> categoryId int(11) PRIMARY KEY,
    -> categoryName varchar(64) NOT NULL,
    -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    -> );

mysql> CREATE TABLE partner (
    -> partnerId int(11) PRIMARY KEY AUTO_INCREMENT,
    -> partnerName varchar(64) NOT NULL,
    -> categoryId int(11),
    -> cratedAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    -> );

mysql> CREATE TABLE history (
    -> historyId int(11) AUTO_INCREMENT,
    -> type varchar(64) NOT NULL,
    -> membershipId varchar(10) NOT NULL,
    -> partnerId int(11) NOT NULL,
    -> point int(10) NOT NULL,
    -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -> approvedAt datetime,
    -> PRIMARY KEY(historyId),
    -> CONSTRAINT FK_membership_history FOREIGN KEY(membershipId) REFERENCES membership(membershipId),
    -> CONSTRAINT FK_partner_history FOREIGN KEY(partnerId) REFERENCES partner(partnerId)
    -> );