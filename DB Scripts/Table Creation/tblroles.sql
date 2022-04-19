create table TBLROLES
(
    ROLE_ID          NUMBER       not null
        constraint TBLROLES_PK
            primary key,
    ROLE_NAME        VARCHAR(20)  not null,
    ROLE_DESCRIPTION VARCHAR(400) not null
);


