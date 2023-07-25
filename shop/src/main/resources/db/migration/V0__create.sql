
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table costumer (id uuid not null default uuid_generate_v4(), email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), primary key (id));
create table location (id uuid not null default uuid_generate_v4(), city varchar(255), country varchar(255), county varchar(255), name varchar(255), street_address varchar(255), primary key (id));
create table order_detail (quantity integer, placed_order_id uuid not null, product_id uuid not null, shipped_from_id uuid, primary key (placed_order_id, product_id));
create table placed_order (created_at timestamp(6), costumer_id uuid, id uuid not null default uuid_generate_v4(), city varchar(255), country varchar(255), county varchar(255), street_address varchar(255), primary key (id));
create table product (price numeric(38,2), weight float(53), id uuid not null default uuid_generate_v4(), product_category_id uuid, description varchar(255), image_url varchar(255), name varchar(255), primary key (id));
create table product_category (id uuid not null default uuid_generate_v4(), category_description varchar(255), category_name varchar(255), primary key (id));
create table stock (quantity integer, location_id uuid not null, product_id uuid not null, primary key (location_id, product_id));
-- default uuid_generate_v4()