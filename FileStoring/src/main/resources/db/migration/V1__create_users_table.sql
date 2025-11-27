CREATE TABLE users (
    id UUID PRIMARY KEY,
    firstname VARCHAR(255) not null,
    lastname  VARCHAR(255) not null,
    unique (firstname, lastname)
);