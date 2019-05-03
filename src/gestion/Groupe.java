package gestion;

import app.Constants;
import app.Database;
import app.Statement;
import app.Input;

import static app.Constants.NBR_COLUMNS_ELEVE_MIN;


public class Groupe {
    private final String DEF_TABLE = "groupe(identifiant,identifiant_promotion)";

    private Database database;

    private String identifiant;
    private String identifiant_promotion;

    public Groupe() {
        this.database = new Database();
    }

    public void setIdentifiant() {
        this.identifiant = Input.askString( "Saisissez le nom de votre groupe > ", 2, 15 );
    }

    public void setIdentifiant_promotion() {
        this.identifiant_promotion = Statement.askQuery( this.database, "promotion", "Choisissez la promotion de votre groupe > " );
    }

    public void query() {
        this.setIdentifiant();
        this.setIdentifiant_promotion();
    }

    public void add() {
        System.out.println();

        this.query();

        String query = Statement.add( DEF_TABLE, this.identifiant, this.identifiant_promotion );

        this.database.execute( query );
    }

    public void update(String id) {
        System.out.println();
        System.out.println( "1) Identifiant du groupe." );
        System.out.println( "2) Promotion du groupe." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 1, 2 );
        System.out.println();

        switch (answer) {
            case 1:
                this.setIdentifiant();
                this.database.execute( Statement.update( "groupe", "identifiant", this.identifiant, "identifiant", id ) );
                break;
            case 2:
                this.setIdentifiant_promotion();
                this.database.execute( Statement.update( "groupe", "identifiant_promotion", this.identifiant_promotion, "identifiant", id ) );
                break;
        }
    }

    public void remove(String id) {
        String query = Statement.remove( "groupe", "identifiant", id );

        this.database.execute( query );
    }

    public void updateGroupOfStudent() {
        String group = Statement.askQuery( this.database, "groupe", "Choisissez un groupe > " );

        String query = Statement.select( "eleve", "identifiant_groupe", "!=", group );
        String student = Statement.askQuery( this.database, query, "eleve", "Choisissez un eleve a ajouter au groupe \"" + group + "\" > ", NBR_COLUMNS_ELEVE_MIN );

        this.database.execute( Statement.update( "eleve", "identifiant_groupe", group, "matricule", student ) );
    }
}
