create table if not exists customer
(
    id   serial primary key,
    name text not null
);

create table if not exists cart
(
    id       serial primary key,
    customer int references customer (id)
);

create table if not exists line_item
(
    id   serial primary key,
    sku  text not null,
    cart int references cart (id)
);