SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `lel` ;
CREATE SCHEMA IF NOT EXISTS `lel` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `lel` ;

-- -----------------------------------------------------
-- Table `lel`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`user` ;

CREATE  TABLE IF NOT EXISTS `lel`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `admin` BIT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`project` ;

CREATE  TABLE IF NOT EXISTS `lel`.`project` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`document` ;

CREATE  TABLE IF NOT EXISTS `lel`.`document` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `data` MEDIUMTEXT NULL ,
  `project` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_document_project` (`project` ASC) ,
  CONSTRAINT `fk_document_project1`
    FOREIGN KEY (`project` )
    REFERENCES `lel`.`project` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`category` ;

CREATE  TABLE IF NOT EXISTS `lel`.`category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`classification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`classification` ;

CREATE  TABLE IF NOT EXISTS `lel`.`classification` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`definition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`definition` ;

CREATE  TABLE IF NOT EXISTS `lel`.`definition` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `comments` TEXT NULL ,
  `notion` TEXT NULL ,
  `actual_intention` TEXT NULL ,
  `future_intention` TEXT NULL ,
  `category` INT UNSIGNED NOT NULL ,
  `classification` INT UNSIGNED NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_definition_category` (`category` ASC) ,
  INDEX `fk_definition_classification` (`classification` ASC) ,
  CONSTRAINT `fk_definition_category`
    FOREIGN KEY (`category` )
    REFERENCES `lel`.`category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_definition_classification`
    FOREIGN KEY (`classification` )
    REFERENCES `lel`.`classification` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`symbol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`symbol` ;

CREATE  TABLE IF NOT EXISTS `lel`.`symbol` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `active` BIT NOT NULL DEFAULT 1 ,
  `name` VARCHAR(255) NOT NULL ,
  `document` INT UNSIGNED NOT NULL ,
  `definition` INT UNSIGNED NOT NULL ,
  `project` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_concept_document` (`document` ASC) ,
  INDEX `fk_concept_definition` (`definition` ASC) ,
  UNIQUE INDEX `concept_in_document` (`document` ASC, `name` ASC) ,
  INDEX `fk_concept_project` (`project` ASC) ,
  UNIQUE INDEX `concept_in_project` (`project` ASC, `name` ASC) ,
  CONSTRAINT `fk_concept_document`
    FOREIGN KEY (`document` )
    REFERENCES `lel`.`document` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_definition`
    FOREIGN KEY (`definition` )
    REFERENCES `lel`.`definition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_project1`
    FOREIGN KEY (`project` )
    REFERENCES `lel`.`project` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`event` ;

CREATE  TABLE IF NOT EXISTS `lel`.`event` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`log` ;

CREATE  TABLE IF NOT EXISTS `lel`.`log` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
  `user` INT UNSIGNED NOT NULL ,
  `event` INT UNSIGNED NOT NULL ,
  `symbol` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_user` (`user` ASC) ,
  INDEX `fk_log_event` (`event` ASC) ,
  INDEX `fk_log_symbol` (`symbol` ASC) ,
  CONSTRAINT `fk_log_user`
    FOREIGN KEY (`user` )
    REFERENCES `lel`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_event`
    FOREIGN KEY (`event` )
    REFERENCES `lel`.`event` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_symbol`
    FOREIGN KEY (`symbol` )
    REFERENCES `lel`.`symbol` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`project_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`project_users` ;

CREATE  TABLE IF NOT EXISTS `lel`.`project_users` (
  `project` INT UNSIGNED NOT NULL ,
  `user` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`project`, `user`) ,
  INDEX `fk_project_users_user` (`user` ASC) ,
  INDEX `fk_project_users_project` (`project` ASC) ,
  CONSTRAINT `fk_project_has_user_project1`
    FOREIGN KEY (`project` )
    REFERENCES `lel`.`project` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_has_user_user1`
    FOREIGN KEY (`user` )
    REFERENCES `lel`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
