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

# --- !Downs
drop table assetclassratio;
