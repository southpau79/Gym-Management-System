create table TBLMEMBERSHIP
(
    MEMBERSHIP_ID NUMBER      not null,
    USER_ID       NUMBER      not null,
    JOINING_DATE  VARCHAR(10) not null,
    END_DATE      VARCHAR(10) not null,
    TYPE_ID       NUMBER      not null
);

create unique index TBLMEMBERSHIP_PK
    on TBLMEMBERSHIP (MEMBERSHIP_ID);


