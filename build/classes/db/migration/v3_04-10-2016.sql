
CREATE SEQUENCE sendemail_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 16
  CACHE 1;
ALTER TABLE sendemail_seq
  OWNER TO caixaki;

CREATE TABLE sendemail
(
  id_sendemail bigint NOT NULL,
  email character varying(100) NOT NULL,
  tratamento character varying(100) NOT NULL,
  enviado boolean NOT NULL,
  msgEnviada character varying(1000000) NOT NULL,
  versionnum integer NOT NULL,
  CONSTRAINT sendemail_pkey PRIMARY KEY (id_sendemail)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sendemail
  OWNER TO caixaki;
  
  
 CREATE TABLE sendemail_aud
(
  id_sendemail bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  email character varying(100) NOT NULL,
  tratamento character varying(100) NOT NULL,
  enviado boolean NOT NULL,
  msgEnviada character varying(1000000) NOT NULL,
  versionnum integer NOT NULL,
  CONSTRAINT sendemail_aud_pkey PRIMARY KEY (id_sendemail, rev),
   CONSTRAINT rev_fk FOREIGN KEY (rev)
      REFERENCES revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sendemail_aud
  OWNER TO caixaki;


