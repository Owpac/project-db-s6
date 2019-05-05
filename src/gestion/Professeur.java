package gestion;

import app.Database;
import app.Statement;
import app.Input;

import static app.Constants.DEF_TABLE_PROFESSEUR;

public class Professeur {
    private static Database database;

    private static String nom;
    private static String prenom;
    private static String numero_rue;
    private static String rue;
    private static String code_postal;
    private static String ville;
    private static String telephone;
    private static String email;

    public Professeur() {
        database = new Database();
    }

    public Professeur(Database database) {
        Professeur.database = database;
    }

    private static void setNom() {
        nom = Input.askString( "Saisissez le nom du professeur > ", 2, 15 );
    }

    private static void setPrenom() {
        prenom = Input.askString( "Saisissez le prenom du professeur > ", 2, 15 );
    }

    private static void setNumero_rue() {
        numero_rue = Input.askString( "Saisissez le numero de rue du professeur > ", 2, 15 );
    }

    private static void setRue() {
        rue = Input.askString( "Saisissez la rue du professeur > ", 2, 15 );
    }

    private static void setCode_postal() {
        code_postal = Input.askString( "Saisissez le code postal du professeur > ", 2, 15 );
    }

    private static void setVille() {
        ville = Input.askString( "Saisissez la ville du professeur > ", 2, 15 );
    }

    private static void setTelephone() {
        telephone = Input.askString( "Saisissez le telephone du professeur > ", 2, 15 );
    }

    private static void setEmail() {
        email = Input.askString( "Saisissez l'email du professeur > ", 2, 15 );
    }

    private void query() {
        setNom();
        setPrenom();
        setNumero_rue();
        setRue();
        setCode_postal();
        setVille();
        setTelephone();
        setEmail();
    }

    public void add() {
        query();
        String query = Statement.add( DEF_TABLE_PROFESSEUR, nom, prenom, numero_rue, rue, code_postal, ville, telephone, email );
        database.execute( query );
        Dispense.add();
    }
}
