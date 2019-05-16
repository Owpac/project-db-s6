/* Florian ERNST - Thomas FAUGIER */
package app;

/**
 * All the constants for the app.
 */
public final class Constants {

    /**
     * Definition of tables in DB.
     */
    public static final String
            DEF_TABLE_GROUPE = "groupe(identifiant,identifiant_promotion)",
            DEF_TABLE_COURS = "cours(nom,description,annee,coefficient,pourcentage_de,pourcentage_tp,pourcentage_projet,identifiant_groupe)",
            DEF_TABLE_PROFESSEUR = "professeur(nom,prenom,numero_rue,rue,code_postal,ville,telephone,email)",
            DEF_TABLE_ELEVE = "eleve(matricule,nom,prenom,date_naissance,ville_naissance,pays_naissance,sexe,numero_rue,rue,code_postal,ville,telephone,email,identifiant_groupe,numero_responsable,annee)",
            DEF_TABLE_DISPENSE = "dispense(matricule_professeur,code_cours)",
            DEF_TABLE_EPREUVE = "epreuve(type,note,matricule_eleve)",
            DEF_TABLE_POSSEDE = "possede(numero_epreuve,code_cours)";

    /**
     * Operator accepted in a where clause.
     */
    public static final String
            EQUAL = "=",
            DIFFERENT = "!=",
            NOT_IN = " not in ";
    /**
     * Separator accepted in a where clause.
     */
    public static final String
            QUOTE = "''",
            PARENTHESIS = "()";
}
