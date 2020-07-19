create TABLE bid(
	id int unsigned not null auto_increment,
	value float not null,
    auction_id int unsigned not null,
    date DATETIME not null,
    made_by_user_id int unsigned not null,
    primary key (id),
    INDEX fkIdx_auction_id(auction_id),
    CONSTRAINT fk_auction_id
	  FOREIGN KEY (auction_id)
	  REFERENCES auction (id),
    INDEX fkIdx_made_by_user_id(made_by_user_id),
    CONSTRAINT fk_made_by_user_id
	  FOREIGN KEY (made_by_user_id)
	  REFERENCES user (id)
);