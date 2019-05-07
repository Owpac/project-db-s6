package gestion;

import app.Database;
import app.Statement;
import app.Input;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        query();
        String query = Statement.add( DEF_TABLE_GROUPE, identifiant, identifiant_promotion );
        database.execute( query );
    }

    public static void update() {
        String id = Statement.askQuery( "groupe", "Choisissez un groupe a modifier > ", NBR_COLUMNS_GROUPE );
        System.out.println();
        System.out.println( "0) Annuler." );
        System.out.println( "1) Identifiant du groupe." );
        System.out.println( "2) Promotion du groupe." );
        System.out.println( "3) Tout." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 0, 3 );
        System.out.println();

        switch (answer) {
            case 0:
                break;

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
        String idGroup = Statement.askQuery( "groupe", "Choisissez un groupe a supprimer > ", NBR_COLUMNS_GROUPE );
        String query;

        for (int i = 0; i < 2; i++) {

            if (i == 0) {
                query = Statement.select( "eleve", "identifiant_groupe", "=", idGroup );
            } else {
                query = Statement.select( "cours", "identifiant_groupe", "=", idGroup );
            }

            try (java.sql.Statement statement = database.getConnection().createStatement()) {
                ResultSet result = statement.executeQuery( query );

                if (i == 0) {
                    Statement.printQuery( query, "eleve", NBR_COLUMNS_MIN );
                    query = Statement.select( "groupe", "identifiant", "!=", idGroup );

                    while (result.next()) {
                        String idStudent = result.getString( 1 );
                        String idNewGroup = Statement.askQuery( query, "groupe", "Saisissez le nouveau groupe de l'eleve n°" + idStudent + " > ", NBR_COLUMNS_GROUPE );
                        Eleve.updateGroup( idStudent, idNewGroup );
                    }
                } else {
                    Statement.printQuery( query, "cours", NBR_COLUMNS_MIN );
                    query = Statement.select( "groupe", "identifiant", "!=", idGroup );

                    while (result.next()) {
                        String idClass = result.getString( 1 );
                        String idNewGroup = Statement.askQuery( query, "groupe", "Saisissez le nouveau groupe de la class n°" + idClass + " > ", NBR_COLUMNS_GROUPE );
                        Cours.updateGroup( idClass, idNewGroup );
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        database.execute( Statement.remove( "groupe", "identifiant", idGroup ) );
    }

    public static void updateGroupOfStudent() {
        String group = Statement.askQuery( "groupe", "Choisissez un groupe > " );

        String query = Statement.select( "eleve", "identifiant_groupe", "!=", group );
        String student = Statement.askQuery( query, "eleve", "Choisissez un eleve a ajouter au groupe \"" + group + "\" > ", NBR_COLUMNS_MIN );

        database.execute( Statement.update( "eleve", 1, "identifiant_groupe", group, "matricule", student ) );
    }
}
