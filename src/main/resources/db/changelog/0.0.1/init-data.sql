-- liquibase formatted sql

-- changeset Nyasha_Chirinda:1675077713535-4
INSERT INTO drones (id, serial_number, model, weight_limit, battery_capacity, state)
VALUES ('f8bd222b-a18b-43d9-9abb-c4ca650a9d4d', 'LW007', 0, 79.0, 100.0, 0),
       ('86780c27-1cab-407a-944a-2d61c20638b2', 'MW006', 1, 164.0, 100.0, 0),
       ('a61139da-bf49-436c-9c37-86f7c3c694b3', 'CW004', 2, 290.0, 100.0, 0),
       ('81c03d6c-04be-43de-accf-477d7ad08250', 'HW009', 3, 380.0, 100.0, 0);

-- changeset Nyasha_Chirinda:1675077713535-5
INSERT INTO medications (id, name, weight, code, image)
VALUES ('377742c8-eb30-4d17-9744-e823cc7340cd', 'Adcodol', 500.0 , 'MD0056', 'image/location'),
       ('02b0b316-214e-43a2-8cb0-4a6f0f4d911c', 'Meizol', 100.0 , 'MD0029', 'image/location'),
       ('f851c7f3-414b-4ff7-afc5-a59c5ce740b8', 'Nefariom', 250.0 , 'MD0077', 'image/location'),
       ('6a63c396-5009-447a-aeb0-0356ada4262a', 'Interlym', 50.0 , 'MD0021', 'image/location');


