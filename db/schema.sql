create table advert(
id serial primary key,
carBrand VARCHAR(30),
description VARCHAR(300),
sold boolean DEFAULT false,
author_id INT REFERENCES users(id),
bodyType_id INT REFERENCES bodyTypes(id),
photoPath VARCHAR(300)
);

create table bodytypes(
id serial primary key,
bodyType VARCHAR(300)
);

create table users(
id serial primary key,
name VARCHAR(30),
email VARCHAR(30),
password VARCHAR(30)
);