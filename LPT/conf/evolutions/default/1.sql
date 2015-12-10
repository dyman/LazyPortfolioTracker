# --- !Ups
CREATE TABLE "user"
(
  id serial NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255),
  name character varying(255),
  isadmin boolean,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE "country"
(
  id smallint NOT NULL,
  code character varying(10),
  allowed boolean,
  currency character varying(3),
  name character varying(255),
  CONSTRAINT country_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

INSERT INTO country VALUES (1, 'HU', NULL, 'HUF', 'Hungary');
INSERT INTO country VALUES (2, 'DE', NULL, 'EUR', 'Germany');

# --- !Downs
DROP TABLE "user";
DROP TABLE "country";