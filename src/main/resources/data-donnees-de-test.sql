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