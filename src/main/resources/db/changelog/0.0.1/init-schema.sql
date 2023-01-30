-- liquibase formatted sql

-- changeset Nyasha_Chirinda:1675077713535-1
CREATE TABLE drones
(
    id                UUID                                   NOT NULL,
    created_date_time TIMESTAMP with time zone DEFAULT NOW() NOT NULL,
    updated_date_time TIMESTAMP with time zone DEFAULT NOW() NOT NULL,
    serial_number     VARCHAR(100)                           NOT NULL,
    model             INT                                    NOT NULL,
    weight_limit      INT                                    NOT NULL,
    battery_capacity  DOUBLE                                 NOT NULL,
    state             INT                                    NOT NULL,
    CONSTRAINT pk_drones PRIMARY KEY (id)
);

-- changeset Nyasha_Chirinda:1675077713535-2
CREATE TABLE medications
(
    id                UUID                                   NOT NULL,
    created_date_time TIMESTAMP with time zone DEFAULT NOW() NOT NULL,
    updated_date_time TIMESTAMP with time zone DEFAULT NOW() NOT NULL,
    name              VARCHAR(255)                           NOT NULL,
    weight            DOUBLE                                 NOT NULL,
    code              VARCHAR(255),
    image             VARCHAR(255),
    CONSTRAINT pk_medications PRIMARY KEY (id)
);

-- changeset Nyasha_Chirinda:1675077713535-3
ALTER TABLE drones
    ADD CONSTRAINT uc_drones_serialnumber UNIQUE (serial_number);

