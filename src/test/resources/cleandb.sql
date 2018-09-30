DELETE FROM COUNTRIES;
ALTER TABLE COUNTRIES AUTO_INCREMENT = 1;
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('US', 'United States');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('CO', 'Colombia');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('CA', 'Canada');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('ES', 'Spain');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('FR', 'France');
DELETE FROM ADDRESS_TYPES;
ALTER TABLE ADDRESS_TYPES AUTO_INCREMENT = 1;
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Home');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Billing');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Office');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Primary');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Shipping');
