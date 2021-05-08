select * from dual;
select * from category;
select * from product;
select * from orders;
select * from order_items;


CREATE SEQUENCE seq_category
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table category(
                         id_category number(10) primary key,
                         name varchar2(50)
);

CREATE SEQUENCE seq_product
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table product(
                        id_product number(10) primary key,
                        id_category number(10),
                        name varchar2(100),
                        price number(10, 2),
                        CONSTRAINT fk_category FOREIGN KEY (id_category) REFERENCES category(id_category)
);

CREATE SEQUENCE seq_order
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table orders(
                       id_order number(10) primary key,
                       name varchar2(100),
                       status varchar2(20),
                       status_changed_at date
);

CREATE SEQUENCE seq_order_items
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table order_items
(
    id_order_item number(10) primary key,
    id_order number(10),
    id_product number(10),
    quantity number(2),
    item_ordered_at date,
    CONSTRAINT fk_order FOREIGN KEY (id_order) REFERENCES orders (id_order),
    CONSTRAINT fk_product FOREIGN KEY (id_product) REFERENCES product(id_product)
);

insert into orders values(seq_order.nextval, 'mesa do careca', 'OPEN', sysdate);
insert into order_items values(seq_order_items.nextval, 2, 8, 1, sysdate);
insert into order_items values(seq_order_items.nextval, 2, null, 1, sysdate);
delete order_items where id_order_item = 27

update product
set name = 'Kariri'
where id_product = 3;

update product
set name = 'Brahma lata'
where id_product = 7;

update product
set name = 'Skol garrafa'
where id_product = 8;

update product
set name = '51'
where id_product = 6;



-- OK Testar se o adicionar comanda esta funcionado perfeitamente.
-- Criar modal para o btnMinus, precisando confirmar a delecao.
-- Atualizar o titulo das comnadas. tentar com focus.
-- Fechar order pode deletar a order direto caso nao haja itens.
-- Criar o modal de fechar comanda
-- Criar o modal de pendurar

--1 tabela pro controle de comandas em aberta, e 1 de history
--1 tabela pros clientes confianca
--scheduler pra mandar dump da base via email 3x ao dia
--criar menu dos pendurados


CREATE TABLE BLOG (
                      ID VARCHAR2(255),
                      TITLE VARCHAR2(255),
                      DESCRIPTION VARCHAR2(255),
                      BODY VARCHAR2(255),
                      CREATED_AT DATE,
                      UPDATED_AT DATE,
                      PRIMARY KEY (ID)
)