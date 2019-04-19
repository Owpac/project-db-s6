package gestion;

import app.Connexion;
import inputs.StringInput;

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

        this.nom = StringInput.askString( "- Un nom > ", 2, 15 );
        this.prenom = StringInput.askString( "- Un prenom > ", 2, 15 );
        this.numero_rue = StringInput.askString( "- Un numero_rue > ", 2, 15 );
        this.rue = StringInput.askString( "- Une rue > ", 2, 15 );
        this.code_postal = StringInput.askString( "- Un code_postal > ", 2, 15 );
        this.ville = StringInput.askString( "- Une ville > ", 2, 15 );
        this.telephone = StringInput.askString( "- Un telephone > ", 2, 15 );
        this.email = StringInput.askString( "- Un email > ", 2, 15 );
    }

    public void add() {

        this.query();

        String query =
                "INSERT INTO " +
                        TABLE +
                        " VALUES " +
                        Connexion.format(
                                this.nom,
                                this.prenom,
                                this.numero_rue,
                                this.rue,
                                this.code_postal,
                                this.ville,
                                this.telephone,
                                this.email
                        );

        Connexion con = new Connexion();

        con.execute( query );
    }
}
