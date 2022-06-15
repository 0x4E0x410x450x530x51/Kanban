CREATE DATABASE IF NOT EXISTS KanbanSimulationDatabase;
USE KanbanSimulationDatabase;

CREATE TABLE Users (
    userID INT,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE User_KanbanBoard (
  kanbanboardID INT,
  userID INT,
  owner BOOL
);

CREATE TABLE KanbanBoard (
  kanbanboardID INT,
  created DATE,
  completed INT,
  inprogress INT
);

CREATE TABLE KanbanBoard_Story (
    kanbanboardID INT,
    storyID INT
);

CREATE TABLE Story (
  storyID INT,
  storyname VARCHAR(255),
  storyStatus INT,
  storyDescription VARCHAR(1024),
  storyExpire DATE,
  storyAssign INT
);


ALTER TABLE Users MODIFY userID INT PRIMARY KEY AUTO_INCREMENT;
ALTER TABLE KanbanBoard MODIFY kanbanboardID INT PRIMARY KEY AUTO_INCREMENT;
ALTER TABLE Story MODIFY storyID INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE User_KanbanBoard ADD FOREIGN KEY (userID) REFERENCES Users (userID);
ALTER TABLE User_KanbanBoard ADD FOREIGN KEY (kanbanboardID) REFERENCES KanbanBoard (kanbanboardID);
ALTER TABLE KanbanBoard_Story ADD FOREIGN KEY (kanbanboardID) REFERENCES KanbanBoard (kanbanboardID);
ALTER TABLE KanbanBoard_Story ADD FOREIGN KEY (storyID) REFERENCES Story (storyID);
