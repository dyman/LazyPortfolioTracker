# --- !Ups
CREATE TABLE currency
(
  id character varying(3) NOT NULL,
  name character varying(255),
  CONSTRAINT currency_pkey PRIMARY KEY (id)
);


INSERT INTO currency (id, name) VALUES ('HUF', 'Forint');
INSERT INTO currency (id, name) VALUES ('USD', 'Dollar');
INSERT INTO currency (id, name) VALUES ('EUR', 'Euro');
INSERT INTO currency (id, name) VALUES ('JPY', 'Yen');


CREATE TABLE "country"
(
  id smallint NOT NULL,
  code character varying(10),
  currency character varying(3),
  name character varying(255),
  CONSTRAINT country_pkey PRIMARY KEY (id),
  CONSTRAINT currency_id FOREIGN KEY (currency)
      REFERENCES currency (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE "user"
(
  id serial NOT NULL,
  email character varying(255) NOT NULL,
  lastactivity date NOT NULL,
  isadmin boolean,
  countryid smallint not null,
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT country_id FOREIGN KEY (countryid)
      REFERENCES country (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

# --- !Downs
DROP TABLE "user";
DROP TABLE "country";
DROP TABLE "currency";
