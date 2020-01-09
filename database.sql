USE workshop2;
CREATE TABLE exercises
(
    id          INT(11) AUTO_INCREMENT,
    title       VARCHAR(255),
    description TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE user_groups
(
    id   INT(11) AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            INT(11) AUTO_INCREMENT,
    username      VARCHAR(255),
    email         VARCHAR(255),
    password      VARCHAR(245),
    user_group_id INT,
    admin TINYINT(1) DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (user_group_id) REFERENCES user_groups (id)
);

CREATE TABLE solutions
(
    id          INT AUTO_INCREMENT,
    created     DATETIME,
    updated     DATETIME,
    description TEXT,
    exercise_id INT,
    user_id     INT,
    PRIMARY KEY (id),
    FOREIGN KEY (exercise_id) REFERENCES exercises (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);






