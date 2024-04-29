drop database shopapp;
create database shopapp;
use shopapp;

CREATE TABLE roles
(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (100) UNIQUE NOT NULL
);

CREATE TABLE users
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    password     VARCHAR(50) NOT NULL,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME,
    is_active    TINYINT(1) DEFAULT 1,
    role_id      INT         NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE tokens
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    token           VARCHAR(255) UNIQUE NOT NULL,
    token_type      VARCHAR(100)        NOT NULL,
    expiration_date DATETIME            NOT NULL,
    revoked         TINYINT(1) NOT NULL,
    expired         TINYINT(1) NOT NULL,
    user_id         INT                 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_tokens_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE user_profiles
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    full_name     VARCHAR(100) NOT NULL,
    address       VARCHAR(200) DEFAULT '',
    date_of_birth DATE         NOT NULL,
    user_id       INT          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_profiles_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE user_avatars
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    user_profile_id INT NOT NULL,
    FOREIGN KEY (user_profile_id) REFERENCES user_profiles (id),
    CONSTRAINT fk_user_avatars_user_profile_id FOREIGN KEY (user_profile_id) REFERENCES user_profiles (id) ON DELETE CASCADE,
    image_data      blob
);

CREATE TABLE notifications
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    notification_type VARCHAR(100) NOT NULL,
    content           TEXT,
    sender_id         INT          NOT NULL,
    receiver_id       INT          NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES users (id),
    FOREIGN KEY (receiver_id) REFERENCES users (id),
    is_read           TINYINT(1) DEFAULT 0
);

CREATE TABLE categories
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL UNIQUE,
    is_active TINYINT(1) DEFAULT 1
);

CREATE TABLE category_thumbnails
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    category_id INT NOT NULL,
    CONSTRAINT fk_category_thumbnails_category_id FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE,
    image_data  blob
);

CREATE TABLE suppliers
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20)  DEFAULT '',
    address      VARCHAR(100) DEFAULT '',
    is_active    TINYINT(1) DEFAULT 1
);

CREATE TABLE supplier_logos
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id),
    CONSTRAINT fk_supplier_logos_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers (id) ON DELETE CASCADE,
    image_data  blob
);

CREATE TABLE supplier_category_associations
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE products
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(100) NOT NULL,
    stock_quantity INT          NOT NULL DEFAULT 0,
    description    LONGTEXT,
    created_at     DATETIME              DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME,
    category_id    INT,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE product_thumbnails
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_product_thumbnails_product_id FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    image_data blob
);

CREATE TABLE supplier_product_associations
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    price       FLOAT NOT NULL CHECK ( price >= 0 ),
    quantity    INT   NOT NULL,
    supplier_id INT   NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id),
    product_id  INT   NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE supplier_product_images
(
    id                              INT PRIMARY KEY AUTO_INCREMENT,
    supplier_product_association_id INT NOT NULL,
    FOREIGN KEY (supplier_product_association_id) REFERENCES supplier_product_associations (id),
    CONSTRAINT fk_supplier_product_images_supplier_product_association_id FOREIGN KEY (supplier_product_association_id) REFERENCES supplier_product_associations (id) ON DELETE CASCADE,
    image_data                      blob
);

CREATE TABLE orders
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status     ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled'),
    is_active  TINYINT(1) DEFAULT 1
);

CREATE TABLE order_infos
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    order_id       INT          NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_order_infos_order_id FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    customer_name  VARCHAR(100) NOT NULL,
    email          VARCHAR(100) DEFAULT '',
    phone_number   VARCHAR(20)  NOT NULL,
    address        VARCHAR(200) NOT NULL,
    note           VARCHAR(100) DEFAULT '',
    total_money    FLOAT CHECK (total_money >= 0),
    payment_method VARCHAR(100)
);

CREATE TABLE order_shipping
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    order_info_id    INT NOT NULL,
    FOREIGN KEY (order_info_id) REFERENCES order_infos (id),
    CONSTRAINT fk_order_shipping_order_info_id FOREIGN KEY (order_info_id) REFERENCES order_infos (id) ON DELETE CASCADE,
    shipping_method  VARCHAR(100) DEFAULT '',
    shipping_address VARCHAR(200) DEFAULT '',
    shipping_date    DATE,
    tracking_number  VARCHAR(100) DEFAULT ''
);

CREATE TABLE order_products
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    order_id   INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    product_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id),
    quantity   INT NOT NULL,
    price      FLOAT CHECK (price >= 0)
);


INSERT INTO categories (name)
VALUES ('Electronics'),
       ('Clothing'),
       ('Books'),
       ('Home & Kitchen'),
       ('Health & Beauty'),
       ('Sports & Outdoors'),
       ('Toys & Games'),
       ('Automotive'),
       ('Furniture'),
       ('Jewelry');
INSERT INTO suppliers (name, phone_number, address, is_active)
VALUES ('TechWorld', '123-456-7890', '123 Main St, City, Country', 1),
       ('FashionHub', '987-654-3210', '456 Oak Ave, Town, Country', 1),
       ('BookEmporium', '555-555-5555', '789 Elm St, Village, Country', 1),
       ('HomeMart', '111-222-3333', '321 Maple St, Suburb, Country', 1),
       ('BeautyBoutique', '999-999-9999', '567 Pine St, Hamlet, Country', 1),
       ('SportsZone', '777-777-7777', '890 Cedar St, Rural, Country', 1),
       ('ToyLand', '444-444-4444', '234 Birch St, Outskirts, Country', 1),
       ('AutoParts', '666-666-6666', '678 Walnut St, Countryside, Country', 1),
       ('FurniturePalace', '222-333-4444', '987 Chestnut St, Farmland, Country', 1),
       ('JewelMasters', '888-888-8888', '543 Spruce St, Wilderness, Country', 1);
