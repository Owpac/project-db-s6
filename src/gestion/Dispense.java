package gestion;

import app.Database;
import app.Statement;

public class Dispense {
    private final String DEF_TABLE = "dispense(matricule_professeur,code_cours)";

    private Database database;

    private String matricule_professeur;
    private String code_cours;

    public Dispense() {
        this.database = new Database();

        this.add();
    }

    public void query() {
        String query = Statement.select( "professeur" );
        this.matricule_professeur = Statement.askQuery( this.database, "Professeur", "Saisissez le professeur responsable du cours > " );

        query = Statement.select( "cours" );
        this.code_cours = Statement.askQuery( this.database, "Cours", "Saisissez le cours > " );
    }

    public void add() {

        this.query();

        String query = Statement.add( DEF_TABLE, this.matricule_professeur, this.code_cours );

        this.database.execute( query );
    }
}
