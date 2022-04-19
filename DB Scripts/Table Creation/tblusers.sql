create table TBLUSERS
(
    USER_ID     NUMBER generated as identity,
    FIRST_NAME  VARCHAR(30) not null,
    MIDDLE_NAME VARCHAR(30),
    LAST_NAME   VARCHAR(30) not null,
    AGE         NUMBER      not null,
    GENDER      VARCHAR(13) not null,
    EMAIL_ID    VARCHAR(30) not null,
    DOOR_NO     VARCHAR(10) not null,
    STR_NAME    VARCHAR(70) not null,
    CITY        VARCHAR(25) not null,
    PINCODE     NUMBER      not null,
    STATE       VARCHAR(30) not null
);

create trigger USERID_TRG
    before insert
    on TBLUSERS
    for each row
begin
    if :NEW.USER_ID is null then
        select USER_ID_SEQ.nextval into :NEW.USER_ID from dual;
    end if;
end;


