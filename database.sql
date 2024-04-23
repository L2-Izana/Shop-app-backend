CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE
);

CREATE TABLE tokens(
    id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(100) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(20) NOT NULL DEFAULT '',
    password VARCHAR(50) NOT NULL DEFAULT '',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    is_active TINYINT(1) DEFAULT 1,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE user_profiles(
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    date_of_birth DATE,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_profiles_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE user_avatars(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_profile_id INT,
    FOREIGN KEY (user_profile_id) REFERENCES user_profiles(id),
    CONSTRAINT fk_user_avatars_user_profile_id FOREIGN KEY (user_profile_id) REFERENCES user_profiles(id) ON DELETE CASCADE,
    image_url VARCHAR(100)
);


CREATE TABLE social_accounts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(20) NOT NULL COMMENT 'Provider name',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL COMMENT 'Provided account email',
    name VARCHAR(100) NOT NULL COMMENT 'Username',
    user_profile_id INT,
    FOREIGN KEY (user_profile_id) REFERENCES user_profiles(id),
    CONSTRAINT fk_social_accounts_user_profile_id FOREIGN KEY (user_profile_id) REFERENCES user_profiles(id) ON DELETE CASCADE
);

CREATE TABLE notifications(
    id INT PRIMARY KEY AUTO_INCREMENT,
    notification_type VARCHAR(100) DEFAULT '',
    content LONGTEXT,
    sender_id INT,
    receiver_id INT,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id),
    is_read TINYINT(1) DEFAULT 0
);

CREATE TABLE categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '',
    is_active TINYINT(1)
);

CREATE TABLE suppliers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '' UNIQUE,
    phone_number VARCHAR(20) UNIQUE DEFAULT '',
    address VARCHAR(100) DEFAULT '',
    is_active TINYINT(1)
);

CREATE TABLE supplier_logos(
    id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    image_url VARCHAR(100)
);

CREATE TABLE supplier_category_associations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_id INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(350) COMMENT 'Product name',
    stock_quantity INT DEFAULT 0,
    thumbnail VARCHAR(300) DEFAULT '',
    description LONGTEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE supplier_product_associations(
    id INT PRIMARY KEY AUTO_INCREMENT,
    price FLOAT NOT NULL CHECK ( price >= 0 ),
    quantity INT NOT NULL,
    supplier_id INT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE supplier_product_images(
    id INT PRIMARY KEY AUTO_INCREMENT,
    supplier_product_association_id INT NOT NULL,
    FOREIGN KEY (supplier_product_association_id) REFERENCES supplier_product_associations(id),
    CONSTRAINT fk_supplier_product_images_supplier_product_association_id FOREIGN KEY (supplier_product_association_id) REFERENCES supplier_product_associations(id) ON DELETE CASCADE,
    image_url VARCHAR(300)
);

CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled'),
    is_active TINYINT(1)
);

CREATE TABLE order_infos(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_order_infos_order_id FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    customer_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    total_money FLOAT CHECK(total_money >= 0),
    payment_method VARCHAR(100)
);

CREATE TABLE order_shipping(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_info_id INT,
    FOREIGN KEY (order_info_id) REFERENCES order_infos(id),
    shipping_method VARCHAR(100),
    shipping_address VARCHAR(200),
    shipping_date DATE,
    tracking_number VARCHAR(100)
);

CREATE TABLE order_products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    product_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    quantity INT,
    price FLOAT CHECK(price >= 0)
);


