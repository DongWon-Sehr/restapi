# 202211-dongwon.sehr-gmail.com

## API-1-working-ver : 
> 아래 4 가지 기능이 동작하는 소스입니다. 
> - 바코드 생성 API
> - 포인트 적립/사용 API
> - 사용내역 조회 API
> Spring Boot (MAVEN), MyBatis, MySQL 환경에서 구현된 소스입니다.

## API-2-incomplete-ver : 
> Entity, Repository, DTO, Service, Controller 구조 및 TDD 방법론으로 구현하고자 했으나 시간이 부족하여 완성하지 못한 소스입니다.

## mySQL DB TABLE 은 아래와 같이 설계했습니다.
> <img width="1000" alt="image" src="https://github.com/kakaopaycoding-server/202211-dongwon.sehr-gmail.com/blob/main/img/kakaopay-membershipAPI-DB-Schema.png?raw=true">
> <br> mysql> CREATE TABLE category (
> <br>     -> categoryId int(11) PRIMARY KEY,
> <br>     -> categoryName varchar(64) NOT NULL,
> <br>     -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
> <br>     -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
> <br>     -> );
> <br> 
> <br> mysql> CREATE TABLE membership (
> <br>     -> membershipId varchar(10),
> <br>     -> categoryId int(11) NOT NULL,
> <br>     -> point int(11) NOT NULL DEFAULT 0,
> <br>     -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
> <br>     -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
> <br>     -> PRIMARY KEY(membershipId, categoryId)
> <br>     -> CONSTRAINT FK_category_membership FOREIGN KEY(categoryId) REFERENCES category(categoryId)
> <br>     -> );
> <br>
> <br> mysql> CREATE TABLE user (
> <br>     -> userId int(9) PRIMARY KEY,
> <br>     -> membershipId varchar(10),
> <br>     -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
> <br>     -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
> <br>     -> CONSTRAINT FK_membership_user FOREIGN KEY(membershipId) REFERENCES membership(membershipId)
> <br>     -> );
> <br> 
> <br> mysql> CREATE TABLE partner (
> <br>     -> partnerId int(11) PRIMARY KEY AUTO_INCREMENT,
> <br>     -> partnerName varchar(64) NOT NULL,
> <br>     -> categoryId int(11),
> <br>     -> cratedAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
> <br>     -> updatedAt datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
> <br>     -> CONSTRAINT FK_category_partner FOREIGN KEY(categoryId) REFERENCES category(categoryId)
> <br>     -> );
> <br> 
> <br> mysql> CREATE TABLE history (
> <br>     -> historyId int(11) AUTO_INCREMENT,
> <br>     -> type varchar(64) NOT NULL,
> <br>     -> membershipId varchar(10) NOT NULL,
> <br>     -> partnerId int(11) NOT NULL,
> <br>     -> point int(10) NOT NULL,
> <br>     -> createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
> <br>     -> approvedAt datetime,
> <br>     -> PRIMARY KEY(historyId),
> <br>     -> CONSTRAINT FK_membership_history FOREIGN KEY(membershipId) REFERENCES membership(membershipId),
> <br>     -> CONSTRAINT FK_partner_history FOREIGN KEY(partnerId) REFERENCES partner(partnerId)
> <br>     -> );
