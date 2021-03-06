/* Florian ERNST - Thomas FAUGIER */

create table professeur
(
    matricule   int auto_increment
        primary key,
    nom         varchar(50) not null,
    prenom      varchar(50) not null,
    numero_rue  varchar(50) not null,
    rue         varchar(50) not null,
    code_postal varchar(50) not null,
    ville       varchar(50) not null,
    telephone   varchar(50) not null,
    email       varchar(50) not null unique
);

create table promotion
(
    identifiant varchar(50) not null
        primary key
);

create table groupe
(
    identifiant           varchar(50) not null
        primary key,
    identifiant_promotion varchar(50) not null,
    constraint Classe_Promotion_FK
        foreign key (identifiant_promotion) references promotion (identifiant)
);

create table cours
(
    code               int auto_increment
        primary key,
    nom                varchar(50)  not null,
    description        varchar(500) not null,
    annee              int          not null,
    coefficient        float        not null,
    pourcentage_de     float        not null,
    pourcentage_tp     float        not null,
    pourcentage_projet float        not null,
    identifiant_groupe varchar(50)  not null,
    constraint Cours_Groupe_FK
        foreign key (identifiant_groupe) references groupe (identifiant)
);

create table dispense
(
    matricule_professeur int not null,
    code_cours           int not null,
    primary key (matricule_professeur, code_cours),
    constraint Dispense_Cours_FK
        foreign key (code_cours) references cours (code)
            on delete cascade,
    constraint Dispense_Professeur_FK
        foreign key (matricule_professeur) references professeur (matricule)
            on delete cascade
);

create table responsable
(
    numero      int auto_increment
        primary key,
    nom         varchar(50) not null,
    prenom      varchar(50) not null,
    numero_rue  varchar(50) not null,
    rue         varchar(50) not null,
    code_postal varchar(50) not null,
    ville       varchar(50) not null,
    telephone   varchar(50) not null,
    email       varchar(50) not null unique
);

create table eleve
(
    matricule          int auto_increment
        primary key,
    nom                varchar(50)      not null,
    prenom             varchar(50)      not null,
    date_naissance     date             not null,
    ville_naissance    varchar(50)      not null,
    pays_naissance     varchar(50)      not null,
    sexe               varchar(50)      not null,
    photo              blob             null,
    numero_rue         varchar(50)      not null,
    rue                varchar(50)      not null,
    code_postal        varchar(50)      not null,
    ville              varchar(50)      not null,
    telephone          varchar(50)      not null,
    email              varchar(50)      not null,
    identifiant_groupe varchar(50)      not null,
    numero_responsable int              not null,
    annee              int default 2016 null,
    constraint Eleve_Groupe_FK
        foreign key (identifiant_groupe) references groupe (identifiant),
    constraint Eleve_Responsable0_FK
        foreign key (numero_responsable) references responsable (numero)
);

create table epreuve
(
    numero          int auto_increment
        primary key,
    type            varchar(50) not null,
    note            int         not null,
    matricule_eleve int         not null,
    constraint Epreuve_Eleve_FK
        foreign key (matricule_eleve) references eleve (matricule)
            on update cascade on delete cascade
);

create table possede
(
    numero_epreuve int not null,
    code_cours     int not null,
    primary key (numero_epreuve, code_cours),
    constraint Possede_Cours_FK
        foreign key (code_cours) references cours (code)
            on delete cascade,
    constraint Possede_Epreuve_FK
        foreign key (numero_epreuve) references epreuve (numero)
            on delete cascade
);