SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

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
  `password` VARCHAR(64) NOT NULL,
  `registered` TIMESTAMP NOT NULL,
  `enabled` BIT NOT NULL,
  `points` INT UNSIGNED NULL DEFAULT NULL,
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
  `budget` INT UNSIGNED NULL DEFAULT NULL,
  `description` VARCHAR(1200) NULL DEFAULT NULL,
  `runtime` SMALLINT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Represent a movie';


-- -----------------------------------------------------
-- Table `movie_rating`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`genre` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
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
  `comment` TEXT(2000) NOT NULL,
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
    ON DELETE CASCADE
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
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Represents rating of current user about current movie';


-- -----------------------------------------------------
-- Table `movie_rating`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`country` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Countries that have made the current movie';


-- -----------------------------------------------------
-- Table `movie_rating`.`movie_has_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`movie_has_genre` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`movie_has_genre` (
  `movie_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  PRIMARY KEY (`movie_id`, `genre_id`),
  INDEX `fk_movie_has_genre_genre1_idx` (`genre_id` ASC),
  INDEX `fk_movie_has_genre_movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_movie_has_genre_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_rating`.`movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_has_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `movie_rating`.`genre` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `movie_rating`.`movie_has_country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie_rating`.`movie_has_country` ;

CREATE TABLE IF NOT EXISTS `movie_rating`.`movie_has_country` (
  `movie_id` INT NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`movie_id`, `country_id`),
  INDEX `fk_movie_has_country_country1_idx` (`country_id` ASC),
  INDEX `fk_movie_has_country_movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_movie_has_country_movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie_rating`.`movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_has_country_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `movie_rating`.`country` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
