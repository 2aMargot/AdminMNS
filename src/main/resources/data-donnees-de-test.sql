INSERT INTO `model_user` (`user_firstname`,`user_lastname`,`user_email`,`user_intern_email`,`user_password`,`user_gender`)
VALUES
    ("Ivan","Byers","quam.pellentesque@outlook.edu","adipiscing@yahoo.net","IWK06MTS1HC","F"),
    ("Uta","Santos","tincidunt.congue.turpis@google.couk","quisque.fringilla.euismod@outlook.ca","IWK06MTS1HC","M"),
    ("Ferdinand","Ramsey","netus@hotmail.com","eu.tempor@protonmail.net","TMI35HOV4HF","F"),
    ("Kasimir","Benton","erat.vivamus@protonmail.org","velit.in@aol.edu","PGP81WWO2KK","F"),
    ("Serena","Clayton","pretium.aliquet@google.com","convallis.est@google.com","LXD50QNA8PG","A");

INSERT INTO `user_role` (`role_name`,`role_description`)
VALUES
    ("Admin","tous les droits"),
    ("Etudiant","Juste les droits étudiant"),
    ("Gestionnaire d'incription","Validation document d'incription"),
    ("Gestionnaire de formation","Peux tout voir"),
    ("Accueil","Validation absence et retard");

INSERT INTO `student` (`student_birthdate`,`student_birthplace`,`student_nationality`,`student_postalcode`,`student_address`,`student_city`,`student_phonenumber`,`student_social_security_number`,`student_france_travail_number`)
VALUES
    (DATE '2024-03-18',"Melilla","India","16485","Ap #326-5966 Ligula Ave","São Gonçalo","08 41 27 57 80","1 24 26 15 882 679 25","844 3446 U"),
    (DATE '2020-04-19',"Hong Kong","New Zealand","20718","Ap #691-452 Consequat Avenue","Augusta","09 31 84 01 38","1 76 62 72 624 503 89","808 1528 H"),
    (DATE '2021-05-17',"Bilbo","South Korea","471734","P.O. Box 952, 8022 Eu St.","Arica","03 39 19 01 61","1 49 59 63 104 341 85","246 9873 T"),
    (DATE '2022-06-16',"Hulst","Norway","820667","P.O. Box 551, 3125 Ligula Rd.","Hamburg","05 37 21 93 23","1 21 66 22 461 970 12","782 0487 P"),
    (DATE '2025-07-15',"Montague","United States","0311","2854 Lobortis. Rd.","Orito","07 73 96 61 94","1 45 09 82 167 422 17","730 7986 R");

INSERT INTO `training` (`training_name`,`training_start`,`training_end`)
VALUES
    ("enim commodo","2023-05-03","2024-12-28"),
    ("gravida non,","2024-04-03","2023-12-23"),
    ("elementum, dui","2023-12-25","2024-12-10"),
    ("dis parturient","2023-09-18","2024-07-29"),
    ("placerat, orci","2025-02-01","2024-01-08");

INSERT INTO `student_inscription_folder` (`inscription_folder_creation_date`,`inscription_folder_deadline`)
VALUES
    ("2024-04-19","2026-12-09"),
    ("2024-11-15","2028-01-13"),
    ("2024-02-17","2027-06-20"),
    ("2025-03-23","2026-07-18"),
    ("2023-08-21","2027-04-07");

INSERT INTO `document` (`document_name`,`document_deposite_date`,`document_refusal_date`,`document_validation_date`,`document_validity`,`document_link`)
VALUES
    ("Cras","2023-11-26","2028-02-01","2023-11-10",1,"http://cnn.com/one?search=1"),
    ("venenatis","2023-09-26","2026-05-01","2024-08-08",0,"https://wikipedia.org/user/110?gi=100"),
    ("nec","2024-01-13","2027-04-20","2024-11-12",0,"http://naver.com/en-us?search=1&q=de"),
    ("rutrum.","2024-08-06","2027-05-03","2024-03-12",0,"https://youtube.com/site?q=4"),
    ("massa","2024-01-29","2026-11-07","2023-04-05",1,"https://wikipedia.org/sub/cars?str=se");

INSERT INTO `doc_type` (`doctype_name`,`doctype_description`)
VALUES
    ("elit,","rhoncus id, mollis nec,"),
    ("Nunc","facilisis lorem tristique aliquet. Phasellus fermentum"),
    ("non","parturient montes, nascetur ridiculus mus. Proin vel arcu eu odio tristique pharetra."),
    ("risus.","risus quis diam luctus lobortis. Class aptent taciti sociosqu ad litora torquent per conubia nostra,"),
    ("gravida.","malesuada. Integer id magna et ipsum cursus vestibulum. Mauris");

INSERT INTO `absence` (`absence_creation_date`,`absence_end`,`absence_start`,`absence_justification`)
VALUES
    ("2025-03-01","2024-07-26 19:08","2025-03-26 09:00","https://reddit.com/sub/cars?search=1&q=de"),
    ("2023-10-14","2023-08-06 22:40","2023-07-05 03:12","http://pinterest.com/fr?search=1"),
    ("2023-10-10","2024-05-26 05:16","2024-08-19 20:34","https://zoom.us/one?g=1"),
    ("2024-12-12","2024-12-05 19:43","2024-02-10 09:42","https://zoom.us/en-us?str=se"),
    ("2023-06-16","2024-06-15 20:24","2024-04-05 09:14","https://youtube.com/sub/cars?ad=115");

INSERT INTO `lateness` (`lateness_creation_date`,`lateness_date`,`lateness_justification`)
VALUES
    ("2024-10-28","2023-04-22 18:22","https://twitter.com/group/9?ab=441&aad=2"),
    ("2023-06-11","2024-12-11 09:49","http://zoom.us/one?gi=100"),
    ("2024-02-02","2024-12-01 10:42","https://google.com/sub/cars?ab=441&aad=2"),
    ("2024-12-01","2023-05-31 05:46","http://walmart.com/fr?ad=115"),
    ("2024-11-16","2023-05-31 07:32","http://reddit.com/sub/cars?ad=115");

INSERT INTO `absence_cause` (`absence_cause_name`)
VALUES
    ("Quisque fringilla"),
    ("lacus. Nulla"),
    ("ligula. Aliquam"),
    ("nibh lacinia"),
    ("Mauris eu");

INSERT INTO `lateness_cause` (`lateness_cause_name`)
VALUES
    ("amet ultricies"),
    ("risus. Quisque"),
    ("Donec egestas."),
    ("rutrum lorem"),
    ("Nulla facilisis.");
