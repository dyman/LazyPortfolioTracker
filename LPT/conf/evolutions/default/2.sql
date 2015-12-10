# --- !Ups
CREATE TABLE "accounttype"
(
  id serial NOT NULL,
  name character varying(255),
  "countryid" smallint NOT NULL,
  CONSTRAINT "accounttype_pkey" PRIMARY KEY (id),
  CONSTRAINT country_fkey FOREIGN KEY ("countryid")
      REFERENCES country (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


# --- !Downs
DROP TABLE "accounttype";
