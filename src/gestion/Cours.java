package gestion;

import app.Database;
import app.Input;
import app.Utils;

public class Cours {

    private final String DEF_TABLE = "cours(nom,description,annee,coefficient,pourcentage_de,pourcentage_tp,pourcentage_projet,identifiant_classe)";

    private Database database;

    private String nom;
    private String description;
    private String annee;
    private String coefficient;
    private String pourcentage_de;
    private String pourcentage_tp;
    private String pourcentage_projet;
    private String identifiant_classe;


    public Cours() {
        this.database = new Database();
    }

    public void query() {
        String query = "select * from classe";

        this.nom = Input.askString("Saisissez le nom du cours > ", 2, 15);
        this.description = Input.askString("Saisissez la description du cours > ", 2, 44);
        this.annee = Input.askString("Saisissez l'année du cours > ", 2, 15);
        this.coefficient = Input.askString("Saisissez le coefficient du cours > ", 2, 15);
        this.pourcentage_de = Input.askString("Saisissez le pourcentage du DE du cours > ", 2, 15);
        this.pourcentage_tp = Input.askString("Saisissez le pourcentage du TP du cours > ", 2, 15);
        this.pourcentage_projet = Input.askString("Saisissez le pourcentage du porjet du cours > ", 2, 15);
        this.identifiant_classe = Utils.askQuery( "Saisissez la classe du cours > ", "Classe", this.database, query);
    }

    public void update() {

    }
}
