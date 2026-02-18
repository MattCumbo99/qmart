CREATE TABLE cart_items
(
    user_id      UUID                           NOT NULL,
    listing_id   UUID                           NOT NULL,
    quantity     INTEGER  DEFAULT 1                 NOT NULL,
    cart_item_id UUID DEFAULT gen_random_uuid() NOT NULL,
    CONSTRAINT cart_items_pkey PRIMARY KEY (cart_item_id)
);

ALTER TABLE cart_items
    ADD CONSTRAINT cart_items_user_id_listing_id_key UNIQUE (user_id, listing_id);

ALTER TABLE cart_items
    ADD CONSTRAINT cart_items_listing_id_fkey FOREIGN KEY (listing_id) REFERENCES item_listings (id) ON DELETE CASCADE;

ALTER TABLE cart_items
    ADD CONSTRAINT cart_items_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE cart_items
    ADD CONSTRAINT cart_items_quantity_check CHECK (quantity > 0);

DROP TYPE IF EXISTS user_role;
