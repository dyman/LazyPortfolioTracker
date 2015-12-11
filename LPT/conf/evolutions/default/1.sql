# --- !Ups
CREATE TABLE currency
(
  id character varying(3) NOT NULL,
  name character varying(255),
  CONSTRAINT currency_pkey PRIMARY KEY (id)
);


INSERT INTO currency (id, name) VALUES ('USD', 'US dollar');
INSERT INTO currency (id, name) VALUES ('JPY', 'Japanese yen');
INSERT INTO currency (id, name) VALUES ('BGN', 'Bulgarian lev');
INSERT INTO currency (id, name) VALUES ('CZK', 'Czech koruna');
INSERT INTO currency (id, name) VALUES ('DKK', 'Danish krone');
INSERT INTO currency (id, name) VALUES ('GBP', 'Pound sterling');
INSERT INTO currency (id, name) VALUES ('HUF', 'Hungarian forint');
INSERT INTO currency (id, name) VALUES ('PLN', 'Polish zloty');
INSERT INTO currency (id, name) VALUES ('RON', 'Romanian leu');
INSERT INTO currency (id, name) VALUES ('SEK', 'Swedish krona');
INSERT INTO currency (id, name) VALUES ('CHF', 'Swiss franc');
INSERT INTO currency (id, name) VALUES ('NOK', 'Norwegian krone');
INSERT INTO currency (id, name) VALUES ('HRK', 'Croatian kuna');
INSERT INTO currency (id, name) VALUES ('RUB', 'Russian rouble');
INSERT INTO currency (id, name) VALUES ('TRY', 'Turkish lira');
INSERT INTO currency (id, name) VALUES ('AUD', 'Australian dollar');
INSERT INTO currency (id, name) VALUES ('BRL', 'Brazilian real');
INSERT INTO currency (id, name) VALUES ('CAD', 'Canadian dollar');
INSERT INTO currency (id, name) VALUES ('CNY', 'Chinese yuan renminb');
INSERT INTO currency (id, name) VALUES ('HKD', 'Hong Kong dollar');
INSERT INTO currency (id, name) VALUES ('IDR', 'Indonesian rupiah');
INSERT INTO currency (id, name) VALUES ('ILS', 'Israeli shekel');
INSERT INTO currency (id, name) VALUES ('INR', 'Indian rupee');
INSERT INTO currency (id, name) VALUES ('KRW', 'South Korean won');
INSERT INTO currency (id, name) VALUES ('MXN', 'Mexican peso');
INSERT INTO currency (id, name) VALUES ('MYR', 'Malaysian ringgit');
INSERT INTO currency (id, name) VALUES ('NZD', 'New Zealand dollar');
INSERT INTO currency (id, name) VALUES ('PHP', 'Philippine peso');
INSERT INTO currency (id, name) VALUES ('SGD', 'Singapore dollar');
INSERT INTO currency (id, name) VALUES ('THB', 'Thai baht');
INSERT INTO currency (id, name) VALUES ('ZAR', 'South African rand');
INSERT INTO currency (id, name) VALUES ('ISK', 'Icelandic krona ');



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
  CONSTRAINT user_pkey PRIMARY KEY (id)
);

# --- !Downs
DROP TABLE "user";
DROP TABLE "country";
DROP TABLE "currency";
