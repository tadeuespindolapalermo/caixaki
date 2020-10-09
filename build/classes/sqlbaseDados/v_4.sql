ALTER TABLE cidade DROP COLUMN cid_uf;
ALTER TABLE cidade_aud DROP COLUMN cid_uf;


select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Acre', 'AC', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Alagoas', 'AL', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Amapá', 'AP', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Amazonas', 'AM', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Bahia', 'BA', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Ceará', 'CE', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Distrito Federal', 'DF', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Espírito Santo', 'ES', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Goiás', 'GO', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Maranhão', 'MA', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Mato Grosso', 'MT', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Mato Grosso do Sul', 'MS', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Minas Gerais', 'MG', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Pará', 'PA', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Paraíba', 'PB', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Pernanbuco', 'PE', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Piauí', 'PI', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Rio de Janeiro', 'RJ', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Rio Grande do Norte', 'RN', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Rio Grande do Sul', 'RS', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Rondônia', 'RO', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Roraima', 'RR', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Santa Catarina', 'SC', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'São Paulo', 'SP', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Sergipe', 'SE', (select min(pai_id) from pais));

select nextval('estado_seq');
INSERT INTO estado(
            est_id, est_nome, est_uf, pais)
    VALUES ((select last_value from estado_seq) , 'Tocantins', 'TO', (select min(pai_id) from pais));


ALTER TABLE filial DROP COLUMN fil_autenticacaoemail; 

ALTER TABLE filial ADD COLUMN fil_autenticaremail character varying(255);

ALTER TABLE filial_aud  DROP COLUMN fil_autenticacaoemail;

ALTER TABLE filial_aud ADD COLUMN fil_autenticaremail character varying(255);

ALTER TABLE filial ADD COLUMN fil_servidoremail character varying(255);

ALTER TABLE filial_aud ADD COLUMN fil_servidoremail character varying(255);

CREATE TABLE comissao_funcionario_filial
(
  com_codigo bigint NOT NULL,
  com_descricao character varying(100),
  ent_codigo bigint NOT NULL,
  fil_codigo bigint NOT NULL,
  CONSTRAINT comissao_funcionario_filial_pkey PRIMARY KEY (com_codigo),
  CONSTRAINT ent_codigo_fk FOREIGN KEY (ent_codigo)
      REFERENCES entidade (ent_codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fil_codigo_fk FOREIGN KEY (fil_codigo)
      REFERENCES filial (fil_codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE 
);
ALTER TABLE comissao_funcionario_filial
  OWNER TO caixaki;
  
  
  CREATE TABLE comissao_funcionario_filial_aud
(
  com_codigo bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  com_descricao character varying(100),
  ent_codigo bigint,
  fil_codigo bigint,
  CONSTRAINT comissao_funcionario_filial_aud_pkey PRIMARY KEY (com_codigo, rev),
  CONSTRAINT revinfo_fk FOREIGN KEY (rev)
      REFERENCES revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comissao_funcionario_filial_aud
  OWNER TO caixaki;

  
  CREATE TABLE valor_comissao_filial
(
  val_codigo bigint NOT NULL,
  val_descricao character varying(100),
  val_valor numeric(15,4) NOT NULL,
  val_comissao bigint NOT NULL,
  CONSTRAINT valor_comissao_filial_pkey PRIMARY KEY (val_codigo),
  CONSTRAINT val_comissao_fk FOREIGN KEY (val_comissao)
      REFERENCES comissao_funcionario_filial (com_codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE valor_comissao_filial
  OWNER TO caixaki;

  
  CREATE TABLE valor_comissao_filial_aud
(
  val_codigo bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  val_descricao character varying(100),
  val_valor numeric(15,4),
  val_comissao bigint,
  CONSTRAINT valor_comissao_filial_aud_pkey PRIMARY KEY (val_codigo, rev),
  CONSTRAINT revinfo_fk FOREIGN KEY (rev)
      REFERENCES revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE valor_comissao_filial_aud
  OWNER TO caixaki;


ALTER TABLE valor_comissao_filial ADD COLUMN val_calculo character varying(255);
ALTER TABLE valor_comissao_filial ADD COLUMN val_tipo character varying(255);
ALTER TABLE valor_comissao_filial_aud ADD COLUMN val_calculo character varying(255);
ALTER TABLE valor_comissao_filial_aud ADD COLUMN val_tipo character varying(255);

