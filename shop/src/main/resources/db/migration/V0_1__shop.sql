
DROP TABLE IF EXISTS customers;

CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  email_address VARCHAR(250) NOT NULL
);

INSERT INTO customer (first_name,last_name,username,password,email_address)
VALUES('ceva','cec','sas','sde','sds');

DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250)

);

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(250) NOT NULL
    );

DROP TABLE IF EXISTS product;
CREATE TABLE product(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(250),
    price NUMERIC(10,2),
    weight NUMERIC(10,2),
    image_url VARCHAR(255),
    category INT,
    supplier INT,
    FOREIGN KEY (category) REFERENCES product_category(id),
    FOREIGN KEY (supplier) REFERENCES supplier(id)
);

DROP TABLE IF EXISTS location;
CREATE TABLE location(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    country VARCHAR(255) NOT NULL,
    city  VARCHAR(255) NOT NULL,
    street_address VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS shop_order;
CREATE TABLE shop_order(
  id INT AUTO_INCREMENT PRIMARY KEY,
  shipped_from INT,
  customer INT,
  creation_time TIMESTAMP,
  country VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  street_address VARCHAR(255) NOT NULL,
  FOREIGN KEY (shipped_from) REFERENCES location(id),
  FOREIGN KEY (customer) REFERENCES customer(id)
);

DROP TABLE IF EXISTS order_detail;
CREATE TABLE order_detail(
  id INT AUTO_INCREMENT PRIMARY KEY,
  shop_order INT,
  product INT,
  quantity INT NOT NULL,
  FOREIGN KEY (shop_order) REFERENCES shop_order(id),
  FOREIGN KEY (product) REFERENCES product(id)
);

DROP TABLE IF EXISTS revenue;
CREATE TABLE revenue(
  id INT AUTO_INCREMENT PRIMARY KEY,
  location INT,
  date_of DATE NOT NULL,
  summation NUMERIC(10, 2) NOT NULL,
  FOREIGN KEY(location) REFERENCES location(id)
);

CREATE TABLE stock(
    id INT AUTO_INCREMENT PRIMARY KEY,
    product INT,
    location INT,
    quantity INT,
    FOREIGN KEY(location) REFERENCES location(id),
    FOREIGN KEY(product) REFERENCES product(id)
);







