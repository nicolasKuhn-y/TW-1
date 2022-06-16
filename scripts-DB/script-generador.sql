
INSERT INTO country(id, name,code)
VALUES (1, 'Argentina','AR');
INSERT INTO country(id, name, code)
VALUES (2, 'Estados Unidos', 'EU');
INSERT INTO country(id, name, code)
VALUES (3, 'Alemania', 'AL');
INSERT INTO country(id, name, code)
VALUES (4, 'Brazil', 'BR');
INSERT INTO country(id, name, code)
VALUES (5, 'Nueva Zelanda', 'NZ');
INSERT INTO country(id, name, code)
VALUES (6, 'Colombia', 'CO');


INSERT INTO vaccine(id, name, rest_time, total_inyections,maxAge,minAge)
VALUES (1, 'Anti fiebre amarilla', 15, 3, 50, 1);
INSERT INTO vaccine(id, name, rest_time, total_inyections,maxAge,minAge)
VALUES (2, 'Antirrabica', 8, 1, 65, 7);
INSERT INTO vaccine(id, name, rest_time, total_inyections,maxAge,minAge)
VALUES (3, 'COVID-19', 4, 5, 90, 13);
INSERT INTO vaccine(id, name, rest_time, total_inyections,maxAge,minAge)
VALUES (4, 'COVID-191', 4, 5, 90, 13);

INSERT INTO country_vaccine_group(id, is_vaccine_required, country_id, vaccine_id)
VALUES
(1, true, 1, 2),
(2, false, 1, 4),
(3, true, 1, 3),
(4, false, 1, 1),
(5, true, 2, 1),
(6, false, 3, 1),
(7, true, 4, 1);

INSERT INTO hospital(id, email, latitude, longitude, name)
VALUES
(1, 'posadas@gmail.com', -34.628341162823055, -58.57503469952699, 'Hospital Posadas'),
(2, 'mail@gmai.com', -34.70435293487975, -58.542960673431025, 'Balestrini'),
(3, 'mail@gmai.com', -34.70140180538249, -58.61267614077974, 'Favaloro' ),
(4, 'mail@gmai.com', -34.63682088989759, -58.54024125977611, 'San juan de dios'),
(5, 'makako@gmail.com', -23.543413739575563, -46.593079258887784, 'CEMA Sao Paulo'),
(6, 'canadaAlFondo@gmail.com', 59.330863918668626, -107.18720004200823, 'Choza');
