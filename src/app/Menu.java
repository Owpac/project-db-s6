package app;

import gestion.Cours;
import gestion.Dispense;
import gestion.Groupe;
import gestion.Professeur;

import static app.Constants.*;

public class Menu {

    private static Database database;

    public Menu() {
        database = new Database();

        new Statement( database );
        new Groupe( database );
        new Cours( database );
        new Dispense( database );
        new Professeur( database );

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
                break;

            case 3:
                break;

            default:
                System.out.println();
                System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
        }
    }

    public void administrator() {
        System.out.println();
        System.out.println( "0) Quitter." );
        System.out.println( "1) Gestion des groupes." );
        System.out.println( "2) Gestion des cours." );
        System.out.println( "3) Gestion des professeurs." );
        int answer = Input.askInt( "Action > ", 0, 3 );

        switch (answer) {
            case 0:
                break;

            case 1:
                manageGroup();
                break;

            case 2:
                manageClass();
                break;

            case 3:
                manageProfessor();
                break;
        }
    }

    public void manageGroup() {
        do {
            Statement.printQuery( "groupe", NBR_COLUMNS_GROUPE );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter un groupe." );
            System.out.println( "2) Modifer un groupe." );
            System.out.println( "3) Supprimer un groupe." );
            System.out.println( "4) Ajouter un eleve a un groupe." );
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
                    } while (isOk( "Voulez-vous ajouter un autre eleve à un groupe ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des groupes ? (oui/non)" ));
    }

    public void manageClass() {
        do {
            Statement.printQuery( "cours", NBR_COLUMNS_COURS );

            System.out.println( "0) Quitter." );
            System.out.println( "1) Ajouter un cours." );
            System.out.println( "2) Modifer un cours." );
            System.out.println( "3) Supprimer un cours." );
            System.out.println( "4) " );
            int answer = Input.askInt( "Que voulez-vous faire > ", 0, 4 );

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

                case 4:
                    do {

                    } while (isOk( "Voulez-vous ajouter un autre eleve à un groupe ? (oui/non)" ));
                    break;

                default:
                    System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
            }
        } while (!isOk( "Quitter la gestion des groupes ? (oui/non)" ));
    }

    public void manageProfessor() {
        do {
            Statement.printQuery( "professeur", NBR_COLUMNS_PROFESSEUR );

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
        } while (!isOk( "Quitter la gestion des groupes ? (oui/non)" ));
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
