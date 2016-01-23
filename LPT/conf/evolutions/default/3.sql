# --- !Ups

CREATE TABLE assetclassratio
(
  id serial NOT NULL,
  name character varying(255),
  description character varying(255),
  stocks double precision,
  fic double precision,
  mm double precision,
  realestate double precision,
  other_property double precision,
  CONSTRAINT assetclassratio_pkey PRIMARY KEY (id)
);

INSERT INTO assetclassratio (id, name, description, stocks, fic, mm, realestate, other_property) VALUES (1, 'Stocks', '100% stocks', 100, 0, 0, 0, 0);
INSERT INTO assetclassratio (id, name, description, stocks, fic, mm, realestate, other_property) VALUES (2, 'FIC', '100% fixed income', 0, 100, 0, 0, 0);
INSERT INTO assetclassratio (id, name, description, stocks, fic, mm, realestate, other_property) VALUES (3, 'MM - Cash', '100% money market, guaranteed cash', 0, 0, 100, 0, 0);
INSERT INTO assetclassratio (id, name, description, stocks, fic, mm, realestate, other_property) VALUES (4, 'Real estate', '100% real estate', 0, 0, 0, 100, 0);
INSERT INTO assetclassratio (id, name, description, stocks, fic, mm, realestate, other_property) VALUES (5, 'Other property', '100% other property', 0, 0, 0, 0, 100);

CREATE TABLE account
(
  id serial NOT NULL,
  accounttypeid integer,
  userid integer,
  name character varying(255),
  description character varying(255),
  defaultcurrency character varying(3),
  assetclassratioid integer,
  CONSTRAINT account_pkey PRIMARY KEY (id),
  CONSTRAINT account_assetclassratioid_fkey FOREIGN KEY (assetclassratioid)
      REFERENCES assetclassratio (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT account_defaultcurrency_fkey FOREIGN KEY (defaultcurrency)
      REFERENCES currency (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT account_typeid_fkey FOREIGN KEY (accounttypeid)
      REFERENCES accounttype (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT account_userid_fkey FOREIGN KEY (userid)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE recording
(
  id serial NOT NULL,
  accountid integer,
  ondate date,
  CONSTRAINT recording_pkey PRIMARY KEY (id),
  CONSTRAINT recording_accountid_fkey FOREIGN KEY (accountid)
      REFERENCES account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE inventory
(
  id serial NOT NULL,
  recordingid integer,
  assetclassratioid integer,
  name character varying(255),
  currency character varying(3),
  amount numeric,
  CONSTRAINT inventory_pkey PRIMARY KEY (id),
  CONSTRAINT inventory_assetclassratioid_fkey FOREIGN KEY (assetclassratioid)
      REFERENCES assetclassratio (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT inventory_currency_fkey FOREIGN KEY (currency)
      REFERENCES currency (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT inventory_recordingid_fkey FOREIGN KEY (recordingid)
      REFERENCES recording (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

# --- !Downs
drop table inventory;
drop table recording;
drop table account;
drop table assetclassratio;


