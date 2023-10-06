

CREATE TABLE config (
id INT NOT NULL AUTO_INCREMENT,
dark_mode TINYINT NULL,
notification_email TINYINT NULL,
notification_browser TINYINT NULL,
notification_news TINYINT NULL,
PRIMARY KEY (id)
);

CREATE TABLE address (
    id INT NOT NULL AUTO_INCREMENT,
    streetName VARCHAR(30) NULL,
    addressnumber INT NULL,
    city VARCHAR(60) NULL,
    neighborhood VARCHAR(50) NULL,
    postalcode VARCHAR(20) NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE myuser (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(30) NULL,
    password VARCHAR(30) NULL,
    email VARCHAR(50) NULL,
    rule VARCHAR(10) NULL,
    config_id INT NULL,
    address_id INT NULL,
    PRIMARY KEY (id),
    INDEX fk_myuser_config1_idx (config_id ASC),
    INDEX fk_myuser_address1_idx (address_id ASC),
    CONSTRAINT fk_myuser_config1
    FOREIGN KEY (config_id)
    REFERENCES config (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_myuser_address1
    FOREIGN KEY (address_id)
    REFERENCES address (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );



CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT,
    productname VARCHAR(45) NULL,
    brand VARCHAR(20) NULL,
    location VARCHAR(20) NULL,
    batch VARCHAR(45) NULL,
    due_date DATE NULL,
    factory_date DATE NULL,
    product_type VARCHAR(45) NULL,
    myuser_id INT NULL,
    PRIMARY KEY (id),
    INDEX fk_products_myuser1_idx (myuser_id ASC),
    CONSTRAINT fk_products_myuser1
    FOREIGN KEY (myuser_id)
    REFERENCES myuser (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );
