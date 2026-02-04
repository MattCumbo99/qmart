--
-- PostgreSQL database dump
--

\restrict 7ChqVwqI4GGDynI7nhmMT58tKqFa7kbdNWr9r7Q2sDMsKWY0JdZjTApyGUTl4RX

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: citext; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;


--
-- Name: EXTENSION citext; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';


--
-- Name: user_role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.user_role AS ENUM (
    'user',
    'moderator',
    'admin',
    'superadmin'
);


ALTER TYPE public.user_role OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: item_listings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_listings (
    seller_id uuid NOT NULL,
    title character varying(200) NOT NULL,
    description text,
    price numeric(10,2) NOT NULL,
    image_url text,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    CONSTRAINT price_check CHECK ((price >= (0)::numeric))
);


ALTER TABLE public.item_listings OWNER TO postgres;

--
-- Name: order_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_items (
    order_item_id uuid DEFAULT gen_random_uuid() NOT NULL,
    order_id uuid NOT NULL,
    listing_id uuid NOT NULL,
    seller_id uuid NOT NULL,
    quantity integer NOT NULL,
    price_each numeric(10,2) NOT NULL,
    CONSTRAINT price_check CHECK ((price_each >= (0)::numeric)),
    CONSTRAINT quantity_check CHECK ((quantity > 0))
);


ALTER TABLE public.order_items OWNER TO postgres;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    buyer_id uuid NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    order_id uuid DEFAULT gen_random_uuid() CONSTRAINT orders_id_not_null NOT NULL,
    status character varying(50) DEFAULT 'pending'::character varying NOT NULL,
    total_paid numeric(10,2) NOT NULL,
    CONSTRAINT order_status_check CHECK (((status)::text = ANY ((ARRAY['pending'::character varying, 'completed'::character varying])::text[]))),
    CONSTRAINT total_paid_check CHECK ((total_paid >= (0)::numeric))
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(50) NOT NULL,
    password_hash character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    email character varying(255),
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    coin_balance numeric(12,2) DEFAULT 0.00 NOT NULL,
    role character varying(50) DEFAULT 'user'::character varying NOT NULL,
    CONSTRAINT user_role_check CHECK (((role)::text = ANY ((ARRAY['user'::character varying, 'moderator'::character varying, 'admin'::character varying, 'superadmin'::character varying])::text[]))),
    CONSTRAINT username_check CHECK (((username)::text ~ '^[A-Za-z0-9_-]+$'::text))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: item_listings item_listings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_listings
    ADD CONSTRAINT item_listings_pkey PRIMARY KEY (id);


--
-- Name: order_items order_items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_pkey PRIMARY KEY (order_item_id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: item_listings item_listings_seller_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_listings
    ADD CONSTRAINT item_listings_seller_id_fkey FOREIGN KEY (seller_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: order_items order_items_listing_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_listing_id_fkey FOREIGN KEY (listing_id) REFERENCES public.item_listings(id);


--
-- Name: order_items order_items_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- Name: order_items order_items_seller_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_seller_id_fkey FOREIGN KEY (seller_id) REFERENCES public.users(id);


--
-- Name: orders orders_buyer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_buyer_id_fkey FOREIGN KEY (buyer_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

\unrestrict 7ChqVwqI4GGDynI7nhmMT58tKqFa7kbdNWr9r7Q2sDMsKWY0JdZjTApyGUTl4RX

