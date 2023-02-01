-- liquibase formatted sql

-- changeset Nyasha_Chirinda:1675077713535-10
INSERT INTO drones (id, serial_number, model, weight_limit, battery_capacity, state)
VALUES ('3ecc473a-8a36-414a-a772-251fa44693e7', 'LW007', 0, 79.0, 100, 0),
       ('bc3c843b-b022-405f-b177-dd3fddda0a2d', 'MW006', 1, 164.0, 100, 0),
       ('9125218d-5a37-46b1-8d53-e01ab0013a23', 'CW004', 2, 290.0, 100, 0),
       ('d26d77ab-a27e-4274-b6cb-00d3b41d567c', 'LW0090', 3, 79.0, 100, 0);

-- changeset Nyasha_Chirinda:1675077713535-11
INSERT INTO medication_images (id, name, type, bytes)
VALUES ('9485051b-87f1-4677-a2ee-1993a320cda6', 'adcodol_image', 'jpeg', 'image/bytes'),
       ('bf2cbd5d-7e0f-4327-a6fa-dd453af623f0', 'meizol_image', 'png', 'image/bytes'),
       ('2bc09d6b-f01d-458f-bf9d-bc1d31e8012f', 'nefariom_iamge', 'png', 'image/bbytes'),
       ('6a63c396-5009-447a-aeb0-0356ada4262a', 'interlym_image', 'jpg', 'image/bytes');

-- changeset Nyasha_Chirinda:1675077713535-12
INSERT INTO medications (id, name, weight, code, image_id)
VALUES ('377742c8-eb30-4d17-9744-e823cc7340cd', 'Adcodol', 500.0, 'MD0056', '9485051b-87f1-4677-a2ee-1993a320cda6'),
       ('02b0b316-214e-43a2-8cb0-4a6f0f4d911c', 'Meizol', 100.0, 'MD0029', 'bf2cbd5d-7e0f-4327-a6fa-dd453af623f0'),
       ('f851c7f3-414b-4ff7-afc5-a59c5ce740b8', 'Nefariom', 250.0, 'MD0077', '2bc09d6b-f01d-458f-bf9d-bc1d31e8012f'),
       ('6a63c396-5009-447a-aeb0-0356ada4262a', 'Interlym', 50.0, 'MD0021', '6a63c396-5009-447a-aeb0-0356ada4262a');


