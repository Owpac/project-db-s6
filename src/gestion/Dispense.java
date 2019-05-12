package gestion;

import app.Database;
import app.Input;
import app.Statement;

import java.util.ArrayList;

import static app.Constants.*;

public class Dispense {
    private static Database database;

    private static String matricule_professeur;
    private static String code_cours;

    public Dispense() {
        database = new Database();
    }

    public Dispense(Database database) {
        Dispense.database = database;
    }

    private static void setMatricule_professeur() {
        matricule_professeur = Statement.askQuery( "professeur", "Saisissez l'enseignant du cours > " );
    }

    private static void setCode_cours() {
        setMatricule_professeur();
        setCode_cours( matricule_professeur );

    }

    private static void setCode_cours(String idProfesseur) {
        String queryDispense = Statement.select( "code_cours", "dispense" ) + Statement.where( EQUAL, QUOTE,
                "matricule_professeur", idProfesseur );

        String query = Statement.select( "*", "cours" ) + Statement.where( NOT_IN, PARENTHESIS, "code", queryDispense );
        code_cours = Statement.askQuery( query, "cours", "Saisissez un cours > " );
    }

    private static void query() {
        setMatricule_professeur();
        setCode_cours();
    }

    public static void add() {
        query();
        String query = Statement.add( DEF_TABLE_DISPENSE, matricule_professeur, code_cours );
        database.execute( query );
    }

    public static void add(String id) {
        setMatricule_professeur();
        String query = Statement.add( DEF_TABLE_DISPENSE, matricule_professeur, id );
        database.execute( query );
    }

    public static void update() {
        Statement.printQuery( "professeur" );
        Statement.printQuery( "cours" );
        ArrayList<String> id = Statement.askQueries( "dispense", "Choisissez l'enseignement a modifier > " );
        System.out.println();
        System.out.println( "0) Annuler." );
        System.out.println( "1) Professeur en charge du cours." );
        System.out.println( "2) Cours a enseigner." );
        System.out.println( "3) Tout." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 0, 3 );
        System.out.println();

        switch (answer) {
            case 0:
                break;

            case 1:
                setMatricule_professeur();
                database.execute( Statement.update( "dispense", 1, "matricule_professeur", matricule_professeur,
                        "matricule_professeur", id.get( 0 ), "code_cours", id.get( 1 ) ) );
                break;

            case 2:
                setCode_cours( id.get( 0 ) );
                database.execute( Statement.update( "dispense", 1, "code_cours", code_cours, "matricule_professeur",
                        id.get( 0 ), "code_cours", id.get( 1 ) ) );
                break;

            case 3:
                query();
                database.execute( Statement.update( "dispense", 2, "matricule_professeur", matricule_professeur,
                        "code_cours", code_cours, "matricule_professeur", id.get( 0 ), "code_cours", id.get( 1 ) ) );
                break;
        }
    }

    public static void remove() {
        ArrayList<String> id = Statement.askQueries( "dispense", "Choisissez l'enseignement a supprimer > " );
        String query = Statement.remove( "dispense", "matricule_professeur", id.get( 0 ), "code_cours", id.get( 1 ) );
        database.execute( query );
    }
}
