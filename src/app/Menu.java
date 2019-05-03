package app;

import gestion.Groupe;

import static app.Constants.NBR_COLUMNS_GROUPE;

public class Menu {

    private Database database;

    public Menu() {
        this.database = new Database();

        do {
            login();
        } while (isOk( "Voulez-vous continuer ? (oui/non)" ));
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
                administrateur();
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

    public void administrateur() {
        System.out.println();
        System.out.println( "0) Retour." );
        System.out.println( "1) Gestion des groupes." );
        int answer = Input.askInt( "Action > ", 0, 2 );

        switch (answer) {
            case 0:
                break;

            case 1:
                manageGroup();
                break;

            case 2:
                System.out.println( "OK" );
                break;
        }
    }

    public void manageGroup() {

        Groupe group = new Groupe();
        String id;

        Statement.printQuery( this.database, "groupe", NBR_COLUMNS_GROUPE );

        System.out.println( "0) Retour." );
        System.out.println( "1) Ajouter un groupe." );
        System.out.println( "2) Modifer un groupe." );
        System.out.println( "3) Supprimer un groupe." );
        int answer = Input.askInt( "Que voulez-vous faire > ", 0, 3 );

        switch (answer) {
            case 0:
                break;

            case 1:
                do {
                    group.add();
                } while (isOk( "Recommencer ?" ));
                break;

            case 2:
                do {
                    id = Statement.askQuery( this.database, "groupe", "Choisissez un groupe a modifier > ", NBR_COLUMNS_GROUPE );
                    group.update( id );
                } while (isOk( "Recommencer ?" ));
                break;

            case 3:
                do {
                    id = Statement.askQuery( this.database, "groupe", "Choisissez un groupe a supprimer > ", NBR_COLUMNS_GROUPE );
                    group.remove( id );
                } while (isOk( "Recommencer ?" ));
                break;

            default:
                System.out.println( "Votre reponse ne correspond pas aux choix disponibles." );
        }
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
