/* This script will create the database and set
up all the tables for the housing web application */
CREATE DATABASE IF NOT EXISTS csci201_database;

USE csci201_database;

/****************************************/
/********* Creating the Tables **********/
/****************************************/

/* Creating the user_table*/
CREATE TABLE IF NOT EXISTS user_table 
(
	userID int NOT NULL AUTO_INCREMENT,
	firstName varchar(255), 
	lastName varchar(255), 
	email varchar(255), 
	password varchar(255),
	PRIMARY KEY (userID)
);

/* Creating the top_housing table*/
CREATE TABLE IF NOT EXISTS top_housing_table
(
	userID int, /* userID of User who has a saved housing option */
	housingID int /* housingID of housing saved by the user */
);

CREATE TABLE IF NOT EXISTS online_table
(
	userID int,
    firstName varchar(255),
    lastName varchar(255)
);

/* Creating the roommate_table table*/
CREATE TABLE IF NOT EXISTS roommate_table
(
	userID int,
    friendID int,
    friendFirstName varchar(255),
    friendLastName varchar(255),
	RequesterID int, /* userID of the requesting user */
	RequesteeID int, /* userID of the requested user */
	status int /* represents if the request has been accepted or not,
				10 = pending, 20 = accepted */
);
/* Creating the preference_table */
CREATE TABLE IF NOT EXISTS preference_table
(
	userID int NOT NULL,
	preferredRoomType varchar(255), /* Single, Double, Triple, House, etc. */
	cookFrequency int,
	cleanliness int, /* measures how tollerable they are of messes */
	partyFrequency int,
	guestFrequency int,
	musicLoudness int, /* measures how tollerable they are of loud music */
	studyFrequency int, /* measures how often the student studies at the apartment */
	sleepingTime Time, /* number between 0-24 that measures when they typically go to sleep */
	wakingTime Time, /* number between 0-24 that measures when they typically wake up */
	schoolYear varchar(255), /* Freshman, Sophomore, Junior, Senior */
	pets boolean,
	smokes boolean,
	greek boolean,
	privacy int, /* measures the willingness of the student to share belongings */
	hobbies varchar(1000),
	funFact varchar(2000)
);

/* Creating the reviews_table */
CREATE TABLE IF NOT EXISTS reviews_table
(
	housingID int,
	stars int,
	content varchar(10000),
	author varchar(255),
	reviewDate date
);


/* Creating the housing_table */
CREATE TABLE IF NOT EXISTS housing_table
(
  `keyid` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `bed` INT NOT NULL,
  `bath` INT NOT NULL,
  `rent` INT NOT NULL,
  `wifi` INT NOT NULL,
  `ac` INT NOT NULL,
  `parking` INT NOT NULL,
  `cable` INT NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `distance` DOUBLE NOT NULL,
  `minutes` INT NOT NULL,
  `uscOwned` INT NOT NULL,
  `visibilityCounter` INT NOT NULL,
  `houseOrApartment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`keyid`)
);

/****************************************/
/******** Populating the Tables *********/
/****************************************/

/* Populating the user_table with inital rows */


/* Populating the housing_table with initial rows */
INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("2646 Ellendale", 13, 5, 769, 0, 1, 1, 0, 34.031595, -118.288281, 0.9, 17, 0, 1, "house");
INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("2943 Walton Ave", 9, 4, 1078, 0, 1, 0, 0, 34.027729, -118.295057, 0.9, 19, 0, 1,"house"); 
INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("1140 W. Adams Blvd", 10, 4, 1000, 0, 1, 1, 0, 34.028155, -118.295086, 0.8, 15, 0, 1,"house");
INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("1241 W. 37th Pl", 6, 4, 917, 1, 1, 1, 1, 34.020348, -118.295874, 0.9, 19, 0, 1, "house");
INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("3335 S Figueroa St (Gateway)", 2, 2, 2060, 1, 1, 1, 1, 34.022911, -118.279858, 0.4, 8, 0, 1,"apartment");

INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("325 West Adams Boulevard (Lorenzo)", 3, 3, 1798, 1, 1, 1, 1, 34.027339, -118.27324, 1.0, 20, 0, 1,"apartment");
INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("3131 McClintock Ave (Cardinal Gardens)", 2, 2, 2010, 1, 1, 1, 1, 34.026246, -118.28724, 0.4, 8, 1, 1,"apartment");
INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("3025 Royal St (Troy Hall)", 1, 1, 2169, 1, 1, 1, 1, 34.02566, -118.281765, 0.4, 8, 1, 1,"apartment");
INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("530 West 27th Street (West 27th)", 4, 2, 1740, 1, 1, 1, 1, 34.026920, -118.276101, 0.8, 15, 0, 1,"apartment");

INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("2638 Portland St (Sierra)", 2, 2, 1696, 1, 1, 1, 1, 34.029502, -118.282543, 0.7, 14, 1, 1,"apartment");

INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("3730 McClintock Ave (Parkside Apartments)", 1, 1, 2202, 0, 1, 0, 1, 34.018907, -118.290372, 0.5, 10, 1, 1, "apartment");

INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("711 W 27th St (Annenberg House)", 2, 2, 1769, 1, 1, 1, 1, 34.028961, -118.279120, 0.7, 14, 1, 1, "apartment");

INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("2610 Portland St (Founders)", 2, 2, 1776, 1, 1, 1, 1, 34.030332, -118.282184, 0.8, 16, 1, 1, "apartment");

INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("3115 S Orchard Ave (Century)", 2, 2, 1776, 1, 1, 1, 1, 34.026313, -118.289390, 0.6, 12, 1, 1, "apartment");

INSERT INTO housing_table(address, bed, bath, rent, ac, wifi, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("2637 Severance St (Pacific)", 2, 2, 1760, 1, 1, 1, 1, 34.029436, -118.281803, 0.7, 15, 1, 1, "apartment");

INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("2640 Menlo Street", 9, 5, 940, 1, 1, 1, 0, 34.031496, -118.289983, 1.1, 21, 0, 1, "house");

INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("1006 West 23rd Street", 6, 7, 1250, 1, 0, 0, 1, 34.034406, -118.281968, 1.1, 22, 0, 1, "house");

INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("1338 W. Jefferson Blvd", 4, 1.5, 1000, 0, 0, 0, 0, 34.025308, -118.295707, 0.9, 18, 0, 1, "house");


INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment) 
VALUES("1123 W. 37th Dr", 3, 2, 715, 0, 1, 0, 0, 34.019256, -118.292346, 0.5, 11, 0, 1, "apartment");



INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment) 
VALUES("1238 W. 35th St", 6, 3, 800, 0, 1, 1, 0, 34.023905, -118.295716, 0.8, 16, 0, 1, "house");


INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment) 
VALUES("3010 Shrine Pl.", 4, 3, 735, 0, 1, 0, 0, 34.025368, -118.279903, 0.5, 11, 0, 1, "apartment");


INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment) 
VALUES("1352 W. 29th St", 2, 2.5, 782, 0, 1, 1, 0, 34.028119, -118.290477, 0.9, 17, 0, 1, "house");

INSERT INTO housing_table(address, bed, bath, rent, wifi, ac, parking, cable, latitude, longitude, distance, minutes, uscOwned, visibilityCounter, houseOrApartment)
VALUES("1021 W. 24th St", 6, 3, 674, 0, 1, 0, 0, 34.033966, -118.282608, 1.1, 21, 0, 1, "house");
