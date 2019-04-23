package gestion;

import app.Database;
import app.Utils;
import app.Input;

public class Professeur {

    private final String DEF_TABLE = "professeur(nom,prenom,numero_rue,rue,code_postal,ville,telephone,email)";

    private Database database;

    private String nom;
    private String prenom;
    private String numero_rue;
    private String rue;
    private String code_postal;
    private String ville;
    private String telephone;
    private String email;

    public Professeur() {
        this.database = new Database();
    }


    public void query() {
        this.nom = Input.askString( "Saisissez le nom du professeur > ", 2, 15 );
        this.prenom = Input.askString( "Saisissez le prenom du professeur > ", 2, 15 );
        this.numero_rue = Input.askString( "Saisissez le numero de rue du professeur > ", 2, 15 );
        this.rue = Input.askString( "Saisissez la rue du professeur > ", 2, 15 );
        this.code_postal = Input.askString( "Saisissez le code postal du professeur > ", 2, 15 );
        this.ville = Input.askString( "Saisissez la ville du professeur > ", 2, 15 );
        this.telephone = Input.askString( "Saisissez le telephone du professeur > ", 2, 15 );
        this.email = Input.askString( "Saisissez l'email du professeur > ", 2, 15 );
    }

    public void add() {

        this.query();

        String query = Utils.add( DEF_TABLE, this.nom, this.prenom, this.numero_rue, this.rue, this.code_postal, this.ville, this.telephone, this.email );

        this.database.execute( query );
    }
}
