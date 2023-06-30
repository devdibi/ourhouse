-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema whereismyhome
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema whereismyhome
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `whereismyhome` DEFAULT CHARACTER SET utf8 ;
USE `whereismyhome` ;

-- -----------------------------------------------------
-- Table `whereismyhome`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`user` (
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `gender` INT(11) NOT NULL,
  `age` INT(11) NOT NULL,
  `age_group` INT(11) NOT NULL,
  `join_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `status` INT(11) NULL DEFAULT '1',
  `dwell_area` VARCHAR(45) NULL DEFAULT NULL,
  `favorite_area` VARCHAR(45) NULL DEFAULT NULL,
  `token` LONGTEXT NULL DEFAULT NULL,
  `role` VARCHAR(8) NULL DEFAULT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`board` (
  `board_no` INT(11) NOT NULL AUTO_INCREMENT,
  `title` LONGTEXT NOT NULL,
  `content` LONGTEXT NOT NULL,
  `hit` INT(11) NULL DEFAULT '0',
  `create_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`board_no`),
  INDEX `board_user_fk` (`email` ASC) VISIBLE,
  CONSTRAINT `board_user_fk`
    FOREIGN KEY (`email`)
    REFERENCES `whereismyhome`.`user` (`email`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`bus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`bus` (
  `bus_no` LONGTEXT NULL DEFAULT NULL,
  `stop_name` TEXT NULL DEFAULT NULL,
  `lat` TEXT NULL DEFAULT NULL,
  `lng` TEXT NULL DEFAULT NULL,
  `date` DATETIME NULL DEFAULT NULL,
  `mobile_number` TEXT NULL DEFAULT NULL,
  `city_code` TEXT NULL DEFAULT NULL,
  `city_name` TEXT NULL DEFAULT NULL,
  `management` TEXT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`comment` (
  `comment_no` INT(11) NOT NULL AUTO_INCREMENT,
  `board_no` INT(11) NOT NULL,
  `comment` LONGTEXT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `create_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_no`),
  INDEX `fk_board_comment` (`board_no` ASC) VISIBLE,
  INDEX `fk_user_comment` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_board_comment`
    FOREIGN KEY (`board_no`)
    REFERENCES `whereismyhome`.`board` (`board_no`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_user_comment`
    FOREIGN KEY (`email`)
    REFERENCES `whereismyhome`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`commercial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`commercial` (
  `name` TEXT NULL DEFAULT NULL,
  `category` TEXT NULL DEFAULT NULL,
  `classification` TEXT NULL DEFAULT NULL,
  `category_name` TEXT NULL DEFAULT NULL,
  `dong_code` TEXT NULL DEFAULT NULL,
  `dong_name` TEXT NULL DEFAULT NULL,
  `jibun_address` TEXT NULL DEFAULT NULL,
  `road_address` TEXT NULL DEFAULT NULL,
  `lng` DOUBLE NULL DEFAULT NULL,
  `lat` DOUBLE NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`sido` (
  `sido_code` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sido_code`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`sigungu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`sigungu` (
  `sigungu_code` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `sido_code` INT(11) NOT NULL,
  PRIMARY KEY (`sigungu_code`),
  INDEX `fk_sigungu_sido1_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `fk_sigungu_sido1`
    FOREIGN KEY (`sido_code`)
    REFERENCES `whereismyhome`.`sido` (`sido_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`dong`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`dong` (
  `dong_code` BIGINT(20) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `sido_code` INT(11) NOT NULL,
  `sigungu_code` INT(11) NOT NULL,
  PRIMARY KEY (`dong_code`),
  INDEX `fk_dong_sido1_idx` (`sido_code` ASC) VISIBLE,
  INDEX `fk_dong_sigungu1_idx` (`sigungu_code` ASC) VISIBLE,
  CONSTRAINT `fk_dong_sido1`
    FOREIGN KEY (`sido_code`)
    REFERENCES `whereismyhome`.`sido` (`sido_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dong_sigungu1`
    FOREIGN KEY (`sigungu_code`)
    REFERENCES `whereismyhome`.`sigungu` (`sigungu_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`dongne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`dongne` (
  `dongCode` VARCHAR(10) NOT NULL,
  `sidoName` VARCHAR(30) NULL DEFAULT NULL,
  `gugunName` VARCHAR(30) NULL DEFAULT NULL,
  `dongName` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `whereismyhome`.`house_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`house_info` (
  `apt_code` BIGINT(20) NOT NULL,
  `build_year` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `jibun_address` VARCHAR(200) NULL DEFAULT '정보없음',
  `road_address` VARCHAR(200) NULL DEFAULT '정보없음',
  `lng` DOUBLE NULL DEFAULT NULL,
  `lat` DOUBLE NULL DEFAULT NULL,
  `dong_code` BIGINT(20) NOT NULL,
  `price_avg` DECIMAL(20,0) NULL DEFAULT NULL,
  `area_avg` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`apt_code`),
  INDEX `fk_info_dong` (`dong_code` ASC) VISIBLE,
  CONSTRAINT `fk_info_dong`
    FOREIGN KEY (`dong_code`)
    REFERENCES `whereismyhome`.`dong` (`dong_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`house_deal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`house_deal` (
  `deal_code` BIGINT(20) NOT NULL DEFAULT '0',
  `price` BIGINT(20) NOT NULL,
  `year` INT(11) NOT NULL,
  `month` INT(11) NOT NULL,
  `day` INT(11) NOT NULL,
  `area` DOUBLE NOT NULL,
  `floor` INT(11) NOT NULL,
  `apt_code` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`deal_code`),
  INDEX `fk_house_deal_house_info1_idx` (`apt_code` ASC) VISIBLE,
  CONSTRAINT `fk_deal_info`
    FOREIGN KEY (`apt_code`)
    REFERENCES `whereismyhome`.`house_info` (`apt_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`favorite_deal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`favorite_deal` (
  `email` VARCHAR(45) NOT NULL,
  `deal_code` BIGINT(20) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `apt_code` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`email`, `deal_code`),
  INDEX `fk_user_has_house_deal_house_deal1_idx` (`deal_code` ASC) VISIBLE,
  INDEX `fk_user_has_house_deal_user1_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_house_deal_user1`
    FOREIGN KEY (`email`)
    REFERENCES `whereismyhome`.`user` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_deal_favorite`
    FOREIGN KEY (`deal_code`)
    REFERENCES `whereismyhome`.`house_deal` (`deal_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`favorite_house`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`favorite_house` (
  `email` VARCHAR(45) NOT NULL,
  `apt_code` BIGINT(20) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`email`, `apt_code`),
  INDEX `fk_user_has_house_info_house_info1_idx` (`apt_code` ASC) VISIBLE,
  INDEX `fk_user_has_house_info_user1_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_house_info_user1`
    FOREIGN KEY (`email`)
    REFERENCES `whereismyhome`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_house`
    FOREIGN KEY (`apt_code`)
    REFERENCES `whereismyhome`.`house_info` (`apt_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`hospital`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`hospital` (
  `name` TEXT NULL DEFAULT NULL,
  `lng` DOUBLE NULL DEFAULT NULL,
  `lat` DOUBLE NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`news`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`news` (
  `news_no` INT(11) NOT NULL AUTO_INCREMENT,
  `title` LONGTEXT NOT NULL,
  `context` LONGTEXT NOT NULL,
  `link` LONGTEXT NOT NULL,
  `post_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hit` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`news_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 401
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`notice` (
  `notice_no` INT(11) NOT NULL AUTO_INCREMENT,
  `title` LONGTEXT NOT NULL,
  `content` LONGTEXT NOT NULL,
  `hit` INT(11) NULL DEFAULT '0',
  `create_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`notice_no`),
  INDEX `fk_user_notice_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_user_notice`
    FOREIGN KEY (`email`)
    REFERENCES `whereismyhome`.`user` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`pharmacy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`pharmacy` (
  `name` TEXT NULL DEFAULT NULL,
  `lng` DOUBLE NULL DEFAULT NULL,
  `lat` DOUBLE NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`polygon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`polygon` (
  `dong_code` TEXT NULL DEFAULT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `geo` TEXT NULL DEFAULT NULL,
  `c_lng` DOUBLE NULL DEFAULT NULL,
  `c_lat` DOUBLE NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`static`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`static` (
  `count` BIGINT(21) NOT NULL DEFAULT '0',
  `price` DECIMAL(41,0) NULL DEFAULT NULL,
  `year` INT(11) NOT NULL,
  `month` INT(11) NOT NULL,
  `dong_code` BIGINT(20) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `whereismyhome`.`search`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome`.`search` (
  `email` VARCHAR(45) NOT NULL,
  `dong_code` BIGINT(20) NOT NULL,
  `search_date` DATETIME NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`email`, `dong_code`),
  INDEX `fk_user_has_dong_dong1_idx` (`dong_code` ASC) VISIBLE,
  INDEX `fk_user_has_dong_user1_idx` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_dong_user1`
    FOREIGN KEY (`email`)
    REFERENCES `whereismyhome`.`user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_dong_dong1`
    FOREIGN KEY (`dong_code`)
    REFERENCES `whereismyhome`.`dong` (`dong_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
