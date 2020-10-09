DROP TABLE entidade_entidade;

CREATE TABLE entidade_entidade
(
  ents_codigo bigint NOT NULL,
  ent_codconstrutora bigint NOT NULL,
  ent_codvendedor bigint NOT NULL,
  CONSTRAINT entidade_entidade_pkey PRIMARY KEY (ents_codigo, ent_codconstrutora, ent_codvendedor),
  CONSTRAINT ent_codconstrutora_fk FOREIGN KEY (ent_codconstrutora)
      REFERENCES entidade (ent_codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ent_codvendedor_fk FOREIGN KEY (ent_codvendedor)
      REFERENCES entidade (ent_codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE entidade_entidade
  OWNER TO caixaki;

  
  
DROP TABLE entidade_entidade_aud;

CREATE TABLE entidade_entidade_aud
(
  ents_codigo bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint not null,
  ent_codconstrutora bigint not null,
  ent_codvendedor bigint not null,
  CONSTRAINT entidade_entidade_aud_pkey PRIMARY KEY (ents_codigo, rev),
  CONSTRAINT rev_fk FOREIGN KEY (rev)
      REFERENCES revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE entidade_entidade_aud
  OWNER TO caixaki;
  
