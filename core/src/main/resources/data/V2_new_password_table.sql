create table login
(
    id_login number(10) primary key,
    password varchar2(20),
    last_login_at date
);

insert into login values(1, *****, sysdate-1);

commit;