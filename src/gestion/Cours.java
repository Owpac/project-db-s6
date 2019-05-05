package gestion;

import app.Database;
import app.Input;
import app.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

import static app.Constants.*;

public class Cours {

    private static Database database;

    private static String nom;
    private static String description;
    private static int annee;
    private static float coefficient;
    private static float pourcentage_de;
    private static float pourcentage_tp;
    private static float pourcentage_projet;
    private static String identifiant_groupe;


    public Cours() {
        database = new Database();
    }

    public Cours(Database database) {
        Cours.database = database;
    }

    private static void setNom() {
        nom = Input.askString( "Saisissez le nom du cours > ", 2, 15 );
    }

    private static void setDescription() {
        description = Input.askString( "Saisissez la description du cours > ", 2, 44 );
    }

    private static void setAnnee() {
        annee = Input.askInt( "Saisissez l'annee du cours > ", 2019, 2020 );
    }

    private static void setCoefficient() {
        coefficient = Input.askFloat( "Saisissez le coefficient du cours > ", 1, 5 );
    }

    private static void setPourcentage_de() {
        pourcentage_de = Input.askFloat( "Saisissez le pourcentage du DE du cours > ", 1, 5 );
    }

    private static void setPourcentage_tp() {
        pourcentage_tp = Input.askFloat( "Saisissez le pourcentage du TP du cours > ", 1, 5 );
    }

    private static void setPourcentage_projet() {
        pourcentage_projet = Input.askFloat( "Saisissez le pourcentage du projet du cours > ", 1, 5 );
    }

    private static void setIdentifiant_groupe() {
        identifiant_groupe = Statement.askQuery( database, "groupe", "Saisissez la classe du cours > " );
    }

    private static void query() {
        setNom();
        setDescription();
        setAnnee();
        setCoefficient();
        setPourcentage_de();
        setPourcentage_tp();
        setPourcentage_projet();
        setIdentifiant_groupe();
    }

    public static void add() {
        query();
        String query = Statement.add( DEF_TABLE_COURS, nom, description, annee, coefficient, pourcentage_de, pourcentage_tp, pourcentage_projet, identifiant_groupe );
        database.execute( query );

        int id = 0;

        try (java.sql.Statement statement = database.getConnexion().createStatement()) {
            ResultSet result = statement.executeQuery( "select LAST_INSERT_ID()" );
            result.next();
            id = result.getInt( 1 );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Dispense.add( id );
    }

    public static void update() {
        String id = Statement.askQuery( database, "cours", "Choisissez un cours a modifier > ", NBR_COLUMNS_COURS );
        System.out.println();
        System.out.println( "0) Annuler." );
        System.out.println( "1) Nom du cours." );
        System.out.println( "2) Description du cours." );
        System.out.println( "3) Annee du cours." );
        System.out.println( "4) Coefficient du cours." );
        System.out.println( "5) Pourcentage du DE du cours." );
        System.out.println( "6) Pourcentage du TP du cours." );
        System.out.println( "7) Pourcentage du Projet du cours." );
        System.out.println( "8) Le groupe du cours." );
        System.out.println( "9) Tout." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 0, 9 );
        System.out.println();

        switch (answer) {
            case 0:
                break;

            case 1:
                setNom();
                database.execute( Statement.update( "cours", "nom", nom, "code", id ) );
                break;

            case 2:
                setDescription();
                database.execute( Statement.update( "cours", "description", description, "code", id ) );
                break;

            case 3:
                setAnnee();
                database.execute( Statement.update( "cours", "annee", String.valueOf( annee ), "code", id ) );
                break;

            case 4:
                setCoefficient();
                database.execute( Statement.update( "cours", "coefficient", String.valueOf( coefficient ), "code", id ) );
                break;

            case 5:
                setPourcentage_de();
                database.execute( Statement.update( "cours", "pourcentage_de", String.valueOf( pourcentage_de ), "code", id ) );
                break;

            case 6:
                setPourcentage_tp();
                database.execute( Statement.update( "cours", "pourcentage_tp", String.valueOf( pourcentage_tp ), "code", id ) );
                break;

            case 7:
                setPourcentage_projet();
                database.execute( Statement.update( "cours", "pourcentage_projet", String.valueOf( pourcentage_projet ), "code", id ) );
                break;

            case 8:
                setIdentifiant_groupe();
                database.execute( Statement.update( "cours", "identifiant_groupe", identifiant_groupe, "code", id ) );
                break;

            case 9:
                query();
                database.execute( Statement.update( "cours", 8,"nom", nom, "description", description, "annee", String.valueOf( annee ), "coefficient", String.valueOf( coefficient ), "pourcentage_de", String.valueOf( pourcentage_de ), "pourcentage_tp", String.valueOf( pourcentage_tp ), "pourcentage_projet", String.valueOf( pourcentage_projet ), "identifiant_groupe", identifiant_groupe, "code", id ) );
                break;
        }
    }

    public static void remove() {
        String id = Statement.askQuery( database, "cours", "Choisissez un cours a supprimer > ", NBR_COLUMNS_DISPENSE );
        String query = Statement.remove( "cours", "code", id );
        database.execute( query );
    }
}
