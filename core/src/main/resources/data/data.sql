select *
from dual;
select *
from category;
select *
from product;
select *
from orders;
select *
from order_items;
select * from partial_payment;
select * from orders_history;
select * from order_items_history;
select * from partial_payment_history;
select * from trusted_client;

CREATE SEQUENCE seq_category
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table category
(
    id_category number(10) primary key,
    name        varchar2(50)
);

CREATE SEQUENCE seq_product
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table product
(
    id_product  number(10) primary key,
    id_category number(10),
    name        varchar2(100),
    price       number(10, 2),
    CONSTRAINT fk_category FOREIGN KEY (id_category) REFERENCES category (id_category)
);

CREATE SEQUENCE seq_order
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table orders
(
    id_order          number(10) primary key,
    name              varchar2(100),
    status            varchar2(20),
    status_changed_at date
);

CREATE SEQUENCE seq_order_items
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table order_items
(
    id_order_item   number(10) primary key,
    id_order        number(10),
    id_product      number(10),
    quantity        number(2),
    item_ordered_at date,
    CONSTRAINT fk_order FOREIGN KEY (id_order) REFERENCES orders (id_order),
    CONSTRAINT fk_product FOREIGN KEY (id_product) REFERENCES product (id_product)
);

CREATE SEQUENCE seq_partial_payment
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table partial_payment
(
    id_partial_payment number(10) primary key,
    id_order           number(10),
    description        varchar2(100),
    value              number(10, 2),
    paid_at            date,
    payment_type varchar2(20),
    CONSTRAINT fk_order_2 FOREIGN KEY (id_order) REFERENCES orders (id_order)
);

create table orders_history
(
    id_order_history  number(10) primary key,
    name              varchar2(100),
    status            varchar2(20),
    status_changed_at date,
    total_value       number(10, 2),
    pendent_owner     number(10),
    payment_type varchar2(20),
    CONSTRAINT pendent_owner FOREIGN KEY (pendent_owner) REFERENCES trusted_client (id_trusted_client)
);

create table order_items_history
(
    id_order_item_history   number(10) primary key,
    id_order_history        number(10),
    name            varchar2(100),
    price           number(10, 2),
    quantity        number(2),
    item_ordered_at date,
    CONSTRAINT fk_order_history FOREIGN KEY (id_order_history) REFERENCES orders_history (id_order_history)
);

create table partial_payment_history
(
    id_partial_payment_history number(10) primary key,
    id_order_history           number(10),
    description        varchar2(100),
    value              number(10, 2),
    paid_at            date,
    payment_type varchar2(20),
    CONSTRAINT fk_order_history_2 FOREIGN KEY (id_order_history) REFERENCES orders_history (id_order_history)
);

CREATE SEQUENCE seq_trusted_client
    MINVALUE 1
    MAXVALUE 999999999999
    START WITH 1
    INCREMENT BY 1;

create table trusted_client
(
    id_trusted_client number(10) primary key,
    name               varchar2(100),
    description        varchar2(1000)
);
d
insert into orders
values (seq_order.nextval, 'mesa do careca', 'OPEN', sysdate);
insert into order_items
values (seq_order_items.nextval, 2, 8, 1, sysdate);
insert into order_items
values (seq_order_items.nextval, 2, null, 1, sysdate);
delete order_items
where id_order_item = 27;
insert into PARTIAL_PAYMENT
values (seq_partial_payment.nextval, 2, 'abatimento do ze', 20, sysdate);

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


-- botao onde o cliente paga 1 valor e ele vai amortizado as ultimas comnandas
-- Marcar se o pagamento foi feito com CC ou dinheiro

-- scheduler pra mandar dump da base via email 3x ao dia

-- OK Produtos: quando add um produto, a categoria fica o numero nao o nome
-- OK Comandas: precisar ter px fixos para nao encolher no monitor menor
--Clientes: no Pc do bar, quando ia adicionar um clinte dava erro de invalid humber e nao adicionada. testar sem massa.
-- OK Impl. editar categoria
-- OK Impl. editar produtos
-- OK Impl. editar cliente
--Criar relatorio de gastos dos dias anteiores.
-- OK Deletar em cascata (add mensagem on error. categoria > produto. produto > comanda)
--Comanda precisa ter um campo livre