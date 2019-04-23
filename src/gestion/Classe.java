package gestion;

import app.Database;
import app.Utils;
import app.Input;

public class Classe {
    private final String DEF_TABLE = "classe(identifiant,identifiant_promotion)";

    private Database database;

    private String identifiant;
    private String identifiant_promotion;

    public Classe() {

        this.database = new Database();
    }

    public void query() {

        String query = Utils.select( "*", "promotion" );

        this.identifiant = Input.askString( "Saisissez le nom de votre groupe > ", 2, 15 );
        this.identifiant_promotion = Utils.askQuery( "Choisissez la promotion de votre groupe > ", "Promotion", this.database, query );

    }

    public void add() {

        this.query();

        Utils.add( DEF_TABLE, this.identifiant, this.identifiant_promotion );
    }

    public void update() {
        String query = Utils.select( "*", "classe" );

        String group = Utils.askQuery( "Choisissez une classe a modifier > ", "Classe", this.database, query );

        int answer = Input.askInt( "Que voulez-vous modifier > \n1) L'identifiant.\n2) La promotion de la classe.\n3) Les deux.", 1, 3 );

        switch (answer) {

            case 1:
                this.identifiant = Input.askString( "Saisissez le nom de votre classe > ", 2, 15 );
                this.database.execute( Utils.update( "classe", "identifiant", this.identifiant, "identifiant", group ) );
                break;

            case 2:
                this.identifiant_promotion = Utils.askQuery( "Choisissez la promotion de votre classe > ", "Promotion", this.database, query );
                this.database.execute( Utils.update( "classe", "identifiant_promotion", this.identifiant_promotion, "identifiant", group ) );
                break;

            default:
                this.query();
                this.database.execute( Utils.update( "classe", "identifiant", this.identifiant, "identifiant", group ) );
                this.database.execute( Utils.update( "classe", "identifiant_promotion", this.identifiant_promotion, "identifiant", group ) );
                break;
        }
    }

    public void updateClassOfStudent() {

        String query = Utils.select( "*", "classe" );

        String classe = Utils.askQuery( "Choisissez une classe > ", "Classe", this.database, query );

        query = Utils.select( "*", "eleve", "identifiant_classe", "!=", classe );

        String eleve = Utils.askQuery( "Choisissez un eleve a ajouter a la classe \'" + classe + "\' > ", "Eleve", this.database, query );

        query = Utils.update( "eleve", "identifiant_classe", classe, "matricule", "=", eleve );

        this.database.execute( query );
    }
}
