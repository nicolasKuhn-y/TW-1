USE tw1;

INSERT INTO country(id, name, code)
VALUES(1, "Argentina", "AR"),
(2, "Estados Unidos", "EU"),
(3, "Alemania", "AL"),
(4, "Brazil", "BR"),
(5, "Nueva Zelanda", "NZ"),
(6, "Colombia", "CO");

INSERT INTO vaccine(id, name, restTime, totalInyections)
VALUES
(1, "Anti fiebre amarilla", 15, 3),
(2, "Antirrabica", 8, 1),
(3, "COVID-19", 4, 5);

INSERT INTO country_vaccine(country_id, vaccine_id)
VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 1)