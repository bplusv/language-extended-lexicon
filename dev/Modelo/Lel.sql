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
  `password` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
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
-- Table `lel`.`concept_classification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_classification` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_classification` (
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
-- Table `lel`.`concept_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_details` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_details` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `comments` LONGTEXT NULL ,
  `notion` LONGTEXT NULL ,
  `actual_intention` LONGTEXT NULL ,
  `future_intention` LONGTEXT NULL ,
  `document_id` INT NOT NULL ,
  `concept_classification_id` INT NOT NULL ,
  `concept_category_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_concept_data_document` (`document_id` ASC) ,
  INDEX `fk_concept_data_concept_classification` (`concept_classification_id` ASC) ,
  INDEX `fk_concept_data_concept_category` (`concept_category_id` ASC) ,
  CONSTRAINT `fk_concept_data_document`
    FOREIGN KEY (`document_id` )
    REFERENCES `lel`.`document` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_data_concept_classification`
    FOREIGN KEY (`concept_classification_id` )
    REFERENCES `lel`.`concept_classification` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_data_concept_category`
    FOREIGN KEY (`concept_category_id` )
    REFERENCES `lel`.`concept_category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `active` TINYINT(1) NOT NULL DEFAULT 1 ,
  `name` LONGTEXT NOT NULL ,
  `concept_details_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_concept_concept_details` (`concept_details_id` ASC) ,
  CONSTRAINT `fk_concept_concept_details`
    FOREIGN KEY (`concept_details_id` )
    REFERENCES `lel`.`concept_details` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lel`.`concept_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lel`.`concept_event` ;

CREATE  TABLE IF NOT EXISTS `lel`.`concept_event` (
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
  `concept_id` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  `concept_event_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_concept_log_concept` (`concept_id` ASC) ,
  INDEX `fk_concept_log_user` (`user_id` ASC) ,
  INDEX `fk_concept_log_concept_event` (`concept_event_id` ASC) ,
  CONSTRAINT `fk_concept_log_concept`
    FOREIGN KEY (`concept_id` )
    REFERENCES `lel`.`concept` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_log_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `lel`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concept_log_concept_event`
    FOREIGN KEY (`concept_event_id` )
    REFERENCES `lel`.`concept_event` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
