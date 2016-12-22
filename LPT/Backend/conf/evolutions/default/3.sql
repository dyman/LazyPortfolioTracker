# --- !Ups
CREATE TABLE instrument
(
  id serial NOT NULL,
  isin character varying(12),
  name character varying(255),
  description character varying(255),
  developed_stocks double precision,
  developing_stocks double precision,
  fic double precision,
  mm double precision,
  realestate double precision,
  other_property double precision,
  CONSTRAINT instrument_pkey PRIMARY KEY (id)
);

CREATE TABLE recording
(
  id serial NOT NULL,
  userid integer NOT NULL,
  valudate date,
  CONSTRAINT recording_pkey PRIMARY KEY (id),
  CONSTRAINT recording_userid_fkey FOREIGN KEY (userid)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
  
);

CREATE TABLE account
(
  id serial NOT NULL,
  accounttypeid integer,
  recordingid integer,
  name character varying(255),
  description character varying(255),
  defaultcurrency character varying(3),  
  CONSTRAINT account_pkey PRIMARY KEY (id),  
  CONSTRAINT account_defaultcurrency_fkey FOREIGN KEY (defaultcurrency)
      REFERENCES currency (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT account_typeid_fkey FOREIGN KEY (accounttypeid)
      REFERENCES accounttype (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT account_recoringid_fkey FOREIGN KEY (recordingid)
      REFERENCES recording (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
  
);

CREATE TABLE lot
(
  id serial NOT NULL,
  accountid integer,
  instrumentid integer,
  name character varying(255),
  currency character varying(3),
  amount numeric,
  CONSTRAINT lot_pkey PRIMARY KEY (id),
  CONSTRAINT lot_instrumentid_fkey FOREIGN KEY (instrumentid)
      REFERENCES instrument (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT lot_currency_fkey FOREIGN KEY (currency)
      REFERENCES currency (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT lot_accountid_fkey FOREIGN KEY (accountid)
      REFERENCES account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

# --- !Downs
drop table lot CASCADE;
drop table account CASCADE;
drop table recording CASCADE;
drop table instrument CASCADE;


