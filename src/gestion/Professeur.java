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
        nom = Input.askString( "Saisissez le nom du professeur > ", 2, 50 );
    }

    private static void setPrenom() {
        prenom = Input.askString( "Saisissez le prenom du professeur > ", 2, 50 );
    }

    private static void setNumero_rue() {
        numero_rue = Input.askString( "Saisissez le numero de rue du professeur > ",  1, 50 );
    }

    private static void setRue() {
        rue = Input.askString( "Saisissez la rue du professeur > ", 2, 50 );
    }

    private static void setCode_postal() {
        code_postal = Input.askString( "Saisissez le code postal du professeur > ", 2, 50 );
    }

    private static void setVille() {
        ville = Input.askString( "Saisissez la ville du professeur > ", 2, 50 );
    }

    private static void setTelephone() {
        telephone = Input.askString( "Saisissez le telephone du professeur > ", 2, 50 );
    }

    private static void setEmail() {
        email = Input.askString( "Saisissez l'email du professeur > ", 2, 50 );
    }

    private static void query() {
        setNom();
        setPrenom();
        setNumero_rue();
        setRue();
        setCode_postal();
        setVille();
        setTelephone();
        setEmail();
    }

    public static void add() {
        query();
        String query = Statement.add( DEF_TABLE_PROFESSEUR, nom, prenom, numero_rue, rue, code_postal, ville,
                telephone, email );
        database.execute( query );
    }

    public static void update() {
        String id = Statement.askQuery( "professeur", "Choisissez le professeur a modifier > " );
        System.out.println();
        System.out.println( "0) Annuler." );
        System.out.println( "1) Nom du professeur." );
        System.out.println( "2) Prenom du professeur." );
        System.out.println( "3) Numero de rue du professeur." );
        System.out.println( "4) Rue du professeur." );
        System.out.println( "5) Code postal du professeur." );
        System.out.println( "6) Ville du professeur." );
        System.out.println( "7) Telephone du professeur." );
        System.out.println( "8) Email du professeur." );
        System.out.println( "9) Tout." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 0, 8 );
        System.out.println();

        switch (answer) {
            case 0:
                break;

            case 1:
                setNom();
                database.execute( Statement.update( "professeur", 1, "nom", nom, "matricule", id ) );
                break;

            case 2:
                setPrenom();
                database.execute( Statement.update( "professeur", 1, "prenom", prenom, "matricule", id ) );
                break;

            case 3:
                setNumero_rue();
                database.execute( Statement.update( "professeur", 1, "numero_rue", numero_rue, "matricule", id ) );
                break;

            case 4:
                setRue();
                database.execute( Statement.update( "professeur", 1, "rue", rue, "matricule", id ) );
                break;

            case 5:
                setCode_postal();
                database.execute( Statement.update( "professeur", 1, "code_postal", code_postal, "matricule", id ) );
                break;

            case 6:
                setVille();
                database.execute( Statement.update( "professeur", 1, "ville", ville, "matricule", id ) );
                break;

            case 7:
                setTelephone();
                database.execute( Statement.update( "professeur", 1, "telephone", telephone, "matricule", id ) );
                break;

            case 8:
                setEmail();
                database.execute( Statement.update( "professeur", 1, "email", email, "matricule", id ) );
                break;

            case 9:
                query();
                database.execute( Statement.update( "professeur", 8, "nom", nom, "prenom", prenom, "numero_rue",
                        numero_rue, "rue", rue, "code_postal", code_postal, "ville", ville, "telephone", telephone,
                        "email", email, "matricule", id ) );
                break;
        }
    }

    public static void remove() {
        String id = Statement.askQuery( "professeur", "Choisissez un professeur a supprimer > " );
        String query = Statement.remove( "professeur", "matricule", id );
        database.execute( query );
    }
}
