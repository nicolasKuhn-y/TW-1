USE tw1;


INSERT INTO user(id, email, password, name)
VALUES (1, 'nicolaskuhn02@gmail.com', 'contraSegura123', "Nicolas");

INSERT INTO country(id, code, name)
VALUES 
(1, "AR", "Argentina"),
(2, "BR", "Brasil"),
(3, "CH", "China"),
(4, "JP", "Japon"),
(5, "EU", "Estados Unidos"),
(6, "PO", "Portugal"),
(7, "AL", "Alemania");

INSERT INTO vaccine(id, name, rest_time, total_inyections)
VALUES 
(1, "Antirrabica",  3, 2),
(2, "COVID-19", 7, 4),
(3, "Fiebre amarilla", 14, 1),
(4, "Tetanos", 5, 2);

INSERT INTO country_vaccine_group(id, is_vaccine_required, country_id, vaccine_id)
VALUES
(1, 1, 1, 2),
(2, 0, 1, 4),
(3, 1, 1, 3),
(4, 0, 1, 1),
(5, 1, 2, 1),
(6, 0, 3, 1),
(7, 1, 4, 1);

INSERT INTO hospital(id, email, latitude, longitude, name, imageUrl, description, address)
VALUES
(1, "posadas@gmail.com", -34.628341162823055, -58.57503469952699, "Hospital Posadas", "/images/posadas.jpg", "El hospital cuenta con especialidades infantiles y de Maternidad. Brinda una amplia atencion para diferentes coberturas.", "Avenida Presidente Arturo U. Illia 386, El Palomar, Provincia de Buenos Aires"),
(2, "mail@gmai.com", -34.70435293487975, -58.542960673431025, "Balestrini", "/images/balestrini.jpg", "Hospital de alta complejidad", "Av. Monseñor Bufano, Ciudad Evita, Provincia de Buenos Aires"),
(3, "mail@gmai.com", -34.70140180538249, -58.61267614077974, "Favaloro", null, "Cuenta con atencion de guardia 24 horas y vacunatorio.", null),
(4, "mail@gmai.com", -34.63682088989759, -58.54024125977611, "San juan de dios", "/images/san-juan-dios.jfif", "Hospital de alta complejidad", "Gral. Pedro Díaz 3366, Hurlingham, Provincia de Buenos Aires"),
(5, "makako@gmail.com", -23.543413739575563, -46.593079258887784, "CEMA Sao Paulo", null, "Hospital de alta complejidad", null),
(6, "canadaAlFondo@gmail.com", 59.330863918668626, -107.18720004200823, "Choza", null, "Hospital de alta complejidad", null);


INSERT INTO reserve(id, date, user_id, hospital_id)
VALUES
(1, '2022-07-29 08:00:00', 1, 1),
(2, '2022-07-01 15:45:00', 1, 1),
(3, '2022-07-11 18:25:00', 1, 1);

