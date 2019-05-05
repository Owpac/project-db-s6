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
            DEF_TABLE_DISPENSE = "dispense(matricule_professeur,code_cours)";
    /**
     * Number of columns for tables in DB.
     */
    public static final int
            NBR_COLUMNS_DEFAULT = 1,
            NBR_COLUMNS_MIN = 3,
            NBR_COLUMNS_GROUPE = 2,
            NBR_COLUMNS_ELEVE = 16,
            NBR_COLUMNS_COURS = 9,
            NBR_COLUMNS_DISPENSE = 2;
}
