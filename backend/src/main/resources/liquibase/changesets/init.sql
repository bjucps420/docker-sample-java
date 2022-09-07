-- liquibase formatted sql

-- changeset ethanmcgee:1
-- validCheckSum: ANY
CREATE TABLE todo (
    id bigserial PRIMARY KEY,
    title varchar(255) not null
);

-- changeset ethanmcgee:2
-- validCheckSum: ANY
insert into todo(title) values ('Test');