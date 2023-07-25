--INSERT INTO product (price, weight, description, image_url, name) VALUES
--(100, 10, 'description1', 'url1', 'mouse1'),
--(200, 20, 'description2', 'url2', 'mouse2');
--
--insert INTO productCategory (description, name ) VALUES
--("category description 1", "category name 1")
--("category description 2", "category name 2");

INSERT INTO product_category
(category_description,
category_name)
VALUES ( 'new kitchen supplies',
'kitchen'
);

INSERT INTO shop.product
(price, weight,
product_category_id,
description,
image_url,
name)
VALUES (5, 2, (select id from shop.product_category where category_name ='kitchen' limit 1), 'blabla', 'ahsdvkahvd', 'spoon');