CREATE TABLE re_user_role (
    user_id INT8 NOT NULL,
    role_id INT8 NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_re_user_role_en_user FOREIGN KEY (user_id) REFERENCES en_user(user_id),
    CONSTRAINT fk_re_user_role_en_role FOREIGN KEY (role_id) REFERENCES en_role(role_id)
);

ALTER TABLE en_user ADD active BOOL NOT NULL;
