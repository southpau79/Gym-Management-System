create table TBLLOGIN
(
    USER_ID   NUMBER      not null,
    USER_NAME VARCHAR(10) not null,
    PASSWORD  VARCHAR(10) not null
        constraint TBLLOGIN_PK
            primary key,
    ROLE_ID   NUMBER      not null
        constraint TBLLOGIN_TBLROLES_ROLE_ID_FK
            references TBLROLES
);


