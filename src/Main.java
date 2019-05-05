import app.Database;
import app.Input;
import app.Menu;
import app.Statement;
import gestion.Cours;
import gestion.Dispense;

public class Main {

    public static void main(String[] args) {

        /*Menu menu = new Menu();*/

/*        Dispense dispense = new Dispense();

        Dispense.update();*/

        System.out.println( Statement.update( "dispense", 1,"matricule_professeur", "prof", "matricule_professeur", 222, "code_cours", 111 ) );

    }
}
