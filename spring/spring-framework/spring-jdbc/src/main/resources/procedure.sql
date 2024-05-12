DROP PROCEDURE resultSetDemo;

DELIMITER //
CREATE PROCEDURE if not exists resultSetDemo()
BEGIN
    START TRANSACTION ;
    SELECT *  FROM person WHERE id = 1 FOR UPDATE;
    UPDATE person SET NAME = '1' WHERE id = 1;
    COMMIT ;
end //
