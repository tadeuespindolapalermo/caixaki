ALTER TABLE entidadeacesso ADD COLUMN esa_codigo_temp character varying(50);
update entidadeacesso SET esa_codigo_temp = esa_codigo;
ALTER TABLE entidadeacesso DROP COLUMN esa_codigo;
ALTER TABLE entidadeacesso ADD COLUMN esa_codigo character varying(70);
update entidadeacesso SET esa_codigo = esa_codigo_temp;
ALTER TABLE entidadeacesso DROP COLUMN esa_codigo_temp; 

ALTER TABLE entidadeacesso_aud ADD COLUMN esa_codigo_temp character varying(50);
update entidadeacesso_aud SET esa_codigo_temp = esa_codigo;
ALTER TABLE entidadeacesso_aud DROP COLUMN esa_codigo;
ALTER TABLE entidadeacesso_aud ADD COLUMN esa_codigo character varying(70);
update entidadeacesso_aud SET esa_codigo = esa_codigo_temp;
ALTER TABLE entidadeacesso_aud DROP COLUMN esa_codigo_temp; 