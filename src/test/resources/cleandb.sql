DELETE FROM USERS_ROLES;
DELETE FROM PERSON_USERS;
DELETE FROM TREATMENTS_PRESCRIPTIONS;
DELETE FROM TREATMENTS;
ALTER TABLE TREATMENTS AUTO_INCREMENT = 1;
DELETE FROM PRESCRIPTIONS;
ALTER TABLE PRESCRIPTIONS AUTO_INCREMENT = 1;
DELETE FROM MEDICATIONS;
ALTER TABLE MEDICATIONS AUTO_INCREMENT = 1;
DELETE FROM VISITS;
ALTER TABLE VISITS AUTO_INCREMENT = 1;
DELETE FROM SERVICES;
ALTER TABLE SERVICES AUTO_INCREMENT = 1;
DELETE FROM PROVIDERS_SCHEDULES;
DELETE FROM ENTITY_ADDRESSES;
DELETE FROM SHIFTS_SCHEDULES;
DELETE FROM WEEK_DAYS;
ALTER TABLE WEEK_DAYS AUTO_INCREMENT = 1;
DELETE FROM SHIFTS;
ALTER TABLE SHIFTS AUTO_INCREMENT = 1;
DELETE FROM PATIENTS;
DELETE FROM PROVIDERS;
DELETE FROM PERSONS;
DELETE FROM ORGANIZATIONS;
ALTER TABLE PERSONS AUTO_INCREMENT = 1;
DELETE FROM ENTITIES;
ALTER TABLE ENTITIES AUTO_INCREMENT = 1;
DELETE FROM PERSON_TYPES;
ALTER TABLE PERSON_TYPES AUTO_INCREMENT = 1;
DELETE FROM ADDRESSES;
ALTER TABLE ADDRESSES AUTO_INCREMENT = 1;
DELETE FROM STATE_PROVINCES;
ALTER TABLE STATE_PROVINCES AUTO_INCREMENT = 1;
DELETE FROM COUNTRIES;
ALTER TABLE COUNTRIES AUTO_INCREMENT = 1;
DELETE FROM ADDRESS_TYPES;
ALTER TABLE ADDRESS_TYPES AUTO_INCREMENT = 1;
DELETE FROM USERS;
ALTER TABLE USERS AUTO_INCREMENT = 1;
DELETE FROM ROLES;
ALTER TABLE ROLES AUTO_INCREMENT = 1;
INSERT INTO ROLES (RoleName, Description) VALUES('Administrator', 'System administrator');
INSERT INTO ROLES (RoleName, Description) VALUES('User', 'System user');
INSERT INTO PERSON_TYPES(PersonTypeName) VALUES("Employee");
INSERT INTO PERSON_TYPES(PersonTypeName) VALUES("Provider");
INSERT INTO PERSON_TYPES(PersonTypeName) VALUES("Patient");
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('US', 'United States');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('CO', 'Colombia');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('CA', 'Canada');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('ES', 'Spain');
INSERT INTO COUNTRIES(CountryCode, Name) VALUES ('FR', 'France');
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('AB','Alberta',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('AK','Alaska',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('AL','Alabama',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('AR','Arkansas',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('AZ','Arizona',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('BC','British Columbia',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('CA','California',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('CO','Colorado',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('CT','Connecticut',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('DC','District of Columbia',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('DE','Delaware',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('FL','Florida',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('GA','Georgia',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('GU','Guam',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('HI','Hawaii',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('IA','Iowa',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('ID','Idaho',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('IL','Illinois',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('IN','Indiana',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('KS','Kansas',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('KY','Kentucky',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('LA','Louisiana',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('LB','Labrador',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MA','Massachusetts',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MB','Manitoba',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MD','Maryland',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('ME','Maine',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MI','Michigan',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MN','Minnesota',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MO','Missouri',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MS','Mississippi',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('MT','Montana',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NB','Brunswick',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NC','North Carolina',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('ND','North Dakota',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NE','Nebraska',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NF','Newfoundland',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NH','New Hampshire',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NJ','New Jersey',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NM','New Mexico',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NS','Nova Scotia',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NT','Northwest Territories',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NV','Nevada',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('NY','New York',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('OH','Ohio',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('OK','Oklahoma',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('ON','Ontario',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('OR','Oregon',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('PA','Pennsylvania',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('PE','Prince Edward Island',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('PR','Puerto Rico',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('QC','Quebec',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('RI','Rhode Island',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('SC','South Carolina',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('SD','South Dakota',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('SK','Saskatchewan',3);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('TN','Tennessee',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('TX','Texas',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('UT','Utah',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('VA','Virginia',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('VT','Vermont',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('WA','Washington',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('WI','Wisconsin',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('WV','West Virginia',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('WY','Wyoming',1);
INSERT INTO STATE_PROVINCES (StateProvinceCode, Name, CountryID) VALUES('YT','Yukon Territory',3);
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Home');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Billing');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Office');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Shipping');
INSERT INTO ADDRESS_TYPES(Name) VALUES ('Medical Center');
INSERT INTO ORGANIZATION_TYPES(Name) VALUES ('Insurance');
INSERT INTO ORGANIZATION_TYPES(Name) VALUES ('Clinic');
INSERT INTO ORGANIZATION_TYPES(Name) VALUES ('Hospital');
INSERT INTO ENTITIES (CreatedDate) VALUES (CURRENT_TIMESTAMP);
INSERT INTO ORGANIZATIONS(OrganizationID, Name, OrganizationTypeID) VALUE (1, 'Group Health Cooperative', 1);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('675 W. Washington Ave.', null, 'Madison', 63, 5, 53703, 'Capitol Clinic');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (1,1);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('3051 Cahill Main', null, 'Fitchburg', 63, 5, 53711, 'Hatchery Hill Clinic');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (1,2);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('5249 E. Terrace Dr.', null, 'Madison', 63, 5, 53718, 'East Clinic');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (1,3);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('1705 Hoffman St.', null, 'Madison', 63, 5, 53704, 'Madison College Community Clinic');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (1,4);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('8054 Watts Rd.', null, 'Madison', 63, 5, 53719, 'Princeton Club West PT/OT Clinic');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (1,5);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('8202 Excelsior Dr.', null, 'Madison', 63, 5, 53717, 'Sauk Trails Clinic');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (1,6);
INSERT INTO ENTITIES (CreatedDate) VALUES (CURRENT_TIMESTAMP);
INSERT INTO ORGANIZATIONS(OrganizationID, Name, OrganizationTypeID) VALUE (2, 'United Health Care', 1);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('2135 Silvernail Rd', null, 'Pewaukee', 63, 5, 53072, 'Minuteclinic Walk In Clinic Cvs Pharm');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (2,7);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('221 E Sunset Dr', null, 'Waukesha', 63, 5, 53189, 'Walgreens Healthcare Clinic');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (2,8);
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode, Name) VALUES ('1305 Randall Rd', null, 'Crystal Lake', 18, 5, 60014, 'Minuteclinic Illinois');
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (2,9);
INSERT INTO ENTITIES (CreatedDate) VALUES (CURRENT_TIMESTAMP);
INSERT INTO PERSONS (PersonID, PersonTypeID, Title, FirstName, MiddleName, LastName, LastName2, Suffix) VALUES(3, 2, null, 'Yesid', null, 'Perea', 'Martinez', null);
INSERT INTO PROVIDERS(ProviderID, NPI) VALUES(3, '3333333');
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode) VALUES ('1701 Wright Street', 'Floor 3', 'Madison', 63, 1, 53704);
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (3,10);
INSERT INTO ENTITIES (CreatedDate) VALUES (CURRENT_TIMESTAMP);
INSERT INTO PERSONS (PersonID, PersonTypeID, Title, FirstName, MiddleName, LastName, LastName2, Suffix) VALUES(4, 2, null, 'Claudia', 'Patricia', 'Carabali', 'Chacon', null);
INSERT INTO PATIENTS(PatientID, BirthDate, Gender, CompanyID, SubscriberCode, IsPrimarySubscriber) VALUES(4, '1974-3-18', 'F', 1, "4444444", 1);
INSERT INTO ENTITIES (CreatedDate) VALUES (CURRENT_TIMESTAMP);
INSERT INTO PERSONS (PersonID, PersonTypeID, Title, FirstName, MiddleName, LastName, LastName2, Suffix) VALUES(5, 3, null, 'John', null, 'Smith', null, null);
INSERT INTO PATIENTS(PatientID, BirthDate, Gender, CompanyID, SubscriberCode, IsPrimarySubscriber) VALUES(5, '1966-6-6', 'M', 1, "5555555", 1);
INSERT INTO PROVIDERS(ProviderID, NPI) VALUES(4, '111111');
INSERT INTO ADDRESSES(AddressLine1, AddressLine2, City, StateProvinceID, AddressTypeID, PostalCode) VALUES ('3591 Anderson Street', null, 'Madison', 63, 1, 53704);
INSERT INTO ENTITY_ADDRESSES(EntityID, AddressID) VALUE (4,11);
INSERT INTO USERS(Username, Email, Password ) VALUES ('admin', 'admin@aol.com', '1234');
INSERT INTO USERS(Username, Email, Password ) VALUES ('yesper', 'yesper@aol.com', '1234');
INSERT INTO USERS(Username, Email, Password ) VALUES ('clacar', 'clacar@aol.com', '1234');
INSERT INTO USERS(Username, Email, Password ) VALUES ('jsmith', 'jsmith@aol.com', '1234');
INSERT INTO USERS_ROLES(UserID, RoleID) VALUES (1,1);
INSERT INTO USERS_ROLES(UserID, RoleID) VALUES (2,2);
INSERT INTO USERS_ROLES(UserID, RoleID) VALUES (3,2);
INSERT INTO USERS_ROLES(UserID, RoleID) VALUES (4,2);
INSERT INTO PERSON_USERS (PersonID, PersonTypeID, UserID) VALUES (3, 2, 2);
INSERT INTO PERSON_USERS (PersonID, PersonTypeID, UserID) VALUES (4, 2, 3);
INSERT INTO PERSON_USERS (PersonID, PersonTypeID, UserID) VALUES (5, 3, 4);
INSERT INTO SERVICES(Name) VALUES ('Medical');
INSERT INTO SERVICES(Name) VALUES ('Therapy');
INSERT INTO SERVICES(Name) VALUES ('Allergy');
INSERT INTO SERVICES(Name) VALUES ('Psychology');
INSERT INTO SERVICES(Name) VALUES ('Psychiatry');
INSERT INTO WEEK_DAYS(WeekDayCode, Name) VALUES('M', 'Monday');
INSERT INTO WEEK_DAYS(WeekDayCode, Name) VALUES('T', 'Tuesday');
INSERT INTO WEEK_DAYS(WeekDayCode, Name) VALUES('W', 'Wednesday');
INSERT INTO WEEK_DAYS(WeekDayCode, Name) VALUES('R', 'Thursday');
INSERT INTO WEEK_DAYS(WeekDayCode, Name) VALUES('F', 'Friday');
INSERT INTO WEEK_DAYS(WeekDayCode, Name) VALUES('S', 'Saturday');
INSERT INTO WEEK_DAYS(WeekDayCode, Name) VALUES('U', 'Sunday');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Morning Spring 2018', 'Weekday Morning Spring 2018', '2018-03-01', '2018-05-31');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Afternoon Spring 2018', 'Weekday Afternoon Spring 2018', '2018-03-01', '2018-05-31');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekend Spring 2018', 'Weekend Spring 2018', '2018-03-01', '2018-05-31');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Morning Summer 2018', 'Weekday Morning Summer 2018', '2018-06-01', '2018-08-31');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Afternoon Summer 2018', 'Weekday Afternoon Summer 2018', '2018-06-01', '2018-08-31');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekend Summer 2018', 'Weekend Summer 2018', '2018-06-01', '2018-08-31');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Morning Fall 2018', 'Weekday Morning Fall 2018', '2018-09-01', '2018-11-30');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Afternoon Fall 2018', 'Weekday Afternoon Fall 2018', '2018-09-01', '2018-11-30');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekend Fall 2018', 'Weekend Fall 2018', '2018-09-01', '2018-11-30');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Morning Winter 2018', 'Weekday Morning Winter 2018', '2018-12-01', '2019-02-28');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekday Afternoon Winter 2018', 'Weekday Afternoon Winter 2018', '2018-12-01', '2019-02-28');
INSERT INTO SHIFTS(Name, Description, StartDate, EndDate) VALUES('Weekend Winter 2018', 'Weekend Winter 2018', '2018-12-01', '2019-02-28');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (1, 1, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (1, 2, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (1, 3, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (1, 4, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (1, 5, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (2, 1, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (2, 2, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (2, 3, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (2, 4, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (2, 5, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (3, 6, '09:00', '13:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (4, 1, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (4, 2, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (4, 3, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (4, 4, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (4, 5, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (5, 1, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (5, 2, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (5, 3, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (5, 4, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (5, 5, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (6, 6, '09:00', '13:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (7, 1, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (7, 2, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (7, 3, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (7, 4, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (7, 5, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (8, 1, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (8, 2, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (8, 3, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (8, 4, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (8, 5, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (9, 6, '09:00', '13:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (10, 1, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (10, 2, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (10, 3, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (10, 4, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (10, 5, '08:00', '12:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (11, 1, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (11, 2, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (11, 3, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (11, 4, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (11, 5, '13:00', '18:00');
INSERT INTO SHIFTS_SCHEDULES(ShiftID, WeekDayID, StartTime, EndTime) VALUES (12, 6, '09:00', '13:00');
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 1, 1, 1, 1);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 2, 2, 1, 2);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 2, 3, 1, 3);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 2, 4, 1, 4);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 1, 5, 1, 5);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 3, 6, 1, 6);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 4, 1, 1, 1);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 5, 2, 1, 2);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 5, 3, 1, 3);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 5, 4, 1, 4);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 4, 5, 1, 5);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 6, 6, 1, 6);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 7, 1, 1, 1);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 8, 2, 1, 2);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 8, 3, 1, 3);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 8, 4, 1, 4);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 7, 5, 1, 5);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 9, 6, 1, 6);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 10, 1, 1, 1);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 11, 2, 1, 2);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 11, 3, 1, 3);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 11, 4, 1, 4);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 10, 5, 1, 5);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (3, 12, 6, 1, 6);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 1, 1, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 2, 2, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 2, 3, 2, 8);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 2, 4, 2, 9);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 1, 5, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 4, 1, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 5, 2, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 5, 3, 2, 8);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 5, 4, 2, 9);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 4, 5, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 7, 1, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 8, 2, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 8, 3, 2, 8);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 8, 4, 2, 9);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 7, 5, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 10, 1, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 11, 2, 2, 7);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 11, 3, 2, 8);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 11, 4, 2, 9);
INSERT INTO PROVIDERS_SCHEDULES(ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID) VALUES (4, 10, 5, 2, 7);
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, SymptomID, SymptomName, DiagnosticID, DiagnosticName, ActualStartDate, ActualEndDate, PatientBloodPressure, PatientWeight, PatientTemperature, PatientRespiration, PatientBMI, PatientHeight, PatientPulse, PatientSymptoms, ProviderComments) VALUES(5, 3, 2, 3, 1, 3, 1, 'C', '2018-01-10 14:30', '2018-01-10 14:30', 46, 'Burning in the throat', 44, 'Inflammation of the nose and throat', '2018-01-10 14:00', '2018-01-10 14:30', 125, 125, 98.3, 16, 26.92, 4.5, 85, 'Sore throat', 'Patient has an inflammation of the nose and throat');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, SymptomID, SymptomName, DiagnosticID, DiagnosticName, ActualStartDate, ActualEndDate, PatientBloodPressure, PatientWeight, PatientTemperature, PatientRespiration, PatientBMI, PatientHeight, PatientPulse, PatientSymptoms, ProviderComments) VALUES(5, 3, 2, 3, 1, 3, 1, 'C', '2018-01-17 14:00', '2018-01-17 14:30', 10, 'Abdominal pain', 59, 'Urinary tract infection', '2018-01-17 14:00', '2018-01-17 14:30', 125, 125, 98.3, 16, 26.92, 4.5, 85, 'Abdominal pain', 'Patient has an infection');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, ActualStartDate, ActualEndDate, PatientBloodPressure, PatientWeight, PatientTemperature, PatientRespiration, PatientBMI, PatientHeight, PatientPulse, PatientSymptoms, ProviderComments) VALUES(5, 4, 2, 2, 2, 7, 1, 'D', '2018-02-13 13:00', '2018-02-13 13:30', '2018-02-13 13:00', '2018-02-13 13:30', 126, 126, 96.3, 15, 25.92, 4.5, 85, '', '');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, ActualStartDate, ActualEndDate, PatientBloodPressure, PatientWeight, PatientTemperature, PatientRespiration, PatientBMI, PatientHeight, PatientPulse, PatientSymptoms, ProviderComments) VALUES(5, 4, 2, 2, 2, 7, 1, 'D', '2018-02-20 14:00', '2018-02-20 14:30', '2018-02-20 14:00', '2018-02-20 14:30', 126, 126, 96.3, 15, 25.92, 4.5, 85, '', '');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, SymptomID, SymptomName, DiagnosticID, DiagnosticName, ActualStartDate, ActualEndDate, PatientBloodPressure, PatientWeight, PatientTemperature, PatientRespiration, PatientBMI, PatientHeight, PatientPulse, PatientSymptoms, ProviderComments) VALUES(5, 4, 2, 2, 2, 7, 1, 'C', '2018-02-27 14:00', '2018-02-27 14:30', 46, 'Burning in the throat', 44, 'Inflammation of the nose and throat', '2018-02-27 14:00', '2018-02-27 14:30', 126, 126, 96.3, 15, 25.92, 4.5, 85, 'Sore throat', 'Patient has flu');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, SymptomID, SymptomName, DiagnosticID, DiagnosticName, ActualStartDate, ActualEndDate, PatientBloodPressure, PatientWeight, PatientTemperature, PatientRespiration, PatientBMI, PatientHeight, PatientPulse, PatientSymptoms, ProviderComments) VALUES(5, 3, 5, 4, 1, 4, 1, 'D','2018-07-18 16:00', '2018-07-18 16:30', 46, 'Burning in the throat', 44, 'Inflammation of the nose and throat', '2018-07-18 16:00', '2018-07-18 16:15',120, 120, 97.3, 16, 25.92, 4.5, 82,'Ear pain', 'Patient has an infection in the ear canal');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, SymptomID, SymptomName, PatientSymptoms) VALUES(5, 3, 11, 3, 1, 3, 1, 'A', '2018-12-19 18:00', '2018-12-19 18:30', 136, 'Neck pain', 'Neck pain');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, SymptomID, SymptomName, PatientSymptoms) VALUES(5, 4, 10, 1, 2, 7, 3, 'A', '2018-12-26 9:00', '2018-12-26 9:30', 33, 'Eye redness', 'Eye redness');
INSERT INTO VISITS(PatientID, ProviderID, ShiftID, WeekDayID, OrganizationID, LocationID, ServiceID, Status, ScheduledStartDate, ScheduledEndDate, SymptomID, SymptomName, PatientSymptoms) VALUES(5, 4, 10, 5, 2, 7, 5, 'A', '2019-01-21 10:00', '2019-01-21 10:30', 52, 'Sleeplessness', 'I cannot sleep');
INSERT INTO TREATMENTS(VisitID, StartDate, EndDate, PatientComments, ProviderComments, Status, Evaluation)VALUES(1, '2018-01-01', '2018-02-01', 'I feel better now', 'Follow treatment', 'C', 4.5);
INSERT INTO TREATMENTS(VisitID, StartDate, EndDate, PatientComments, ProviderComments, Status, Evaluation)VALUES(2, '2018-01-01', '2018-02-01', 'I feel better now', 'Follow treatment', 'C', 4.5);
INSERT INTO MEDICATIONS(Name, CommonName, Description, GeneralInstructions)VALUES('Robitussin', 'Robitussin', 'Power through your day with an enhanced formula that soothes your irritated throat and provides fast and powerful relief from your worst cough, cold & flu symptoms.', '10 mL every 12 hours, not to exceed 20 mL in 24 hours.');
INSERT INTO MEDICATIONS(Name, CommonName, Description, GeneralInstructions)VALUES('Ibuprofen', 'Advil+', 'Ibuprofen is a medication in the nonsteroidal anti-inflammatory drug class that is used for treating pain, fever, and inflammation.', '800 milligrams per dose or 3200 mg per day.');
INSERT INTO MEDICATIONS(Name, CommonName, Description, GeneralInstructions)VALUES('Amoxicillin', 'Amoxicillin', 'Amoxicillin is used to treat a wide variety of bacterial infections. This medication is a penicillin-type antibiotic. It works by stopping the growth of bacteria.', 'Take this medication by mouth with or without food as directed by your doctor, usually every 8 or 12 hours.');
INSERT INTO MEDICATIONS(Name, CommonName, Description, GeneralInstructions)VALUES('Benzonatate', 'Zonatuss', 'This medication is used to treat coughs caused by the common cold and other breathing problems.', 'Take this medication by mouth with or without food, usually 3 times daily as needed or as directed by your doctor.');
INSERT INTO PRESCRIPTIONS(MedicationID, Instructions) VALUES (3, '10 mL every 12 hours, not to exceed 20 mL in 24 hours.');
INSERT INTO PRESCRIPTIONS(MedicationID, Instructions) VALUES (2, '100 mg every 12 hours, not to exceed 200 mg in 24 hours.');
INSERT INTO TREATMENTS_PRESCRIPTIONS(TreatmentID, PrescriptionID) VALUES (1,1);
INSERT INTO TREATMENTS_PRESCRIPTIONS(TreatmentID, PrescriptionID) VALUES (2,2);