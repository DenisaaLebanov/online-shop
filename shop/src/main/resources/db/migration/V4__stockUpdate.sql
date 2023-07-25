UPDATE shop.stock
SET quantity = 40
WHERE location_id = (select id from shop.location where city ='Timisoara' limit 1)
AND product_id = (select id from shop.product where name ='notebook' limit 1);


UPDATE shop.stock
SET quantity = 40
WHERE location_id = (select id from shop.location where city ='Timisoara' limit 1)
AND product_id = (select id from shop.product where name ='pencil' limit 1);