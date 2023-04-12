/*tabla trabajadores*/
INSERT INTO `trabajadores` (nombre,email) VALUES('Nathalie','nathaliefebres93@gmail.com');
/*tabla usuarios*/
INSERT INTO `usuarios` (nombre,password,email) VALUES('Miguelangel','$2a$10$1ZOf89f8RIo2/9mkam27SuCHNTUkfSJZqBOCNX61f1rgg7m9ChIF6','mlugo2277@gmail.com');
INSERT INTO `phones` (number,citycode,contrycode) VALUES('53438328','9','56');

INSERT INTO `usuarios_phones` (usuario_id,phone_id) VALUES(1,1)