-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.8.2
-- PostgreSQL version: 9.5
-- Project Site: pgmodeler.com.br
-- Model Author: ---

-- object: "FAS" | type: ROLE --
-- DROP ROLE IF EXISTS "FAS";
CREATE ROLE "FAS" WITH 
	INHERIT
	LOGIN
	ENCRYPTED PASSWORD '********';
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: "LPT" | type: DATABASE --
-- -- DROP DATABASE IF EXISTS "LPT";
-- CREATE DATABASE "LPT"
-- 	ENCODING = 'UTF8'
-- 	LC_COLLATE = 'en_US.UTF8'
-- 	LC_CTYPE = 'en_US.UTF8'
-- 	TABLESPACE = pg_default
-- 	OWNER = "FAS"
-- ;
-- -- ddl-end --
-- 

-- object: public.play_evolutions | type: TABLE --
-- DROP TABLE IF EXISTS public.play_evolutions CASCADE;
CREATE TABLE public.play_evolutions(
	id integer NOT NULL,
	hash character varying(255) NOT NULL,
	applied_at timestamp NOT NULL,
	apply_script text,
	revert_script text,
	state character varying(255),
	last_problem text,
	CONSTRAINT play_evolutions_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.play_evolutions OWNER TO "FAS";
-- ddl-end --

-- object: public.currency | type: TABLE --
-- DROP TABLE IF EXISTS public.currency CASCADE;
CREATE TABLE public.currency(
	id character varying(3) NOT NULL,
	name character varying(255),
	CONSTRAINT currency_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.currency OWNER TO "FAS";
-- ddl-end --

-- object: public.rate_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.rate_id_seq CASCADE;
CREATE SEQUENCE public.rate_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.rate_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public.rate | type: TABLE --
-- DROP TABLE IF EXISTS public.rate CASCADE;
CREATE TABLE public.rate(
	id integer NOT NULL DEFAULT nextval('public.rate_id_seq'::regclass),
	currencyid character varying(3),
	ondate date,
	rate numeric,
	CONSTRAINT rate_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.rate OWNER TO "FAS";
-- ddl-end --

-- object: public.country | type: TABLE --
-- DROP TABLE IF EXISTS public.country CASCADE;
CREATE TABLE public.country(
	id smallint NOT NULL,
	code character varying(10),
	currency character varying(3),
	name character varying(255),
	CONSTRAINT country_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.country OWNER TO "FAS";
-- ddl-end --

-- object: public.user_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.user_id_seq CASCADE;
CREATE SEQUENCE public.user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.user_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public."user" | type: TABLE --
-- DROP TABLE IF EXISTS public."user" CASCADE;
CREATE TABLE public."user"(
	id integer NOT NULL DEFAULT nextval('public.user_id_seq'::regclass),
	email character varying(255) NOT NULL,
	password character varying(255),
	lastactivity timestamp NOT NULL,
	isadmin boolean,
	lastlogin character varying(255),
	CONSTRAINT user_pkey PRIMARY KEY (id),
	CONSTRAINT email_uniqe UNIQUE (email)

);
-- ddl-end --
ALTER TABLE public."user" OWNER TO "FAS";
-- ddl-end --

-- object: public.registration | type: TABLE --
-- DROP TABLE IF EXISTS public.registration CASCADE;
CREATE TABLE public.registration(
	id character varying(255) NOT NULL,
	email character varying(255) NOT NULL,
	password character varying(255) NOT NULL,
	ondate timestamp NOT NULL,
	confirmed boolean,
	CONSTRAINT registration_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.registration OWNER TO "FAS";
-- ddl-end --

-- object: public.accounttype_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.accounttype_id_seq CASCADE;
CREATE SEQUENCE public.accounttype_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.accounttype_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public.accounttype | type: TABLE --
-- DROP TABLE IF EXISTS public.accounttype CASCADE;
CREATE TABLE public.accounttype(
	id integer NOT NULL DEFAULT nextval('public.accounttype_id_seq'::regclass),
	name character varying(255),
	countryid smallint NOT NULL,
	description character varying(255),
	url character varying(255)[],
	CONSTRAINT accounttype_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.accounttype OWNER TO "FAS";
-- ddl-end --

-- object: public.quote_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.quote_id_seq CASCADE;
CREATE SEQUENCE public.quote_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.quote_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public.quote | type: TABLE --
-- DROP TABLE IF EXISTS public.quote CASCADE;
CREATE TABLE public.quote(
	id integer NOT NULL DEFAULT nextval('public.quote_id_seq'::regclass),
	quote text NOT NULL,
	author character varying(255) NOT NULL,
	CONSTRAINT quote_pg_key PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.quote OWNER TO "FAS";
-- ddl-end --

-- object: public.instrument_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.instrument_id_seq CASCADE;
CREATE SEQUENCE public.instrument_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.instrument_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public.instrument | type: TABLE --
-- DROP TABLE IF EXISTS public.instrument CASCADE;
CREATE TABLE public.instrument(
	id integer NOT NULL DEFAULT nextval('public.instrument_id_seq'::regclass),
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
-- ddl-end --
ALTER TABLE public.instrument OWNER TO "FAS";
-- ddl-end --

-- object: public.recording_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.recording_id_seq CASCADE;
CREATE SEQUENCE public.recording_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.recording_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public.recording | type: TABLE --
-- DROP TABLE IF EXISTS public.recording CASCADE;
CREATE TABLE public.recording(
	id integer NOT NULL DEFAULT nextval('public.recording_id_seq'::regclass),
	userid integer NOT NULL,
	ondate date,
	CONSTRAINT recording_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.recording OWNER TO "FAS";
-- ddl-end --

-- object: public.account_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.account_id_seq CASCADE;
CREATE SEQUENCE public.account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.account_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public.account | type: TABLE --
-- DROP TABLE IF EXISTS public.account CASCADE;
CREATE TABLE public.account(
	id integer NOT NULL DEFAULT nextval('public.account_id_seq'::regclass),
	accounttypeid integer,
	recordingid integer,
	name character varying(255),
	description character varying(255),
	defaultcurrency character varying(3),
	CONSTRAINT account_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.account OWNER TO "FAS";
-- ddl-end --

-- object: public.lot_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.lot_id_seq CASCADE;
CREATE SEQUENCE public.lot_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.lot_id_seq OWNER TO "FAS";
-- ddl-end --

-- object: public.lot | type: TABLE --
-- DROP TABLE IF EXISTS public.lot CASCADE;
CREATE TABLE public.lot(
	id integer NOT NULL DEFAULT nextval('public.lot_id_seq'::regclass),
	accountid integer,
	instrumentid integer,
	name character varying(255),
	currency character varying(3),
	amount numeric,
	CONSTRAINT lot_pkey PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.lot OWNER TO "FAS";
-- ddl-end --

-- object: rate_currencyid_fkey | type: CONSTRAINT --
-- ALTER TABLE public.rate DROP CONSTRAINT IF EXISTS rate_currencyid_fkey CASCADE;
ALTER TABLE public.rate ADD CONSTRAINT rate_currencyid_fkey FOREIGN KEY (currencyid)
REFERENCES public.currency (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: currency_id | type: CONSTRAINT --
-- ALTER TABLE public.country DROP CONSTRAINT IF EXISTS currency_id CASCADE;
ALTER TABLE public.country ADD CONSTRAINT currency_id FOREIGN KEY (currency)
REFERENCES public.currency (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: country_fkey | type: CONSTRAINT --
-- ALTER TABLE public.accounttype DROP CONSTRAINT IF EXISTS country_fkey CASCADE;
ALTER TABLE public.accounttype ADD CONSTRAINT country_fkey FOREIGN KEY (countryid)
REFERENCES public.country (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: recording_userid_fkey | type: CONSTRAINT --
-- ALTER TABLE public.recording DROP CONSTRAINT IF EXISTS recording_userid_fkey CASCADE;
ALTER TABLE public.recording ADD CONSTRAINT recording_userid_fkey FOREIGN KEY (userid)
REFERENCES public."user" (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: account_defaultcurrency_fkey | type: CONSTRAINT --
-- ALTER TABLE public.account DROP CONSTRAINT IF EXISTS account_defaultcurrency_fkey CASCADE;
ALTER TABLE public.account ADD CONSTRAINT account_defaultcurrency_fkey FOREIGN KEY (defaultcurrency)
REFERENCES public.currency (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: account_typeid_fkey | type: CONSTRAINT --
-- ALTER TABLE public.account DROP CONSTRAINT IF EXISTS account_typeid_fkey CASCADE;
ALTER TABLE public.account ADD CONSTRAINT account_typeid_fkey FOREIGN KEY (accounttypeid)
REFERENCES public.accounttype (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: account_recoringid_fkey | type: CONSTRAINT --
-- ALTER TABLE public.account DROP CONSTRAINT IF EXISTS account_recoringid_fkey CASCADE;
ALTER TABLE public.account ADD CONSTRAINT account_recoringid_fkey FOREIGN KEY (recordingid)
REFERENCES public.recording (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: lot_instrumentid_fkey | type: CONSTRAINT --
-- ALTER TABLE public.lot DROP CONSTRAINT IF EXISTS lot_instrumentid_fkey CASCADE;
ALTER TABLE public.lot ADD CONSTRAINT lot_instrumentid_fkey FOREIGN KEY (instrumentid)
REFERENCES public.instrument (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: lot_currency_fkey | type: CONSTRAINT --
-- ALTER TABLE public.lot DROP CONSTRAINT IF EXISTS lot_currency_fkey CASCADE;
ALTER TABLE public.lot ADD CONSTRAINT lot_currency_fkey FOREIGN KEY (currency)
REFERENCES public.currency (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: lot_accountid_fkey | type: CONSTRAINT --
-- ALTER TABLE public.lot DROP CONSTRAINT IF EXISTS lot_accountid_fkey CASCADE;
ALTER TABLE public.lot ADD CONSTRAINT lot_accountid_fkey FOREIGN KEY (accountid)
REFERENCES public.account (id) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


