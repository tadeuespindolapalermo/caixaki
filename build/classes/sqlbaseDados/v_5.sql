ALTER TABLE mensagem ADD COLUMN men_mensagem_temp character varying(500);
UPDATE mensagem SET men_mensagem_temp = men_mensagem;
ALTER TABLE mensagem DROP COLUMN men_mensagem;
ALTER TABLE mensagem ADD COLUMN men_mensagem character varying(1000);
UPDATE mensagem SET men_mensagem = men_mensagem_temp;
ALTER TABLE mensagem DROP COLUMN men_mensagem_temp;

ALTER TABLE mensagem_aud  ADD COLUMN men_mensagem_temp character varying(500);
UPDATE mensagem_aud SET men_mensagem_temp = men_mensagem;
ALTER TABLE mensagem_aud DROP COLUMN men_mensagem;
ALTER TABLE mensagem_aud ADD COLUMN men_mensagem character varying(1000);
UPDATE mensagem_aud SET men_mensagem = men_mensagem_temp;
ALTER TABLE mensagem_aud DROP COLUMN men_mensagem_temp;

