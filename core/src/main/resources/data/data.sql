select * from dual;

create table category(
                         id_category number(10) primary key,
                         name varchar2(50)
);

create table product(
                        id_product number(10) primary key,
                        id_category number(10),
                        name varchar2(100),
                        price number(10),
                        CONSTRAINT fk_category FOREIGN KEY (id_category) REFERENCES category(id_category)
);

CREATE SEQUENCE seq_category
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

select * from category;