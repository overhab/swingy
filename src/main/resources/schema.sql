CREATE TABLE IF NOT EXISTS hero (
    id BIGSERIAL not null primary key,
    heroName VARCHAR(20) not null,
    heroClass varchar(10) not null,
    level int not null,
    exp bigint not null,
    attack int not null,
    defence int not null,
    hitPoints int not null
);

create table if not exists artifact (
    id bigserial not null primary key,
    name varchar(60) not null,
    type varchar(20) not null,
    tier int not null,
    effect int not null
);

create table if not exists inventory (
    id bigserial not null primary key,
    hero_id bigint references hero(id),
    item_id bigint references artifact(id)
);