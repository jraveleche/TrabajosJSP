-- MySQL Script generated by MySQL Workbench
-- Sun Apr  1 17:53:49 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema analisis
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema analisis
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `analisis` DEFAULT CHARACTER SET utf8 ;
USE `analisis` ;

-- -----------------------------------------------------
-- Table `analisis`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `analisis`.`usuario` (
  `usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `correo` VARCHAR(45) NULL,
  `password` VARCHAR(255) NULL,
  `edad` INT NULL,
  `sexo` INT NULL,
  `tipo` INT NULL,
  `foto` MEDIUMBLOB NULL,
  PRIMARY KEY (`usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `analisis`.`puesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `analisis`.`puesto` (
  `puesto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`puesto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `analisis`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `analisis`.`categoria` (
  `categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(100) NULL,
  PRIMARY KEY (`categoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `analisis`.`oferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `analisis`.`oferta` (
  `oferta` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NULL,
  `numeroPlazas` INT NULL,
  `nivelExperiencia` INT NULL,
  `salario` DECIMAL(9,2) NULL,
  `vehiculo` INT NULL,
  `fecha` DATE NULL,
  `estado` INT NULL,
  `categoria_categoria` INT NOT NULL,
  `puesto_puesto` INT NOT NULL,
  PRIMARY KEY (`oferta`),
  INDEX `fk_oferta_categoria1_idx` (`categoria_categoria` ASC),
  INDEX `fk_oferta_puesto1_idx` (`puesto_puesto` ASC),
  CONSTRAINT `fk_oferta_categoria1`
    FOREIGN KEY (`categoria_categoria`)
    REFERENCES `analisis`.`categoria` (`categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oferta_puesto1`
    FOREIGN KEY (`puesto_puesto`)
    REFERENCES `analisis`.`puesto` (`puesto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `analisis`.`ofertaUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `analisis`.`ofertaUsuario` (
  `usuario_usuario` INT NOT NULL,
  `oferta_oferta` INT NOT NULL,
  PRIMARY KEY (`usuario_usuario`, `oferta_oferta`),
  INDEX `fk_ofertaUsuario_usuario_idx` (`usuario_usuario` ASC),
  INDEX `fk_ofertaUsuario_oferta1_idx` (`oferta_oferta` ASC),
  CONSTRAINT `fk_ofertaUsuario_usuario`
    FOREIGN KEY (`usuario_usuario`)
    REFERENCES `analisis`.`usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ofertaUsuario_oferta1`
    FOREIGN KEY (`oferta_oferta`)
    REFERENCES `analisis`.`oferta` (`oferta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `analisis`.`postulacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `analisis`.`postulacion` (
  `fecha` DATE NULL,
  `estado` INT NULL,
  `ofertaUsuario_usuario_usuario` INT NOT NULL,
  `ofertaUsuario_oferta_oferta` INT NOT NULL,
  `usuario_usuario` INT NOT NULL,
  PRIMARY KEY (`ofertaUsuario_usuario_usuario`, `ofertaUsuario_oferta_oferta`, `usuario_usuario`),
  INDEX `fk_postulacion_usuario1_idx` (`usuario_usuario` ASC),
  CONSTRAINT `fk_postulacion_ofertaUsuario1`
    FOREIGN KEY (`ofertaUsuario_usuario_usuario` , `ofertaUsuario_oferta_oferta`)
    REFERENCES `analisis`.`ofertaUsuario` (`usuario_usuario` , `oferta_oferta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_postulacion_usuario1`
    FOREIGN KEY (`usuario_usuario`)
    REFERENCES `analisis`.`usuario` (`usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
