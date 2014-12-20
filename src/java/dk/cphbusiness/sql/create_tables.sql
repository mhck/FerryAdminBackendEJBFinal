DROP TABLE RESERVATION_HAS_TRAVELING_ENTITY;
DROP TABLE RESERVATION;
DROP TABLE DEPARTURE;
DROP TABLE BOOKER;
DROP TABLE PRICE;
DROP TABLE ROUTE;
DROP TABLE TRAVELING_ENTITY;
DROP TABLE TRAVELING_ENTITY_TYPE;
DROP TABLE FERRY_CONFIG;
DROP TABLE FERRY_HAS_HARBOUR;
DROP TABLE HARBOUR;
DROP TABLE FERRY;

create table FERRY (
	id int,
	name varchar(40),
	primary key(id)
);

create table HARBOUR(
        id int,
        name varchar(60),
        primary key(id)
);

create table FERRY_HAS_HARBOUR(
        ferry_id int references FERRY(id),
        harbour_id int references HARBOUR(id),
        primary key(ferry_id, harbour_id)
);

create table FERRY_CONFIG(
	id int,
	people_capacity int NOT NULL,
	vehicle_capacity int NOT NULL,
	weight_capacity int NOT NULL,
        ferry_id int references FERRY(id),
	primary key(id)
);

create table TRAVELING_ENTITY_TYPE (
        id int,
        dtype varchar(40) UNIQUE,
        primary key(id)
);

create table TRAVELING_ENTITY(
        id int,
        weight int,
        reg_no varchar(40),
        vehicle_length int,
        dtype varchar(40) references TRAVELING_ENTITY_TYPE(dtype),
        primary key(id)
);

create table ROUTE(
        id int,
        duration int,
        id_destination int references HARBOUR(id),
        id_origin int references HARBOUR(id),
        primary key(id)
);

create table PRICE (
        id int,
        traveling_entity_id int references TRAVELING_ENTITY(id),
        route_id int references ROUTE(id),
        amount double,
        primary key(id)
);

create table BOOKER (
    id int,
    person_name varchar(80),
    email varchar(100),
    primary key(id)
);

create table DEPARTURE (
    id int,
    departure_time varchar(4),
    departure_date Date,
    route_id int references ROUTE(id),
    primary key(id)
);

create table RESERVATION (
    id int,
    reservation_number varchar(80),
    has_arrived boolean,
    booker_id int references BOOKER(id),
    departure_id int references DEPARTURE(id),
    primary key(id)
);

create table RESERVATION_HAS_TRAVELING_ENTITY(
    reservation_id int references reservation(id),
    traveling_entity_id int references TRAVELING_ENTITY(id),
    primary key(reservation_id, traveling_entity_id)
);