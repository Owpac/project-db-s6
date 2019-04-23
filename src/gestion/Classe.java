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

    public String getIdentifiant() {
        return identifiant;
    }

    public String getIdentifiant_promotion() {
        return identifiant_promotion;
    }

    public void setIdentifiant() {
        this.identifiant = Input.askString( "Saisissez le nom de votre groupe > ", 2, 15 );
    }

    public void setIdentifiant_promotion() {
        String query = Utils.select( "*", "promotion" );

        this.identifiant_promotion = Utils.askQuery( "Choisissez la promotion de votre groupe > ", "Promotion", this.database, query );
    }

    public void query() {
        this.setIdentifiant();
        this.setIdentifiant_promotion();
    }

    public void add() {
        this.query();

        String query = Utils.add( DEF_TABLE, this.identifiant, this.identifiant_promotion );

        this.database.execute( query );
    }

    public void updateClassOfStudent() {
        String query = Utils.select( "*", "classe" );
        String group = Utils.askQuery( "Choisissez une classe > ", "Classe", this.database, query );

        query = Utils.select( "*", "eleve", "identifiant_classe", "!=", group );
        String student = Utils.askQuery( "Choisissez un eleve a ajouter a la classe \"" + group + "\" > ", "Eleve", this.database, query );

        query = Utils.update( "eleve", "identifiant_classe", group, "matricule", "=", student );
        this.database.execute( query );
    }
}
