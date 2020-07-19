create table auction (
	id int unsigned not null auto_increment,
    name varchar(50) not null,
    initial_value float not null,
    used tinyint not null,
    open_date DATETIME not null,
    end_date DATETIME,
	primary key (id),
    user_responsible_id int unsigned not null,
    INDEX fkIdx_user_responsible_id(user_responsible_id),
    CONSTRAINT fk_user_responsible_id
	  FOREIGN KEY (user_responsible_id)
	  REFERENCES user (id)
);