CREATE TABLE en_role (
    role_id INT8 PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    active BOOL NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE SEQUENCE se_role_id INCREMENT BY 1 START WITH 1 OWNED BY en_role.role_id;
