DELETE FROM ADDRESS_TYPES;
ALTER TABLE ADDRESS_TYPES AUTO_INCREMENT = 1;
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Home');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Billing');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Office');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Primary');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Shipping');