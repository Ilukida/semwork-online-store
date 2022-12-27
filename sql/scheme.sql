create table file_info(
    id serial not null primary key
    , original_file_name varchar
    , storage_file_name varchar not null
    , size bigint not null
    , type varchar
);

create table users(
    id serial primary key
    , first_name varchar not null
    , last_name varchar not null
    , email varchar not null
    , password_hash varchar
    , phone_number varchar not null
    , avatar_id integer
    , foreign key(avatar_id) references file_info(id)
);

create table categories(
    id serial primary key
    , category varchar
    , picture_id integer
    , foreign key(picture_id) references file_info(id)
);

create table products(
    id serial primary key
    , name varchar
    , maker varchar
    , price decimal
    , description varchar
    , structure varchar
    , expiration_date date
    , protein integer
    , carbohydrates integer
    , fats integer
    , kilocalories integer
    , category_id integer
    , picture_id integer
    , foreign key(category_id) references categories(id)
    , foreign key(picture_id) references file_info(id)
);

create table orders(
    id serial primary key
    , user_id integer
    , city varchar
    , street varchar
    , house varchar
    , apartment varchar
    , card_owner varchar
    , card_number varchar
    , expiration varchar
    , cvv varchar
    , amount integer
    , foreign key(user_id) references users(id)
);

create table liked_products(
    user_id integer
    , product_id integer
    , foreign key(user_id) references users(id)
    , foreign key(product_id) references products(id)
);

create table products_orders(
    product_id integer
    , order_id integer
    , foreign key(product_id) references products(id)
    , foreign key(order_id) references orders(id)
);

create table basket(
    user_id integer
    , product_id integer
    , foreign key(user_id) references users(id)
    , foreign key(product_id) references products(id)
);

create table support(
    id serial primary key
    , problem varchar
    , user_id integer
    , foreign key(user_id) references users(id)
);
