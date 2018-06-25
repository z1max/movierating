-- MySQL Script generated by MySQL Workbench
-- Sun 24 Jun 2018 09:11:22 PM +03
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema movie_rating
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movie_rating
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movie_rating` DEFAULT CHARACTER SET utf8 ;
USE `movie_rating` ;

-- -----------------------------------------------------
-- Table `movie_rating`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`user` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `registered` TIMESTAMP NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Represents users of application';


-- -----------------------------------------------------
-- Table `movie_rating`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`user_role` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`user_role` (
  `user_id` INT NOT NULL,
  `role` TINYINT NOT NULL,
  INDEX `fk_user_roles_users_idx` (`user_id` ASC),
  PRIMARY KEY (`user_id`, `role`),
  CONSTRAINT `fk_user_roles_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `movie_rating`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Stores user roles';


-- -----------------------------------------------------
-- Table `movie_rating`.`movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`movie` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`movie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(60) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `release_date` DATE NOT NULL,
  `budget` INT UNSIGNED NULL,
  `description` VARCHAR(1200) NULL,
  `runtime` SMALLINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Represwent a movie';


-- -----------------------------------------------------
-- Table `movie_rating`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`genre` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`genre` (
  `movie_id` INT NOT NULL,
  `genre` TINYINT NOT NULL,
  INDEX `fk_genres_movie_idx` (`movie_id` ASC),
  PRIMARY KEY (`movie_id`, `genre`),
  CONSTRAINT `fk_genres_movie`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_rating`.`movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Genre of movie';


-- -----------------------------------------------------
-- Table `movie_rating`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`review` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  `comment` TEXT(2000) NULL,
  `date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_user_idx` (`user_id` ASC),
  INDEX `fk_review_movie_idx` (`movie_id` ASC),
  CONSTRAINT `fk_review_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `movie_rating`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_rating`.`movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Review of current user about current movie';


-- -----------------------------------------------------
-- Table `movie_rating`.`rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`rating` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`rating` (
  `user_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  `rating` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `movie_id`),
  INDEX `fk_rating_user_idx` (`user_id` ASC),
  INDEX `fk_rating_movie_idx` (`movie_id` ASC),
  CONSTRAINT `fk_rating_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `movie_rating`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rating_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_rating`.`movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Represents rating of current user about current movie';


-- -----------------------------------------------------
-- Table `movie_rating`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`country` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`country` (
  `movie_id` INT NOT NULL,
  `name` VARCHAR(2) NOT NULL,
  INDEX `fk_countries_movies1_idx` (`movie_id` ASC),
  PRIMARY KEY (`name`, `movie_id`),
  CONSTRAINT `fk_countries_movies1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_rating`.`movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Countries that have made the currrent movie';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;