----------------User Information--------------
CREATE TABLE IF NOT EXISTS user (
    user_id INTEGER PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS shipping_address (
    address_id INTEGER PRIMARY KEY,
    user_id INTEGER,
    address_line1 TEXT,
    address_line2 TEXT,
    city TEXT,
    "state" TEXT,
    zip_code TEXT,
    country TEXT
);

CREATE TABLE IF NOT EXISTS user_listings (
    listing_id INTEGER PRIMARY KEY,
    user_id INTEGER,
    manga_id INTEGER,
    quantity INTEGER,
    price DOUBLE PRECISION,
    creation_date TIMESTAMP,
    active BOOLEAN
);
-------------Product Details-------------
CREATE TABLE IF NOT EXISTS manga (
    manga_id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    score DOUBLE PRECISION,
    "status" TEXT NOT NULL,
    volumes INTEGER NOT NULL,
    chapters INTEGER NOT NULL,
    "start_date" DATE,
    end_date DATE,
    sfw BOOLEAN NOT NULL,
    genres TEXT[],
    themes TEXT[],
    demographics TEXT[],
    authors JSONB,
    synopsis TEXT,
    main_picture TEXT,
    title_japanese TEXT,
    title_synonyms TEXT[]
);

CREATE TABLE IF NOT EXISTS discount (
    discount_id INTEGER PRIMARY KEY,
    "name" VARCHAR(50),
    "description" TEXT,
    discount_percent DOUBLE PRECISION,
    "start_date" DATE,
    end_date DATE
);

------------Order Details------------
CREATE TABLE IF NOT EXISTS cart (
    order_id INTEGER PRIMARY KEY,
    buyer_id INTEGER,
    discount_id INTEGER,
    listing_id INTEGER,
    item_quantity INTEGER,
    listing_price DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS order_history (
    order_id INTEGER PRIMARY KEY,
    buyer_id INTEGER,
    address_id INTEGER,
    listing_id INTEGER,
    discount_id INTEGER,
    item_quantity INTEGER,
    listing_price DOUBLE PRECISION,
    order_date TIMESTAMP
);

--\COPY is an SQL meta-command so this line must be run directly through DB command line. Change the file location to where you have manga.csv located.
\COPY manga FROM 'C:\Users\adanr\OneDrive\Documents\Classes\SE\manga.csv' WITH(FORMAT csv, HEADER);