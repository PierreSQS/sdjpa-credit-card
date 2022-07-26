DROP DATABASE IF EXISTS sdjpa_sect22_payment;
DROP USER IF EXISTS `paymentadmin`@`%`;
DROP USER IF EXISTS `paymentuser`@`%`;
CREATE DATABASE IF NOT EXISTS sdjpa_sect22_payment CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `paymentadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'paymentadmin';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `sdjpa_sect22_payment`.* TO `paymentadmin`@`%`;
CREATE USER IF NOT EXISTS `paymentuser`@`%` IDENTIFIED WITH mysql_native_password BY 'paymentuser';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `sdjpa_sect22_payment`.* TO `paymentuser`@`%`;
FLUSH PRIVILEGES;