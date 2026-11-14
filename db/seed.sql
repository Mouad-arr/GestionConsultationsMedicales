-- =====================================================================
--  Seed data — Gestion des Consultations Médicales
--  Target DB: gestion_consultations (MySQL)
--
--  Run with:
--    docker exec -i gestion-db mysql -uroot -proot gestion_consultations < db/seed.sql
-- =====================================================================

USE gestion_consultations;

-- Reset tables (idempotent — safe to re-run)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE consultation;
TRUNCATE TABLE diagnostic;
TRUNCATE TABLE prescription;
TRUNCATE TABLE Admin;
TRUNCATE TABLE Medecin;
TRUNCATE TABLE Patient;
SET FOREIGN_KEY_CHECKS = 1;

-- ---------------------------------------------------------------------
-- Admin (login: admin@medicare.com / admin123)
-- ---------------------------------------------------------------------
INSERT INTO `Admin` (nom, prenom, email, telephone, dateNaissance, role, motDePasse, dateCreation)
VALUES ('Mohammed', 'Laadimi', 'admin@medicare.com', '0697779120', '2005-07-15', 'ADMIN', 'admin123', CURRENT_DATE);

-- ---------------------------------------------------------------------
-- Médecins
-- ---------------------------------------------------------------------
INSERT INTO `Medecin` (nom, prenom, email, telephone, dateNaissance, role, motDePasse, specialite, numeroOrdre)
VALUES
('Omar',  'Laadimi', 'o.laadimi@medicare.com',  '0611111111', '1980-05-20', 'MEDECIN', 'med123', 'Cardiologie', 'ORD-001'),
('El Amrani', 'Salma',  's.elamrani@medicare.com', '0622222222', '1988-09-10', 'MEDECIN', 'med123', 'Donteste',   'ORD-002');

-- ---------------------------------------------------------------------
-- Patients
-- ---------------------------------------------------------------------
INSERT INTO `Patient` (nom, prenom, email, telephone, dateNaissance, role, numeroDossier, groupeSanguin, sexe, cin, adresse)
VALUES
('Alaoui',   'Karim', 'k.alaoui@mail.com', '0633333333', '1990-03-05', 'PATIENT', 'DOS-001', 'O+', 'HOMME', 'AB123456', 'Casablanca'),
('Tazi',     'Nadia', 'n.tazi@mail.com',   '0644444444', '1995-07-22', 'PATIENT', 'DOS-002', 'A-', 'FEMME', 'CD654321', 'Rabat'),
('Idrissi',  'Omar',  'o.idrissi@mail.com', '0655555555', '1982-11-30', 'PATIENT', 'DOS-003', 'B+', 'HOMME', 'EF789012', 'Fès');

-- ---------------------------------------------------------------------
-- Consultations
--   patient_id / medecin_id refer to the ids inserted above (1,2,3 ...)
--   Some are "today" (NOW()) so they appear in "Consultations du jour".
-- ---------------------------------------------------------------------
INSERT INTO `consultation` (date_consultation, observations, motif, patient_id, medecin_id, status)
VALUES
(NOW(),                      NULL,             'Douleur thoracique',     1, 1, 'Planifie'),
(NOW() + INTERVAL 1 HOUR,    NULL,             'Consultation de suivi',  2, 1, 'Planifie'),
(NOW() - INTERVAL 3 DAY,     'Tension normale', 'Contrôle annuel',       1, 1, 'TERMINEE'),
(NOW() - INTERVAL 5 DAY,     NULL,             'Fièvre',                 3, 2, 'TERMINEE'),
(NOW() + INTERVAL 2 DAY,     NULL,             'Vaccination',            2, 2, 'Planifie'),
(NOW() - INTERVAL 1 DAY,     NULL,             'Annulé par le patient',  3, 2, 'ANNULEE');
