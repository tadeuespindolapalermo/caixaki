
INSERT INTO pais (pai_id, pai_nome, pai_sigla) VALUES (1, 'BRASIL', 'BR');

INSERT INTO estado (est_id, est_uf, est_nome, pais) VALUES (1, 'PR', 'Paraná', 1);

INSERT INTO cidade (cid_codigo, cid_descricao, estado, cid_uf) VALUES (1, 'Maringá', 1, 'Maringá');


alter SEQUENCE pais_seq INCREMENT 1;
alter SEQUENCE estado_seq INCREMENT 1;
alter SEQUENCE cidade_seq INCREMENT 1;

select nextval('pais_seq');
select nextval('estado_seq');
select nextval('cidade_seq');

INSERT INTO entidade (ent_codigo, ent_celular, ent_cep, ent_complemento, ent_contato, ent_datacadastro, ent_datanascimento, ent_email, ent_emailcontato, ent_emailmovimento, ent_endereco, ent_fax, ent_fone, ent_inativo, ent_login, ent_mudarsenha, ent_nomefantasia, ent_observacao, ent_razao, ent_senha, ent_tipo, ent_tipopessoa, ent_ultimoacesso, bai_codigo, cid_codigo, fil_codigo) VALUES (1, NULL, NULL, NULL, NULL, '2014-10-10 00:00:00', '2014-10-10', NULL, NULL, NULL, NULL, NULL, NULL, false, 'alex', false, 'alex', NULL, NULL, 'alex', 'TIPO_CADASTRO_FUNCIONARIO', NULL, '2014-06-23 15:20:00', NULL, NULL, NULL);
INSERT INTO entidade (ent_codigo, ent_celular, ent_cep, ent_complemento, ent_contato, ent_datacadastro, ent_datanascimento, ent_email, ent_emailcontato, ent_emailmovimento, ent_endereco, ent_fax, ent_fone, ent_inativo, ent_login, ent_mudarsenha, ent_nomefantasia, ent_observacao, ent_razao, ent_senha, ent_tipo, ent_tipopessoa, ent_ultimoacesso, bai_codigo, cid_codigo, fil_codigo) VALUES (2, NULL, NULL, NULL, NULL, '2014-10-10 00:00:00', '2014-10-10', NULL, NULL, NULL, NULL, NULL, NULL, false, 'junior', false, 'junior', NULL, NULL, 'junior', 'TIPO_CADASTRO_FUNCIONARIO', NULL, '2014-06-23 15:20:00', NULL, NULL, NULL);

/*atualiza chamando duas vezes*/
select nextval('entidade_seq');
select nextval('entidade_seq');

INSERT INTO entidadeacesso (ent_codigo, esa_codigo) VALUES (1, 'USER');
INSERT INTO entidadeacesso (ent_codigo, esa_codigo) VALUES (2, 'USER');
