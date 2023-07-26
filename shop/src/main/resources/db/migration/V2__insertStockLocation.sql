INSERT INTO shop.product
(price, weight,
product_category_id,
description,
image_url,
name)
VALUES
(5, 2, (select id from shop.product_category where category_name ='kitchen' limit 1), 'description1', 'image_url1', 'pencil'),
(5, 2, (select id from shop.product_category where category_name ='kitchen' limit 1), 'description2', 'image_url2', 'notebook');

INSERT INTO shop.location (name, country, city, county, street_address) VALUES
('Depozit Timisoara', 'Romania', 'Timisoara', 'Timis', 'Bulevardul Cetatii'),
('Depozit Arad', 'Romania', 'Arad', 'Arad', 'Bulevardul Revolutiei');

INSERT INTO shop.stock (quantity, location_id, product_id) VALUES
(10, (select id from shop.location where city ='Timisoara' limit 1), (select id from shop.product where name ='spoon' limit 1)),
(20, (select id from shop.location where city ='Timisoara' limit 1),  (select id from shop.product where name ='pencil' limit 1)),
(30, (select id from shop.location where city ='Timisoara' limit 1),  (select id from shop.product where name ='notebook' limit 1)),
(10, (select id from shop.location where city ='Arad' limit 1), (select id from shop.product where name ='spoon' limit 1)),
(10, (select id from shop.location where city ='Arad' limit 1),  (select id from shop.product where name ='pencil' limit 1)),
(10, (select id from shop.location where city ='Arad' limit 1),  (select id from shop.product where name ='notebook' limit 1));

