--for h2
insert into sbsb_product (product_id,price,name)values(1,'2999.00','Watch');
insert into sbsb_product (product_id,price,name)values(2,'999.00','Book');
insert into sbsb_product (product_id,price,name)values(3,'9999.00','SmartPhone');
insert into sbsb_product (product_id,price,name)values(4,'499.00','Toys');
insert into sbsb_product (product_id,price,name)values(5,'399.00','Bottle');

insert into sbsb_review (review_id,ratings,commment,product_id_fk)values(1,8,'Watch is Good',1);
insert into sbsb_review (review_id,ratings,commment,product_id_fk)values(2,9,'Book is Good',2);
insert into sbsb_review (review_id,ratings,commment,product_id_fk)values(3,7,'Great',1);
insert into sbsb_review (review_id,ratings,commment,product_id_fk)values(4,8,'Average',1);
