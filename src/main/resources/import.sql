INSERT INTO tb_roles (authority) VALUES ('ROLE_ADMIN'), ('ROLE_CLIENT');

INSERT INTO tb_users(name, email, pass) VALUES ('Samille', 'sami@mail.com', '$2a$12$BEPUGk/8jpO9wBlQBjgSq.NHMXMQFJTcWzqcnPBsmL6JSEDCDczom');
INSERT INTO tb_users(name, email, pass) VALUES ('Lucio', 'lucio@mail.com', '$2a$12$BEPUGk/8jpO9wBlQBjgSq.NHMXMQFJTcWzqcnPBsmL6JSEDCDczom');
INSERT INTO tb_users(name, email, pass) VALUES ('Elisa', 'elisa@mail.com', '$2a$12$BEPUGk/8jpO9wBlQBjgSq.NHMXMQFJTcWzqcnPBsmL6JSEDCDczom');

INSERT INTO tb_users_roles (id_user, id_role) VALUES (1, 1);
INSERT INTO tb_users_roles (id_user, id_role) VALUES (2, 2);
INSERT INTO tb_users_roles (id_user, id_role) VALUES (3, 2);

--INSERT INTO tb_cities (name) values ('Porto Alegre');
--INSERT INTO tb_cities (name) values ('Rio de Janeiro');
--INSERT INTO tb_cities (name) values ('São Paulo');
--INSERT INTO tb_cities (name) values ('Curitiba');
--INSERT INTO tb_cities (name) values ('Florianópolis');
--
--INSERT INTO tb_events (name, date, url) VALUES ("Rock in Rio", 2023-07-02, "www.rockinrio.com.br");
--INSERT INTO tb_events (name, date, url) VALUES ("Lollapalloza", 2024-07-02, "www.lollapalooza.com.br");
--INSERT INTO tb_events (name, date, url) VALUES ("Rock in Rio", 2026-07-02, "www.rockinrio.com.br");
--INSERT INTO tb_events (name, date, url) VALUES ("Lollapalloza", 2027-07-02, "www.lollapalooza.com.br");
