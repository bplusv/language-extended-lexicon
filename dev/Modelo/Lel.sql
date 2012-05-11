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
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NOT NULL ,
  `password` LONGTEXT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_details` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_details` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `comments` LONGTEXT NULL ,
  `notion` LONGTEXT NULL ,
  `actual_intention` LONGTEXT NULL ,
  `future_intention` LONGTEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`document` ;

CREATE  TABLE IF NOT EXISTS `lel`.`document` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept_class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_class` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_class` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_category` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_category` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `active` TINYINT(1) NULL DEFAULT 1 ,
  `name` LONGTEXT NOT NULL ,
  `concept_details_id` INT NOT NULL ,
  `document_id` INT NOT NULL ,
  `concept_class_id` INT NOT NULL ,
  `concept_category_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_concept_concept_details` (`concept_details_id` ASC) ,
  INDEX `fk_concept_document` (`document_id` ASC) ,
  INDEX `fk_concept_concept_class` (`concept_class_id` ASC) ,
  INDEX `fk_concept_concept_category` (`concept_category_id` ASC) ,
  CONSTRAINT `fk_concept_concept_details`
    FOREIGN KEY (`concept_details_id` )
    REFERENCES `lel`.`concept_details` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_document`
    FOREIGN KEY (`document_id` )
    REFERENCES `lel`.`document` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_concept_class`
    FOREIGN KEY (`concept_class_id` )
    REFERENCES `lel`.`concept_class` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_concept_category`
    FOREIGN KEY (`concept_category_id` )
    REFERENCES `lel`.`concept_category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`user_action`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`user_action` ;

CREATE  TABLE IF NOT EXISTS `lel`.`user_action` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`user_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`user_log` ;

CREATE  TABLE IF NOT EXISTS `lel`.`user_log` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
  `user_id` INT NOT NULL ,
  `user_action_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_user_log_user` (`user_id` ASC) ,
  INDEX `fk_user_log_user_action` (`user_action_id` ASC) ,
  CONSTRAINT `fk_user_log_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `lel`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_log_user_action`
    FOREIGN KEY (`user_action_id` )
    REFERENCES `lel`.`user_action` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept_action`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_action` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_action` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_log` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_log` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
  `user_id` INT NOT NULL ,
  `concept_id` INT NOT NULL ,
  `concept_action_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_concept_log_concept` (`concept_id` ASC) ,
  INDEX `fk_concept_log_concept_action` (`concept_action_id` ASC) ,
  CONSTRAINT `fk_concept_log_concept`
    FOREIGN KEY (`concept_id` )
    REFERENCES `lel`.`concept` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_log_concept_action`
    FOREIGN KEY (`concept_action_id` )
    REFERENCES `lel`.`concept_action` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
