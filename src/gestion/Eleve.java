package gestion;

import app.Database;
import app.Input;
import app.JTableBasic;
import app.Statement;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static app.Constants.*;

public class Eleve
{
    private static Database database;

    private static String nom;
    private static String prenom;
    private static String date_naissance;
    private static String ville_naissance;
    private static String pays_naissance;
    private static String sexe;
    private static String numero_rue;
    private static String rue;
    private static String code_postal;
    private static String ville;
    private static String telephone;
    private static String email;
    private static String identifiant_groupe;
    private static String numero_responsable;
    private static Integer matricule;
    private static Integer annee;

    public Eleve()
    {
        database = new Database();
    }

    public Eleve(Database database)
    {
        Eleve.database = database;
    }

    private static void setNom()
    {
        nom = Input.askString("Saisissez le nom de l'eleve > ", 2, 50);
    }

    private static void setPrenom()
    {
        prenom = Input.askString("Saisissez le prenom de l'eleve > ", 2, 50);
    }

    public static void setDate_naissance()
    {
        int year = Input.askInt("Saisissez l'annee de naissance de l'eleve > ", 1900, 2020);
        int month = Input.askInt("Saisissez le mois de naissance de l'eleve > ", 1, 12);
        int day = Input.askInt("Saisissez le jour de naissance de l'eleve > ", 1, 31);

        date_naissance = String.format("%s-%s-%s", year, month, day);
    }

    public static void setVille_naissance()
    {
        ville_naissance = Input.askString("Saisissez la ville de naissance de l'eleve > ", 2, 50);
    }

    public static void setPays_naissance()
    {
        pays_naissance = Input.askString("Saisissez le pays de naissance de l'eleve > ", 2, 50);
    }

    public static void setSexe()
    {
        sexe = Input.askString("Saisissez le sexe de l'eleve > ", 2, 50);
    }

    private static void setNumero_rue()
    {
        numero_rue = Input.askString("Saisissez le numero de rue de l'eleve > ", 1, 50);
    }

    private static void setRue()
    {
        rue = Input.askString("Saisissez la rue de l'eleve > ", 2, 50);
    }

    private static void setCode_postal()
    {
        code_postal = Input.askString("Saisissez le code postal de l'eleve > ", 2, 50);
    }

    private static void setVille()
    {
        ville = Input.askString("Saisissez la ville de l'eleve > ", 2, 50);
    }

    private static void setTelephone()
    {
        telephone = Input.askString("Saisissez le telephone de l'eleve > ", 2, 50);
    }

    private static void setEmail()
    {
        email = Input.askString("Saisissez l'email de l'eleve > ", 2, 50);
    }

    public static void setIdentifiant_groupe()
    {
        identifiant_groupe = Statement.askQuery("groupe", "Saisissez le groupe de l'eleve > ");
    }

    public static void setNumero_responsable()
    {
        numero_responsable = Statement.askQuery("responsable", "Saisissez le responsable de l'eleve > ");
    }

    public static void setAnnee()
    {
        annee = Input.askInt("Saisissez l'année de l'élève", 4, 4);
        setMatricule();
    }

    private static void setMatricule()
    {
        String query = Statement.select("*", "eleve")
            + Statement.where(EQUAL, "", "annee", annee)
            + " ORDER BY matricule DESC LIMIT 1";
        matricule = annee * 1000 + 1; // matricule par defaut
        ResultSet rset = Statement.executeQuery(query);

        try {
            if (rset.next()) {
                matricule = rset.getInt("matricule") + 1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void query()
    {
        setNom();
        setPrenom();
        setDate_naissance();
        setVille_naissance();
        setPays_naissance();
        setSexe();
        setNumero_rue();
        setRue();
        setCode_postal();
        setVille();
        setTelephone();
        setEmail();
        setIdentifiant_groupe();
        setNumero_responsable();
    }

    public static void add()
    {
        query();
        String query = Statement.add(DEF_TABLE_ELEVE, matricule, nom, prenom, date_naissance, ville_naissance, pays_naissance,
                                     sexe, numero_rue, rue, code_postal, ville, telephone, email, identifiant_groupe, numero_responsable, annee);
        database.execute(query);
    }

    public static void update()
    {
        String id = Statement.askQuery("eleve", "Choisissez l'eleve a modifier > ");
        System.out.println();
        System.out.println("0) Annuler.");
        System.out.println("1) Nom de l'eleve.");
        System.out.println("2) Prenom de l'eleve.");
        System.out.println("3) Date de naissance de l'eleve.");
        System.out.println("4) Ville de naissance de l'eleve.");
        System.out.println("5) Pays de naissance de l'eleve.");
        System.out.println("6) Sexe de l'eleve.");
        System.out.println("7) Numero de rue de l'eleve.");
        System.out.println("8) Rue de l'eleve.");
        System.out.println("9) Code postal de l'eleve.");
        System.out.println("10) Ville de l'eleve.");
        System.out.println("11) Telephone de l'eleve.");
        System.out.println("12) Email de l'eleve.");
        System.out.println("13) Le groupe de l'eleve.");
        System.out.println("14) Le responsable de l'eleve.");
        System.out.println("50) Tout.");
        int answer = Input.askInt("Que voulez-vous modifier > ", 0, 50);
        System.out.println();

        switch (answer) {
            case 0:
                break;

            case 1:
                setNom();
                database.execute(Statement.update("eleve", 1, "nom", nom, "matricule", id));
                break;

            case 2:
                setPrenom();
                database.execute(Statement.update("eleve", 1, "prenom", prenom, "matricule", id));
                break;

            case 3:
                setDate_naissance();
                database.execute(Statement.update("eleve", 1, "date_naissance", date_naissance, "matricule", id));
                break;

            case 4:
                setVille_naissance();
                database.execute(Statement.update("eleve", 1, "ville_naissance", ville_naissance, "matricule", id));
                break;

            case 5:
                setPays_naissance();
                database.execute(Statement.update("eleve", 1, "pays_naissance", pays_naissance, "matricule", id));
                break;

            case 6:
                setSexe();
                database.execute(Statement.update("eleve", 1, "sexe", sexe, "matricule", id));
                break;


            case 7:
                setNumero_rue();
                database.execute(Statement.update("eleve", 1, "numero_rue", numero_rue, "matricule", id));
                break;

            case 8:
                setRue();
                database.execute(Statement.update("eleve", 1, "rue", rue, "matricule", id));
                break;

            case 9:
                setCode_postal();
                database.execute(Statement.update("eleve", 1, "code_postal", code_postal, "matricule", id));
                break;

            case 10:
                setVille();
                database.execute(Statement.update("eleve", 1, "ville", ville, "matricule", id));
                break;

            case 11:
                setTelephone();
                database.execute(Statement.update("eleve", 1, "telephone", telephone, "matricule", id));
                break;

            case 12:
                setEmail();
                database.execute(Statement.update("eleve", 1, "email", email, "matricule", id));
                break;

            case 13:
                setIdentifiant_groupe();
                database.execute(Statement.update("eleve", 1, "identifiant_groupe", identifiant_groupe, "matricule"
                    , id));
                break;

            case 14:
                setNumero_responsable();
                database.execute(Statement.update("eleve", 1, "numero_responsable", numero_responsable, "matricule"
                    , id));
                break;

            case 50:
                query();
                database.execute(Statement.update("eleve", 50, "nom", nom, "prenom", prenom, "date_naissance",
                                                  date_naissance, "ville_naissance", ville_naissance, "pays_naissance", pays_naissance, "sexe",
                                                  sexe, "numero_rue", numero_rue, "rue", rue, "code_postal", code_postal, "ville", ville,
                                                  "telephone", telephone, "email", email, "identifiant_groupe", identifiant_groupe,
                                                  "numero_responsable", numero_responsable, "matricule", id));
                break;
        }
    }

    public static void updateGroup(String idStudent, String idNewGroup)
    {
        database.execute(Statement.update("eleve", 1, "identifiant_groupe", idNewGroup, "matricule", idStudent));
    }

    public static void remove()
    {
        String id = Statement.askQuery("eleve", "Choisissez un eleve a supprimer > ");
        String query = Statement.remove("eleve", "matricule", id);
        database.execute(query);
    }

    public static void printGrades()
    {
        String id = Statement.askQuery("eleve", "Choisissez un eleve > ");
        printGrades(id);
    }

    public static void printGrades(String studentId)
    {
        String query = Statement.join("e.*, c.*", "epreuve e", "possede p", "e.numero = p.numero_epreuve", "cours c", "p.code_cours = c.code") + Statement.where(EQUAL, QUOTE, "matricule_eleve", studentId);
        ResultSet rset = Statement.executeQuery(query);
        Map<String, String> champCoeff = Map.of("DE", "pourcentage_de", "TP", "pourcentage_tp", "Projet", "pourcentage_projet");
        while (true) {
            try {
                if (!rset.next()) {
                    break;
                }
                String type = rset.getString("type");
                double coeffExam = rset.getDouble(champCoeff.get(type));
                double coeffCours = rset.getDouble("coefficient");
                System.out.println(rset.getString("nom") + " " + coeffCours + " | " + type + ": " + rset.getString("note") + " (coefficient " + coeffExam + ")");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        generateReport(studentId);
    }

    public static void generateReport(String studentId)
    {
        String query = Statement.join("e.*, c.*", "epreuve e", "possede p", "e.numero = p.numero_epreuve", "cours c", "p.code_cours = c.code") + Statement.where(EQUAL, QUOTE, "matricule_eleve", studentId);
        ResultSet rset = Statement.executeQuery(query);
        List<String> indexs = Arrays.asList("Cours", "DE", "TP", "Projet");
        Map<Integer, Double[]> coefficients = new HashMap<>();
        Map<Integer, String> nomsCours = new HashMap<>();
        Map<Integer, Double> totauxNotes = new HashMap<>();
        Map<Integer, Double> sommesCoeffs = new HashMap<>();

        // On récupère toutes les notes, et on les stocke (ainsi que leurs coefficients)
        while (true) {
            try {
                if (!rset.next()) { break; }
                Integer idCours = rset.getInt("code");
                String nomCours = rset.getString("nom");
                Double note = rset.getDouble("note");
                String type = rset.getString("type");

                if (!nomsCours.containsKey(idCours)) {
                    nomsCours.put(idCours, nomCours);
                    totauxNotes.put(idCours, 0.0);
                    sommesCoeffs.put(idCours, 0.0);
                    coefficients.put(idCours, new Double[]{
                        rset.getDouble("coefficient"),
                        rset.getDouble("pourcentage_de"),
                        rset.getDouble("pourcentage_tp"),
                        rset.getDouble("pourcentage_projet")
                    });
                }

                Double[] coeffs = coefficients.get(idCours);
                Double coeffEpreuve = coeffs[indexs.indexOf(type)];

                totauxNotes.put(idCours, totauxNotes.get(idCours) + note * coeffEpreuve);
                sommesCoeffs.put(idCours, sommesCoeffs.get(idCours) + coeffEpreuve);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String[] nomsColonnes = {"Matière", "Moyenne"};
        int nombreLignes = nomsCours.size() + 1;
        Object[][] data = new Object[nombreLignes][2];

        // On calcule les moyennes par matières, et la moyenne générale
        double totalMoyennes = 0;
        double totalCoeffsCours = 0;

        int ligne = 0;
        for (Integer idCours : nomsCours.keySet()) {
            String nomCours = nomsCours.get(idCours);
            Double totalNotesMatiere = totauxNotes.get(idCours);
            Double sommeCoeffsMatiere = sommesCoeffs.get(idCours);

            double moyenne = totalNotesMatiere / sommeCoeffsMatiere;
            data[ligne] = new Object[]{nomCours, moyenne};
            ligne++;

            double coeffCours = coefficients.get(idCours)[indexs.indexOf("Cours")];
            totalMoyennes += moyenne * coeffCours;
            totalCoeffsCours += coeffCours;
        }
        data[data.length - 1] = new Object[]{"Moyenne Générale :", totalMoyennes / totalCoeffsCours};
        var table = new JTableBasic("Bulletin de notes de l'étudiant " + studentId, data, nomsColonnes);
        table.pack();
        table.setVisible(true);
    }

/*    public static void saveGrades(String idStudent) {
        String save = Input.askString("Choisissez le nom de votre fichier : ", 1, 50);
        save += ".pdf";

        try {

            String query =

            FileWriter fw = new FileWriter(save);

            fw.write("Bulletin de l'élève n°" + idStudent + " :\n");
            fw.write(  );
            fw.close();

        } catch (IOException ioex) {

            ioex.printStackTrace();

        }
    }*/

    public static void findByPromotions()
    {
        String id = Statement.askQuery("promotion", "Choisissez une promotion dans laquelle rechercher > ");
        String query =
            Statement.join("*", "eleve", "groupe", "identifiant_groupe=identifiant") + Statement.where(EQUAL,
                                                                                                       QUOTE, "identifiant_promotion", id);
        Statement.printQuery(query, "eleves par promotion");
    }

    public static void findByGroups()
    {
        String id = Statement.askQuery("groupe", "Choisissez un groupe dans laquelle rechercher > ");
        String query = Statement.select("eleve") + Statement.where(EQUAL, QUOTE, "identifiant_groupe", id);
        Statement.printQuery(query, "eleves par groupe");
    }
}
