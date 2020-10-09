ALTER TABLE bairro ADD COLUMN versionnum integer;
update bairro set versionnum = 0;
ALTER TABLE bairro ALTER COLUMN versionnum SET NOT NULL;

ALTER TABLE cidade ADD COLUMN versionnum integer;
update cidade set versionnum = 0;
ALTER TABLE cidade ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE comissao_funcionario_filial ADD COLUMN versionnum integer;
update comissao_funcionario_filial set versionnum = 0;
ALTER TABLE comissao_funcionario_filial ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE empreendimento ADD COLUMN versionnum integer;
update empreendimento set versionnum = 0;
ALTER TABLE empreendimento ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE entidade ADD COLUMN versionnum integer;
update entidade set versionnum = 0;
ALTER TABLE entidade ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE entidade_entidade ADD COLUMN versionnum integer;
update entidade_entidade set versionnum = 0;
ALTER TABLE entidade_entidade ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE estado ADD COLUMN versionnum integer;
update estado set versionnum = 0;
ALTER TABLE estado ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE filial ADD COLUMN versionnum integer;
update filial set versionnum = 0;
ALTER TABLE filial ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE mensagem ADD COLUMN versionnum integer;
update mensagem set versionnum = 0;
ALTER TABLE mensagem ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE pais ADD COLUMN versionnum integer;
update pais set versionnum = 0;
ALTER TABLE pais ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE produto ADD COLUMN versionnum integer;
update produto set versionnum = 0;
ALTER TABLE produto ALTER COLUMN versionnum SET NOT NULL;

ALTER TABLE serie ADD COLUMN versionnum integer;
update serie set versionnum = 0;
ALTER TABLE serie ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE titulo ADD COLUMN versionnum integer; 
update titulo set versionnum = 0;
ALTER TABLE titulo ALTER COLUMN versionnum SET NOT NULL;


ALTER TABLE valor_comissao_filial ADD COLUMN versionnum integer;
update valor_comissao_filial set versionnum = 0;
ALTER TABLE valor_comissao_filial ALTER COLUMN versionnum SET NOT NULL;

