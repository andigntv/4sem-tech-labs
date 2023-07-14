create table if not exists Brands
(
    id serial primary key,
    name varchar,
    date date
);

create table if not exists Cars
(
    id serial primary key,
    name varchar,
    length int,
    width int,
    body varchar,
    brand_id int references Brands (id)
);