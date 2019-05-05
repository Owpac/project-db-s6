package gestion;

import app.Database;
import app.Statement;
import app.Input;

import static app.Constants.*;


public class Groupe {

    private static Database database;

    private static String identifiant;
    private static String identifiant_promotion;

    public Groupe() {
        database = new Database();
    }

    public Groupe(Database database) {
        Groupe.database = database;
    }

    private static void setIdentifiant() {
        identifiant = Input.askString( "Saisissez le nom de votre groupe > ", 2, 15 );
    }

    private static void setIdentifiant_promotion() {
        identifiant_promotion = Statement.askQuery( "promotion", "Choisissez la promotion de votre groupe > " );
    }

    private static void query() {
        setIdentifiant();
        setIdentifiant_promotion();
    }

    public static void add() {
        System.out.println();
        query();
        String query = Statement.add( DEF_TABLE_GROUPE, identifiant, identifiant_promotion );
        database.execute( query );
    }

    public static void update() {
        String id = Statement.askQuery( "groupe", "Choisissez un groupe a modifier > ", NBR_COLUMNS_GROUPE );
        System.out.println();
        System.out.println( "1) Identifiant du groupe." );
        System.out.println( "2) Promotion du groupe." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 1, 2 );
        System.out.println();

        switch (answer) {
            case 1:
                setIdentifiant();
                database.execute( Statement.update( "groupe", 1, "identifiant", identifiant, "identifiant", id ) );
                break;

            case 2:
                setIdentifiant_promotion();
                database.execute( Statement.update( "groupe", 1, "identifiant_promotion", identifiant_promotion, "identifiant", id ) );
                break;

            case 3:
                query();
                database.execute( Statement.update( "groupe", 2, "identifiant", identifiant, "identifiant_promotion", identifiant_promotion, "identifiant", id ) );
        }
    }

    public static void remove() {
        String id = Statement.askQuery( "groupe", "Choisissez un groupe a supprimer > ", NBR_COLUMNS_GROUPE );
        String query = Statement.remove( "groupe", "identifiant", id );
        database.execute( query );
    }

    public static void updateGroupOfStudent() {
        String group = Statement.askQuery( "groupe", "Choisissez un groupe > " );

        String query = Statement.select( "eleve", "identifiant_groupe", "!=", group );
        String student = Statement.askQuery( query, "eleve", "Choisissez un eleve a ajouter au groupe \"" + group + "\" > ", NBR_COLUMNS_MIN );

        database.execute( Statement.update( "eleve", 1, "identifiant_groupe", group, "matricule", student ) );
    }
}
