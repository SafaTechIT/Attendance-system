CREATE TABLE users
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(256),
    role     int
);

Create table events
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    title   VARCHAR(255),
    date    DATE,
    members JSON
)