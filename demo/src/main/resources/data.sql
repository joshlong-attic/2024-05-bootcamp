delete from line_item;
delete from cart;
delete from customer;

insert into customer(name) values ('Lou');
insert into customer(name) values ('Josh');

insert into cart(customer) values ((select id from customer where name='Josh'));
insert into cart(customer) values ((select id from customer where name='Josh'));
insert into cart(customer) values ((select id from customer where name='Lou'));

insert into line_item (sku, cart)
values ('1234',
    (select ca.id from cart ca, customer cu where ca.customer = cu.id and cu.name = 'Lou' limit 1));


insert into line_item (sku, cart)
values ('5678',
        (select ca.id from cart ca, customer cu where ca.customer = cu.id and cu.name = 'Josh' limit 1));


insert into line_item (sku, cart)
values ('9112',
        (select ca.id from cart ca, customer cu where ca.customer = cu.id and cu.name = 'Josh' limit 1));