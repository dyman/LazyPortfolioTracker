# --- !Ups
CREATE TABLE "user"
(
  id serial NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255),
  fullname character varying(255),
  isadmin boolean,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


# --- !Downs
DROP TABLE "user";
