ALTER TABLE orders
	RENAME COLUMN order_id TO id;
	
ALTER TABLE order_items
	RENAME COLUMN order_item_id TO id;
	
ALTER TABLE cart_items
	RENAME COLUMN cart_item_id TO id;
