package app;

import gestion.*;

import static app.Constants.EQUAL;
import static app.Constants.QUOTE;

public class Menu {

    private static Database database;

    public Menu() {
        database = new Database();

        new Statement( database );
        new Groupe( database );
        new Cours( database );
        new Dispense( database );
        new Professeur( database );
        new Eleve( database );
        new Epreuve( database );
        new Possede( database );

        do {
            login();
        } while (!isOk( "Quitter le programme ? (oui/non)" ));
    }

    public void login() {
        System.out.println();
        System.out.println( "0) Quitter." );
        System.out.println( "1) Administrateur." );
        System.out.println( "2) Professeur." );
        System.out.println( "3) Eleve." );
        int answer = Input.askInt( "Login > ", 0, 3 );

        switch (answer) {
            case 0:
                System.exit( 0 );
                break;

            case 1:
                administrator();
                break;

            case 2:
                professor();
                break;

            case 3:
                student();
                break;
        }
    }

    public void administrator() {
        boolean disconnect = false;
        do {
            System.out.println();
            System.out.println( "0) Quitter." );
            System.out.println( "1) Gestion." );
            System.out.println( "2) Recherche." );
            System.out.println( "3) Affichage." );
            int answer = Input.askInt( "Action > ", 0, 3 );

            switch (answer) {
                case 0:
                    disconnect = isOk( "Se deconnecter ? (oui/non)" );
                    break;

                case 1:
                    manage();
                    break;

                case 2:
                    find();
                    break;

                case 3:
                    print();
                    break;
            }
        } while (!disconnect);
    }

    public void professor() {
        boolean disconnect = false;
        do {
            String idProfessor = Statement.askQuery( "professeur", "Quel professeur etes-vous ? " );

            System.out.println();
            System.out.println( "0) Quitter." );
            System.out.println( "1) Gestion des epreuves." );
            System.out.println( "2) Afficher le releve de note d'un eleve." );
            System.out.println( "3) Rechercher." );
            int answer = Input.askInt( "Action > ", 0, 3 );

            switch (answer) {
                case 0:
                    disconnect = isOk( "Se deconnecter ? (oui/non)" );
                    break;

                case 1:
                    manageTest( idProfessor );
                    break;

                case 2:
                    Professeur.printGrades( idProfessor );
                    break;

                case 3:
                    find();
                    break;
            }
        } while (!disconnect);
    }

    public void student() {
        boolean disconnect = false;
        do {
            String query = Statement.join( "matricule,eleve.nom,eleve.prenom,date_naissance,ville_naissance," +
                    "pays_naissance,sexe,eleve.numero_rue,eleve.rue,eleve.code_postal,eleve.ville,eleve" +
                    ".telephone,eleve.email,eleve.annee,identifiant_groupe,responsable.nom,responsable" + ".prenom",
                    "eleve", "responsable", "eleve.numero_responsable=responsable.numero" );
            String idStudent = Statement.askQuery( query, "eleve", "Quel eleve etes-vous ? " );

            System.out.println();
            System.out.println( "0) Quitter." );
            System.out.println( "1) Afficher mon releve de notes." );
            System.out.println( "2) Rechercher." );
            int answer = Input.askInt( "Action > ", 0, 1 );

            switch (answer) {
                case 0:
                    disconnect = isOk( "Se deconnecter ? (oui/non)" );
                    break;

                case 1:
                    Eleve.printGrades( idStudent );
                    break;

                case 2:
                    find();
                    break;
            }
        } while (!disconnect);
    }

    public void manage() {
        System.out.println();
        System.out.println( "0) Quitter." );
        System.out.println( "1) Gestion des groupes." );
        System.out.println( "2) Gestion des eleves." );
        System.out.println( "3) Gestion des cours." );
        System.out.println( "4) Gestion des professeurs." );
        System.out.println( "5) Gestion des professeurs d'un cours." );
        System.out.println( "6) Gestion des epreuves." );
        int answer = Input.askInt( "Action > ", 0, 6 );

        switch (answer) {
            case 0:
                break;

            case 1:
                manageGroup();
                break;

            case 2:
                manageStudent();
                break;

            case 3:
                manageClass();
                break;

            case 4:
                manageProfessor();
                break;

            case 5:
                manageProfessorOfClass();
                break;

            case 6:
                manageTest();
                break;
        }
    }

    public void find() {
        System.out.println();
        System.out.println( "0) Quitter." );
        System.out.println( "1) Rechercher par promotions." );
        System.out.println( "2) Rechercher par groupes." );
        int answer = Input.askInt( "Action > ", 0, 2 );

        switch (answer) {
            case 0:
                break;

            case 1:
                Eleve.findByPromotions();
                break;

            case 2:
                Eleve.findByGroups();
                break;
        }
    }

    public void print() {
        System.out.println();
        System.out.println( "0) Quitter." );
        System.out.println( "1) Afficher le relevé de note d'un eleve." );
        int answer = Input.askInt( "Action > ", 0, 1 );

        switch (answer) {
            case 0:
                break;

            case 1:
                Eleve.printGrades();
                break;
        }
    }

    public void manageGroup() {
        do {
            Statement.printQuery( "groupe" );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter un groupe." );
            System.out.println( "2) Modifer un groupe." );
            System.out.println( "3) Supprimer un groupe." );
            System.out.println( "4) Mise a jour des eleves d'un groupe." );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 4 );

            switch (answer) {
                case 0:
                    break;

                case 1:
                    do {
                        Groupe.add();
                    } while (isOk( "Voulez-vous ajouter un autre groupe ? (oui/non)" ));
                    break;

                case 2:
                    do {
                        Groupe.update();
                    } while (isOk( "Voulez-vous modifier un autre groupe ? (oui/non)" ));
                    break;

                case 3:
                    do {
                        Groupe.remove();
                    } while (isOk( "Voulez-vous supprimer un autre groupe ? (oui/non)" ));
                    break;

                case 4:
                    do {
                        Groupe.updateGroupOfStudent();
                    } while (isOk( "Voulez-vous ajouter un autre eleve a un groupe ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des groupes ? (oui/non)" ));
    }

    public void manageStudent() {
        do {
            String query = Statement.join( "matricule,eleve.nom,eleve.prenom,date_naissance,ville_naissance," +
                    "pays_naissance,sexe,eleve.numero_rue,eleve.rue,eleve.code_postal,eleve.ville,eleve" +
                    ".telephone,eleve.email,eleve.annee,identifiant_groupe,responsable.nom,responsable" + ".prenom",
                    "eleve", "responsable", "eleve.numero_responsable=responsable.numero" );

            Statement.printQuery( query, "eleve" );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter un eleve." );
            System.out.println( "2) Modifer un eleve." );
            System.out.println( "3) Supprimer un eleve." );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 3 );

            switch (answer) {
                case 0:
                    break;

                case 1:
                    do {
                        Eleve.add();
                    } while (isOk( "Voulez-vous ajouter un autre eleve ? (oui/non)" ));
                    break;

                case 2:
                    do {
                        Eleve.update();
                    } while (isOk( "Voulez-vous modifier un autre eleve ? (oui/non)" ));
                    break;

                case 3:
                    do {
                        Eleve.remove();
                    } while (isOk( "Voulez-vous supprimer un autre eleve ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des eleves ? (oui/non)" ));
    }

    public void manageClass() {
        do {
            Statement.printQuery( "cours" );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter un cours." );
            System.out.println( "2) Modifer un cours." );
            System.out.println( "3) Supprimer un cours." );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 3 );

            switch (answer) {
                case 0:
                    break;

                case 1:
                    do {
                        Cours.add();
                    } while (isOk( "Voulez-vous ajouter un autre cours ? (oui/non)" ));
                    break;

                case 2:
                    do {
                        Cours.update();
                    } while (isOk( "Voulez-vous modifier un autre cours ? (oui/non)" ));
                    break;

                case 3:
                    do {
                        Cours.remove();
                    } while (isOk( "Voulez-vous supprimer un autre cours ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des cours ? (oui/non)" ));
    }

    public void manageProfessor() {
        do {
            Statement.printQuery( "professeur" );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter un professeur." );
            System.out.println( "2) Modifer un professeur." );
            System.out.println( "3) Supprimer un professeur." );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 3 );

            switch (answer) {
                case 0:
                    break;

                case 1:
                    do {
                        Professeur.add();
                    } while (isOk( "Voulez-vous ajouter un autre professeur ? (oui/non)" ));
                    break;

                case 2:
                    do {
                        Professeur.update();
                    } while (isOk( "Voulez-vous modifier un autre professeur ? (oui/non)" ));
                    break;

                case 3:
                    do {
                        Professeur.remove();
                    } while (isOk( "Voulez-vous supprimer un autre professeur ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des professeurs ? (oui/non)" ));
    }

    public void manageProfessorOfClass() {
        do {
            String query = Statement.join( "matricule_professeur, code_cours, professeur.nom, prenom, cours.nom",
                    "dispense", "cours", "dispense.code_cours = cours.code", "professeur", "dispense" +
                            ".matricule_professeur = professeur.matricule" );

            Statement.printQuery( query, "dispense" );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter un professeur a un cours." );
            System.out.println( "2) Modifier le professeur d'un cours." );
            System.out.println( "3) Supprimer le professeur d'un cours." );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 3 );

            switch (answer) {
                case 0:
                    break;

                case 1:
                    do {
                        Dispense.add();
                    } while (isOk( "Voulez-vous ajouter un autre professeur a un cours ? (oui/non)" ));
                    break;

                case 2:
                    do {
                        Dispense.update();
                    } while (isOk( "Voulez-vous modifier un autre professeur d'un cours ? (oui/non)" ));
                    break;

                case 3:
                    do {
                        Dispense.remove();
                    } while (isOk( "Voulez-vous supprimer un autre professeur d'un cours ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des professeurs responsables des cours ? (oui/non)" ));
    }

    public void manageTest() {
        do {
            String query = Statement.join( "numero, type, note, eleve.prenom, eleve.nom, cours.nom", "epreuve",
                    "possede", "epreuve.numero=possede.numero_epreuve", "dispense", "possede.code_cours=dispense" +
                            ".code_cours", "cours", "possede.code_cours = cours.code", "eleve", "epreuve" +
                            ".matricule_eleve = eleve.matricule" );
            Statement.printQuery( query, "epreuve" );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter une epreuve." );
            System.out.println( "2) Modifier une epreuve." );
            System.out.println( "3) Supprimer une epreuve." );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 3 );

            switch (answer) {
                case 0:
                    break;

                case 1:
                    do {
                        Epreuve.add();
                    } while (isOk( "Voulez-vous ajouter une autre epreuve ? (oui/non)" ));
                    break;

                case 2:
                    do {
                        Epreuve.update();
                    } while (isOk( "Voulez-vous modifier une autre epreuve ? (oui/non)" ));
                    break;

                case 3:
                    do {
                        Epreuve.remove();
                    } while (isOk( "Voulez-vous supprimer une autre epreuve ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des epreuves ? (oui/non)" ));
    }

    public void manageTest(String idProfessor) {
        do {
            String query = Statement.join( "numero, type, note, eleve.prenom, eleve.nom, cours.nom", "epreuve",
                    "possede", "epreuve.numero=possede.numero_epreuve", "dispense", "possede.code_cours=dispense" +
                            ".code_cours", "cours", "possede.code_cours = cours.code", "eleve", "epreuve" +
                            ".matricule_eleve = eleve.matricule" ) + Statement.where( EQUAL, QUOTE,
                    "matricule_professeur", idProfessor );
            Statement.printQuery( query, "epreuve" );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter une epreuve." );
            System.out.println( "2) Modifier une epreuve." );
            System.out.println( "3) Supprimer une epreuve." );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 3 );

            switch (answer) {
                case 0:
                    break;

                case 1:
                    do {
                        Epreuve.add( idProfessor );
                    } while (isOk( "Voulez-vous ajouter une autre epreuve ? (oui/non)" ));
                    break;

                case 2:
                    do {
                        Epreuve.update( query );
                    } while (isOk( "Voulez-vous modifier une autre epreuve ? (oui/non)" ));
                    break;

                case 3:
                    do {
                        Epreuve.remove( idProfessor );
                    } while (isOk( "Voulez-vous supprimer une autre epreuve ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des epreuves ? (oui/non)" ));
    }

    public boolean isOk(String message) {
        String isOk;

        do {
            System.out.println();
            System.out.println( message );
            isOk = Input.askString( "> ", 1, 3 );
        } while (!isOk.equals( "oui" ) && !isOk.equals( "non" ) && !isOk.equals( "Oui" ) && !isOk.equals( "Non" ) && !isOk.equals( "o" ) && !isOk.equals( "n" ));

        return isOk.matches( "^([oO]).?.?" );
    }
}
