/* Florian ERNST - Thomas FAUGIER */
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
        identifiant_groupe = Statement.askQuery( "groupe", "Saisissez la classe du cours > " );
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
        String query = Statement.add( DEF_TABLE_COURS, nom, description, annee, coefficient, pourcentage_de,
                pourcentage_tp, pourcentage_projet, identifiant_groupe );
        database.execute( query );

        Dispense.add( Statement.lastUpdate() );
    }

    public static void update() {
        String id = Statement.askQuery( "cours", "Choisissez un cours a modifier > " );
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
                database.execute( Statement.update( "cours", 1, "nom", nom, "code", id ) );
                break;

            case 2:
                setDescription();
                database.execute( Statement.update( "cours", 1, "description", description, "code", id ) );
                break;

            case 3:
                setAnnee();
                database.execute( Statement.update( "cours", 1, "annee", annee, "code", id ) );
                break;

            case 4:
                setCoefficient();
                database.execute( Statement.update( "cours", 1, "coefficient", coefficient, "code", id ) );
                break;

            case 5:
                setPourcentage_de();
                database.execute( Statement.update( "cours", 1, "pourcentage_de", pourcentage_de, "code", id ) );
                break;

            case 6:
                setPourcentage_tp();
                database.execute( Statement.update( "cours", 1, "pourcentage_tp", pourcentage_tp, "code", id ) );
                break;

            case 7:
                setPourcentage_projet();
                database.execute( Statement.update( "cours", 1, "pourcentage_projet", pourcentage_projet, "code",
                        id ) );
                break;

            case 8:
                setIdentifiant_groupe();
                database.execute( Statement.update( "cours", 1, "identifiant_groupe", identifiant_groupe, "code",
                        id ) );
                break;

            case 9:
                query();
                database.execute( Statement.update( "cours", 8, "nom", nom, "description", description, "annee",
                        annee, "coefficient", coefficient, "pourcentage_de", pourcentage_de, "pourcentage_tp",
                        pourcentage_tp, "pourcentage_projet", pourcentage_projet, "identifiant_groupe",
                        identifiant_groupe, "code", id ) );
                break;
        }
    }

    public static void updateGroup(String idClass, String idNewGroup) {
        database.execute( Statement.update( "cours", 1, "identifiant_groupe", idNewGroup, "code", idClass ) );
    }

    public static void remove() {
        String id = Statement.askQuery( "cours", "Choisissez un cours a supprimer > " );
        String query = Statement.remove( "cours", "code", id );
        database.execute( query );
    }
}
