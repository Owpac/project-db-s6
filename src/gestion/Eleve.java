package gestion;

import app.Database;
import app.Input;
import app.Statement;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static app.Constants.*;

public class Eleve {
    private static Database database;

    private static String nom;
    private static String prenom;
    private static String date_naissance;
    private static String ville_naissance;
    private static String pays_naissance;
    private static String sexe;
    private static String numero_rue;
    private static String rue;
    private static String code_postal;
    private static String ville;
    private static String telephone;
    private static String email;
    private static String identifiant_groupe;
    private static String numero_responsable;

    public Eleve() {
        database = new Database();
    }

    public Eleve(Database database) {
        Eleve.database = database;
    }

    private static void setNom() {
        nom = Input.askString( "Saisissez le nom de l'eleve > ", 2, 15 );
    }

    private static void setPrenom() {
        prenom = Input.askString( "Saisissez le prenom de l'eleve > ", 2, 15 );
    }

    public static void setDate_naissance() {
        int year = Input.askInt( "Saisissez l'annee de naissance de l'eleve > ", 1900, 2020 );
        int month = Input.askInt( "Saisissez le mois de naissance de l'eleve > ", 1, 12 );
        int day = Input.askInt( "Saisissez le jour de naissance de l'eleve > ", 1, 31 );

        date_naissance = String.format( "%s-%s-%s", year, month, day );
    }

    public static void setVille_naissance() {
        ville_naissance = Input.askString( "Saisissez la ville de naissance de l'eleve > ", 2, 15 );
    }

    public static void setPays_naissance() {
        pays_naissance = Input.askString( "Saisissez le pays de naissance de l'eleve > ", 2, 15 );
    }

    public static void setSexe() {
        sexe = Input.askString( "Saisissez le sexe de l'eleve > ", 2, 15 );
    }

    private static void setNumero_rue() {
        numero_rue = Input.askString( "Saisissez le numero de rue de l'eleve > ", 2, 15 );
    }

    private static void setRue() {
        rue = Input.askString( "Saisissez la rue de l'eleve > ", 2, 15 );
    }

    private static void setCode_postal() {
        code_postal = Input.askString( "Saisissez le code postal de l'eleve > ", 2, 15 );
    }

    private static void setVille() {
        ville = Input.askString( "Saisissez la ville de l'eleve > ", 2, 15 );
    }

    private static void setTelephone() {
        telephone = Input.askString( "Saisissez le telephone de l'eleve > ", 2, 15 );
    }

    private static void setEmail() {
        email = Input.askString( "Saisissez l'email de l'eleve > ", 2, 15 );
    }

    public static void setIdentifiant_groupe() {
        identifiant_groupe = Statement.askQuery( "groupe", "Saisissez le groupe de l'eleve > " );
    }

    public static void setNumero_responsable() {
        numero_responsable = Statement.askQuery( "responsable", "Saisissez le responsable de l'eleve > " );
    }

    private static void query() {
        setNom();
        setPrenom();
        setDate_naissance();
        setVille_naissance();
        setPays_naissance();
        setSexe();
        setNumero_rue();
        setRue();
        setCode_postal();
        setVille();
        setTelephone();
        setEmail();
        setIdentifiant_groupe();
        setNumero_responsable();
    }

    public static void add() {
        query();
        String query = Statement.add( DEF_TABLE_ELEVE, nom, prenom, date_naissance, ville_naissance, pays_naissance,
                sexe, numero_rue, rue, code_postal, ville, telephone, email, identifiant_groupe, numero_responsable );
        database.execute( query );
    }

    public static void update() {
        String id = Statement.askQuery( "eleve", "Choisissez l'eleve a modifier > " );
        System.out.println();
        System.out.println( "0) Annuler." );
        System.out.println( "1) Nom de l'eleve." );
        System.out.println( "2) Prenom de l'eleve." );
        System.out.println( "3) Date de naissance de l'eleve." );
        System.out.println( "4) Ville de naissance de l'eleve." );
        System.out.println( "5) Pays de naissance de l'eleve." );
        System.out.println( "6) Sexe de l'eleve." );
        System.out.println( "7) Numero de rue de l'eleve." );
        System.out.println( "8) Rue de l'eleve." );
        System.out.println( "9) Code postal de l'eleve." );
        System.out.println( "10) Ville de l'eleve." );
        System.out.println( "11) Telephone de l'eleve." );
        System.out.println( "12) Email de l'eleve." );
        System.out.println( "13) Le groupe de l'eleve." );
        System.out.println( "14) Le responsable de l'eleve." );
        System.out.println( "15) Tout." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 0, 15 );
        System.out.println();

        switch (answer) {
            case 0:
                break;

            case 1:
                setNom();
                database.execute( Statement.update( "eleve", 1, "nom", nom, "matricule", id ) );
                break;

            case 2:
                setPrenom();
                database.execute( Statement.update( "eleve", 1, "prenom", prenom, "matricule", id ) );
                break;

            case 3:
                setDate_naissance();
                database.execute( Statement.update( "eleve", 1, "date_naissance", date_naissance, "matricule", id ) );
                break;

            case 4:
                setVille_naissance();
                database.execute( Statement.update( "eleve", 1, "ville_naissance", ville_naissance, "matricule", id ) );
                break;

            case 5:
                setPays_naissance();
                database.execute( Statement.update( "eleve", 1, "pays_naissance", pays_naissance, "matricule", id ) );
                break;

            case 6:
                setSexe();
                database.execute( Statement.update( "eleve", 1, "sexe", sexe, "matricule", id ) );
                break;


            case 7:
                setNumero_rue();
                database.execute( Statement.update( "eleve", 1, "numero_rue", numero_rue, "matricule", id ) );
                break;

            case 8:
                setRue();
                database.execute( Statement.update( "eleve", 1, "rue", rue, "matricule", id ) );
                break;

            case 9:
                setCode_postal();
                database.execute( Statement.update( "eleve", 1, "code_postal", code_postal, "matricule", id ) );
                break;

            case 10:
                setVille();
                database.execute( Statement.update( "eleve", 1, "ville", ville, "matricule", id ) );
                break;

            case 11:
                setTelephone();
                database.execute( Statement.update( "eleve", 1, "telephone", telephone, "matricule", id ) );
                break;

            case 12:
                setEmail();
                database.execute( Statement.update( "eleve", 1, "email", email, "matricule", id ) );
                break;

            case 13:
                setIdentifiant_groupe();
                database.execute( Statement.update( "eleve", 1, "identifiant_groupe", identifiant_groupe, "matricule"
                        , id ) );
                break;

            case 14:
                setNumero_responsable();
                database.execute( Statement.update( "eleve", 1, "numero_responsable", numero_responsable, "matricule"
                        , id ) );
                break;

            case 15:
                query();
                database.execute( Statement.update( "eleve", 15, "nom", nom, "prenom", prenom, "date_naissance",
                        date_naissance, "ville_naissance", ville_naissance, "pays_naissance", pays_naissance, "sexe",
                        sexe, "numero_rue", numero_rue, "rue", rue, "code_postal", code_postal, "ville", ville,
                        "telephone", telephone, "email", email, "identifiant_groupe", identifiant_groupe,
                        "numero_responsable", numero_responsable, "matricule", id ) );
                break;
        }
    }

    public static void updateGroup(String idStudent, String idNewGroup) {
        database.execute( Statement.update( "eleve", 1, "identifiant_groupe", idNewGroup, "matricule", idStudent ) );
    }

    public static void remove() {
        String id = Statement.askQuery( "eleve", "Choisissez un eleve a supprimer > " );
        String query = Statement.remove( "eleve", "matricule", id );
        database.execute( query );
    }

    public static void printGrades() {
        String id = Statement.askQuery( "eleve", "Choisissez un eleve > " );
        printGrades( id );
    }

    public static void printGrades(String id) {
        String query = Statement.select( "epreuve" ) + Statement.where( EQUAL, QUOTE, "matricule_eleve", id );
        Statement.printQuery( query, "note" );
    }

/*    public static void saveGrades(String idStudent) {
        String save = Input.askString("Choisissez le nom de votre fichier : ", 1, 15);
        save += ".pdf";

        try {

            String query =

            FileWriter fw = new FileWriter(save);

            fw.write("Bulletin de l'élève n°" + idStudent + " :\n");
            fw.write(  );
            fw.close();

        } catch (IOException ioex) {

            ioex.printStackTrace();

        }
    }*/

    public static void findByPromotions() {
        String id = Statement.askQuery( "promotion", "Choisissez une promotion dans laquelle rechercher > " );
        String query =
                Statement.join( "*", "eleve", "groupe", "identifiant_groupe=identifiant" ) + Statement.where( EQUAL,
                        QUOTE, "identifiant_promotion", id );
        Statement.printQuery( query, "eleves par promotion" );
    }

    public static void findByGroups() {
        String id = Statement.askQuery( "groupe", "Choisissez un groupe dans laquelle rechercher > " );
        String query = Statement.select( "eleve" ) + Statement.where( EQUAL, QUOTE, "identifiant_groupe", id );
        Statement.printQuery( query, "eleves par groupe" );
    }
}
