DROP DATABASE IF EXISTS KanbanBoardSimulation;
CREATE DATABASE IF NOT EXISTS KanbanBoardSimulation;

USE KanbanBoardSimulation;

CREATE TABLE Board (
  UUID VARCHAR(50) NOT NULL,
  creationDate date NOT NULL,
  PRIMARY KEY (UUID)
);

CREATE TABLE SettingsConfiguration (
  ID INT NOT NULL AUTO_INCREMENT,
  boardUUID VARCHAR(50) NOT NULL,
  constantID INT NOT NULL,
  normalID INT NOT NULL,
  sizedID INT NOT NULL,
  rulesID INT NOT NULL,
  defaultValue BOOLEAN NOT NULL,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE createStrat (
  ID INT NOT NULL AUTO_INCREMENT,
  constantpushID INT NOT NULL,
  randompushID INT NOT NULL,
  scrumcreateID INT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE constantpush (
  ID INT NOT NULL AUTO_INCREMENT,
  itemspday FLOAT NOT NULL,
  `desc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE randompush (
  ID INT NOT NULL AUTO_INCREMENT,
  demandLevel FLOAT NOT NULL,
  batchsize INT NOT NULL,
  `desc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE scrumcreate (
  ID INT NOT NULL AUTO_INCREMENT,
  iteration_len INT NOT NULL,
  taskPerIteration INT NOT NULL,
  wipSub BOOLEAN NOT NULL,
  `desc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE rules (
  ID INT NOT NULL AUTO_INCREMENT,
  maxWipPerPerson INT NOT NULL,
  maxPeopleWorkOnWip INT NOT NULL,
  warmupTime FLOAT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE sizedSettings (
  ID INT NOT NULL AUTO_INCREMENT,
  effortSmall FLOAT NOT NULL,
  probabilitySmall INT NOT NULL,
  effortMedium FLOAT NOT NULL,
  probabilityMedium INT NOT NULL,
  effortLarge FLOAT NOT NULL,
  probabilityLarge INT NOT NULL,
  effortXLarge FLOAT NOT NULL,
  probabilityXLarge INT NOT NULL,
  `desc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE normalSettings (
  ID INT NOT NULL AUTO_INCREMENT,
  meanHours FLOAT NOT NULL,
  variations FLOAT NOT NULL,
  departmentID INT NOT NULL,
  `desc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE constantSettings (
  ID INT NOT NULL AUTO_INCREMENT,
  amountHours FLOAT NOT NULL,
  departmentID INT NOT NULL,
  `desc` VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE departmentSized (
  ID INT NOT NULL AUTO_INCREMENT,
  sizedSettings INT NOT NULL,
  effort INT NOT NULL,
  departmentID INT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE departments (
  ID INT NOT NULL AUTO_INCREMENT,
  departmentName VARCHAR(255) NOT NULL,
  members INT NOT NULL,
  colIndex INT NOT NULL,
  efficency INT NOT NULL,
  doingLimit INT NOT NULL,
  doneLimit INT NOT NULL,
  PRIMARY KEY (ID)
);

ALTER TABLE SettingsConfiguration ADD FOREIGN KEY (boardUUID) REFERENCES Board(UUID);
ALTER TABLE SettingsConfiguration ADD FOREIGN KEY (rulesID) REFERENCES rules(ID);
ALTER TABLE SettingsConfiguration ADD FOREIGN KEY (constantID) REFERENCES constantSettings(ID);
ALTER TABLE SettingsConfiguration ADD FOREIGN KEY (normalID) REFERENCES normalSettings(ID);
ALTER TABLE SettingsConfiguration ADD FOREIGN KEY (sizedID) REFERENCES sizedSettings(ID);

ALTER TABLE createStrat ADD FOREIGN KEY (constantpushID) REFERENCES constantpush(ID);
ALTER TABLE createStrat ADD FOREIGN KEY (randompushID) REFERENCES randompush(ID);
ALTER TABLE createStrat ADD FOREIGN KEY (scrumcreateID) REFERENCES scrumcreate(ID);
ALTER TABLE createStrat ADD FOREIGN KEY (ID) REFERENCES SettingsConfiguration(ID);

ALTER TABLE constantSettings ADD FOREIGN KEY (departmentID) REFERENCES departments(ID);
ALTER TABLE normalSettings ADD FOREIGN KEY (departmentID) REFERENCES departments(ID);

ALTER TABLE departmentSized ADD FOREIGN KEY (departmentID) REFERENCES departments(ID);
ALTER TABLE departmentSized ADD FOREIGN KEY (sizedSettings) REFERENCES sizedSettings(ID);