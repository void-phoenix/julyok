CREATE TABLE story
(id SERIAL PRIMARY KEY,
 name VARCHAR(100),
 description text,
 created_at timestamp,
 preview_photo varchar);


CREATE TABLE element
(id SERIAL PRIMARY KEY,
 story_id bigint NOT NULL,
 content text,
 position integer,
 type varchar(10));