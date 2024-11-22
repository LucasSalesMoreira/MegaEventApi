create database valenotadb;

create table address (
	id varchar(36) primary key,
    street varchar(255) not null,
    city varchar(255) not null,
    district varchar(255) not null,
    number int not null,
    cep varchar(10) not null
);

CREATE TABLE user (
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	cnpj VARCHAR(14),
	password VARCHAR(255) NOT NULL,
	address_id VARCHAR(36) NOT NULL,
	role tinyint(1) NOT NULL,
	FOREIGN KEY(address_id) references address (id)
);

create table support (
	id varchar(36) primary key,
    number varchar(45),
    email varchar(255),
    site varchar(255)
);

create table event (
	id varchar(36) primary key,
    date_event datetime not null,
    price double not null,
    name varchar(255) not null,
    support_id varchar(36),
    address_id varchar(36) not null,
    owner_id varchar(36) not null,
    foreign key(address_id) references address (id),
    foreign key(owner_id) references user(id)
);

create table question (
	id varchar(36) primary key,
    q varchar(255) not null,
    resp varchar(255) not null
);

create table session_token (
	token varchar(32) primary key,
    user_id varchar(36),
    expired boolean default false not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    foreign key (user_id) references user (id)
);

create table network (
	id varchar(36) primary key,
	follower varchar(36) not null,
	followed varchar(36) not null,
	foreign key (follower) references user (id),
	foreign key (followed) references user (id)
);