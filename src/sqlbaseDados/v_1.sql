 CREATE TABLE arquivoversao
(
  arquivosql character varying(100) NOT NULL,
  dataatualizacao date NOT NULL DEFAULT ('now'::text)::date,
  hora time without time zone NOT NULL DEFAULT ('now'::text)::time without time zone,
  CONSTRAINT arquivoversao_arquivosql_key UNIQUE (arquivosql)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE arquivoversao
  OWNER TO caixaki;
 