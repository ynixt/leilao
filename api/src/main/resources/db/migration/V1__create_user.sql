CREATE TABLE user (
	id int unsigned not null auto_increment,
    login varchar(15) not null,
    password binary(60) not null,
    active tinyint not null default 1,
    primary key (id),
    unique index login_unique (login)
);