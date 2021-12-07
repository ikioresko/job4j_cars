create table body(
id serial primary key,
name VARCHAR(300)
);

create table brand(
id serial primary key,
name VARCHAR(300)
);

create table model(
id serial primary key,
name VARCHAR(300),
brand INT REFERENCES brand(id)
);

create table category(
id serial primary key,
name VARCHAR(300)
);

create table users(
id serial primary key,
username VARCHAR(30),
email VARCHAR(30),
password VARCHAR(30)
);

create table advert(
id serial primary key,
created TIMESTAMP with time zone,
category_id INT REFERENCES category(id),
brand_id INT REFERENCES brand(id),
model_id INT REFERENCES model(id),
body_id INT REFERENCES body(id),
description VARCHAR(3000),
price INT,
sold boolean DEFAULT false,
author_id INT REFERENCES users(id),
photoPath VARCHAR(3000)
);