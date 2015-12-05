# --- !Ups
CREATE TABLE "accountTypes"
(
  id serial NOT NULL,
  name character varying(255),
  "countryId" smallint NOT NULL,
  CONSTRAINT "accountTypes_pkey" PRIMARY KEY (id),
  CONSTRAINT country_fkey FOREIGN KEY ("countryId")
      REFERENCES country (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO "accountTypes" VALUES (1, 'TBSZ 2015', 1);
INSERT INTO "accountTypes" VALUES (2, 'TBSZ 2011', 1);
INSERT INTO "accountTypes" VALUES (3, 'TBSZ 2012', 1);
INSERT INTO "accountTypes" VALUES (4, 'TBSZ 2013', 1);
INSERT INTO "accountTypes" VALUES (5, 'TBSZ 2014', 1);

# --- !Downs
DROP TABLE "accountTypes"
