package gestion;

import app.Database;
import app.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static app.Constants.*;

public class Possede {
    private static Database database;

    private static String code_cours;

    public Possede() {
        database = new Database();
    }

    public Possede(Database database) {
        Possede.database = database;
    }

    private static void setCode_cours() {
        code_cours = Statement.askQuery( "cours", "Saisissez le cours de l'epreuve > " );
    }

    static void setCode_cours(String idProfessor) {
        String query = Statement.join( "*", "cours", "dispense", "code=code_cours" ) + Statement.where( EQUAL, QUOTE,
                "matricule_professeur", idProfessor );
        code_cours = Statement.askQuery( query, "cours", "Saisissez le cours de l'epreuve > " );
    }

    static void setCode_coursMatricule(String matricule) {
        String query = Statement.join( "c.*", "cours c", "eleve e", "e.identifiant_groupe = c.identifiant_groupe" ) + Statement.where( EQUAL, "  ",
                "matricule", matricule );
        code_cours = Statement.askQuery( query, "cours", "Saisissez le cours de l'epreuve > " );
    }

    public static void add(String id) {
        setCode_cours();
        String query = Statement.add( DEF_TABLE_POSSEDE, id, code_cours );
        database.execute( query );
    }

    public static void addWithoutSetCours(String id) {
        String query = Statement.add( DEF_TABLE_POSSEDE, id, code_cours );
        database.execute( query );
    }

    public static void update(String id) {
        ArrayList<String> idTest = new ArrayList<>();
        String query = Statement.select( "possede" ) + Statement.where( EQUAL, QUOTE, "numero_epreuve", id );

        try (java.sql.Statement statement = database.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery( query );

            while (result.next()) {
                idTest.add( result.getString( 1 ) );
                idTest.add( result.getString( 2 ) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String idClasse = Statement.askQuery( "cours", "Choisissez la nouvelle matiere de l'epreuve > " );
        database.execute( Statement.update( "possede", 1, "code_cours", idClasse, "numero_epreuve", idTest.get( 0 ),
                "code_cours", idTest.get( 1 ) ) );
    }

    public static String getCode_cours()
    {
        return code_cours;
    }
}
