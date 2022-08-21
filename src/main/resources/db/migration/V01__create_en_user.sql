CREATE TABLE en_user (
    user_id INT8 PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_AT TIMESTAMP NOT NULL
);

CREATE SEQUENCE se_user_id INCREMENT BY 1 START WITH 1 OWNED BY en_user.user_id;
