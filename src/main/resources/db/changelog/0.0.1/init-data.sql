-- liquibase formatted sql

-- changeset Nyasha_Chirinda:1675077713535-4
INSERT INTO drones (id, serial_number, model, weight_limit, battery_capacity, state)
VALUES ('3ecc473a-8a36-414a-a772-251fa44693e7', 'LW007', 0, 79.0, 100, 0),
       ('bc3c843b-b022-405f-b177-dd3fddda0a2d', 'MW006', 1, 164.0, 100, 0),
       ('9125218d-5a37-46b1-8d53-e01ab0013a23', 'CW004', 2, 290.0, 100, 0),
       ('d26d77ab-a27e-4274-b6cb-00d3b41d567c', 'LW0090', 3, 79.0, 100, 0);

-- changeset Nyasha_Chirinda:1675077713535-5
INSERT INTO medications (id, name, weight, code, image)
VALUES ('377742c8-eb30-4d17-9744-e823cc7340cd', 'Adcodol', 500.0 , 'MD0056', 'image/location'),
       ('02b0b316-214e-43a2-8cb0-4a6f0f4d911c', 'Meizol', 100.0 , 'MD0029', 'image/location'),
       ('f851c7f3-414b-4ff7-afc5-a59c5ce740b8', 'Nefariom', 250.0 , 'MD0077', 'image/location'),
       ('6a63c396-5009-447a-aeb0-0356ada4262a', 'Interlym', 50.0 , 'MD0021', 'image/location');


