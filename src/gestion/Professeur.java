package gestion;

import app.Database;
import app.Utils;
import app.Input;

public class Professeur {

    private final String TABLE =
            "professeur(" +
                    "nom," +
                    "prenom," +
                    "numero_rue," +
                    "rue," +
                    "code_postal," +
                    "ville," +
                    "telephone," +
                    "email)";

    private String nom;
    private String prenom;
    private String numero_rue;
    private String rue;
    private String code_postal;
    private String ville;
    private String telephone;
    private String email;


    public void query() {

        System.out.println( "Veuillez saisir :" );

        this.nom = Input.askString( "- Un nom > ", 2, 15 );
        this.prenom = Input.askString( "- Un prenom > ", 2, 15 );
        this.numero_rue = Input.askString( "- Un numero_rue > ", 2, 15 );
        this.rue = Input.askString( "- Une rue > ", 2, 15 );
        this.code_postal = Input.askString( "- Un code_postal > ", 2, 15 );
        this.ville = Input.askString( "- Une ville > ", 2, 15 );
        this.telephone = Input.askString( "- Un telephone > ", 2, 15 );
        this.email = Input.askString( "- Un email > ", 2, 15 );
    }

    public void add() {

        this.query();

        String query = "INSERT INTO " +
                        TABLE +
                        " VALUES " +
                        Utils.format(
                                this.nom,
                                this.prenom,
                                this.numero_rue,
                                this.rue,
                                this.code_postal,
                                this.ville,
                                this.telephone,
                                this.email
                        );

        Database database = new Database();

        database.execute( query );
    }
}
