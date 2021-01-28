create schema instituto;
use instituto

--ejecutar con la aplicacion levantada pero antes de registrar al primer usuario
INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `role` VALUES (2,'USER');

--se inserta el usuario admin para ingresar la primera vez con 1234/1234
INSERT INTO `USER` (USER_ID, ACTIVE, DNI, NUMBER)VALUES (1,1, '1234', '$2a$10$bewZiucZ7pOAMplMLovQVeRBpcymc0q87/sq0MH9MK5HUg/Nl2NZq')
