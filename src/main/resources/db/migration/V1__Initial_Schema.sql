CREATE TABLE item_listings
(
    seller_id   UUID                                              NOT NULL,
    title       VARCHAR(200)                                          NOT NULL,
    description TEXT,
    price       numeric(10, 2)                                        NOT NULL,
    image_url   TEXT,
    created_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()             NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()             NOT NULL,
    id          UUID                    DEFAULT gen_random_uuid() NOT NULL,
    CONSTRAINT item_listings_pkey PRIMARY KEY (id)
);

CREATE TABLE order_items
(
    order_item_id UUID DEFAULT gen_random_uuid() NOT NULL,
    order_id      UUID                           NOT NULL,
    listing_id    UUID                           NOT NULL,
    seller_id     UUID                           NOT NULL,
    quantity      INTEGER                            NOT NULL,
    price_each    numeric(10, 2)                     NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (order_item_id)
);

CREATE TABLE orders
(
    buyer_id   UUID                                              NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()             NOT NULL,
    order_id   UUID                    DEFAULT gen_random_uuid() NOT NULL,
    status     VARCHAR(50)                 DEFAULT 'pending'         NOT NULL,
    total_paid numeric(10, 2)                                        NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id)
);

CREATE TABLE users
(
    username      VARCHAR(50)                                           NOT NULL,
    password_hash VARCHAR(255)                                          NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    email         VARCHAR(255),
    id            UUID                    DEFAULT gen_random_uuid() NOT NULL,
    coin_balance  numeric(12, 2)              DEFAULT 0                 NOT NULL,
    role          VARCHAR(50)                 DEFAULT 'user'            NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT users_username_key UNIQUE (username);

ALTER TABLE users
    ADD CONSTRAINT user_role_check CHECK (role IN ('user', 'moderator', 'admin', 'superadmin'));

ALTER TABLE users
    ADD CONSTRAINT username_check CHECK (username ~ '^[A-Za-z0-9_-]+$');

ALTER TABLE item_listings
    ADD CONSTRAINT item_listings_seller_id_fkey FOREIGN KEY (seller_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE item_listings
    ADD CONSTRAINT price_check CHECK (price >= 0);

ALTER TABLE order_items
    ADD CONSTRAINT order_items_listing_id_fkey FOREIGN KEY (listing_id) REFERENCES item_listings (id) ON DELETE NO ACTION;

ALTER TABLE order_items
    ADD CONSTRAINT order_items_order_id_fkey FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE NO ACTION;

ALTER TABLE order_items
    ADD CONSTRAINT order_items_seller_id_fkey FOREIGN KEY (seller_id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE order_items
    ADD CONSTRAINT price_check CHECK (price_each >= 0);

ALTER TABLE order_items
    ADD CONSTRAINT quantity_check CHECK (quantity > 0);

ALTER TABLE orders
    ADD CONSTRAINT orders_buyer_id_fkey FOREIGN KEY (buyer_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE orders
    ADD CONSTRAINT order_status_check CHECK (status IN ('pending', 'completed'));

ALTER TABLE orders
    ADD CONSTRAINT total_paid_check CHECK (total_paid >= 0);
