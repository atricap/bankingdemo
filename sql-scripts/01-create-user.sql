DROP USER if exists 'bankingdemo'@'%';
DROP USER if exists 'bankingdemoadmin'@'%';

CREATE USER 'bankingdemo'@'%' IDENTIFIED BY 'password';
CREATE USER 'bankingdemoadmin'@'%' IDENTIFIED BY 'password';

GRANT
  DELETE, EXECUTE, INSERT, LOCK TABLES, SELECT, UPDATE
  ON `bankingdemo`.* TO 'bankingdemo'@'%';

GRANT
  ALTER, CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE,
  DROP, EXECUTE, INDEX, INSERT, LOCK TABLES, REFERENCES, SELECT, UPDATE
  ON `bankingdemo`.* TO 'bankingdemoadmin'@'%';
