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
    height int,
    body varchar,
    brand_id int references Brands(id)
);

create table if not exists Engines
(
    id serial primary key,
    name varchar,
    volume int,
    cylinders int,
    height int,
    car_id int references Cars(id)
);

create table if not exists Users
(
    id serial primary key,
    username varchar unique,
    password varchar,
    role varchar default 'USER',
    status varchar default 'ACTIVE',
    brand_id int references Brands(id)
);