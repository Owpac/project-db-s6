import app.Database;
import app.Menu;
import app.Statement;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();


        /*String query = Statement.join( "*", "cours", "dispense", "code=code_cours" ) + Statement.where( "matricule_profeseur", "12351" );
        System.out.println( query );*/

/*        new Statement( new Database() );
        String query = Statement.join( "professeur.nom, cours.nom", "professeur", "dispense", "matricule=matricule_professeur", "cours", "code_cours=code" );
        System.out.println( query );
        Statement.printQuery( query, "prof" );*/
    }
}
