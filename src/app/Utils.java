package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Utils {

    public static String format(Object... elements) {
        StringBuilder str = new StringBuilder();

        str.append( "(" );
        for (Object element : elements) {
            str.append( "\"" );
            str.append( element );
            str.append( "\"" );
            str.append( "," );
        }

        // Remove the last "," before the parenthesis
        str.deleteCharAt( str.lastIndexOf( "," ) );

        str.append( ")" );

        return str.toString();
    }

    public static String select(String selection, String table) {

        /*String query = "select " + selection + " from " + table;*/

        return String.format( "select %s from %s", selection, table );

    }

    public static String select(String selection, String table, String conditionColumn, String operation, String conditionValue) {

        /*String query = select( selection, table ) + " where " + column + operation + "\'" + condition + "\'";*/

        return String.format( "%s where %s%s'%s'", select( selection, table ), conditionColumn, operation, conditionValue );
    }

    public static String add(String definitionTable, Object... elements) {

        /*String query = "insert into " + definitionTable + " values " + Utils.format( elements );*/

        return String.format( "insert into %s values %s", definitionTable, Utils.format( elements ) );

    }

    public static String update(String table, String updateColumn, String newValue, String conditionColumn, String operation, String conditionValue) {

        /*String query = "update " + table + " set " + updateColumn + "=" + "\'" + newValue + "\'" + " where " + conditionColumn + operation + "\'" + conditionValue + "\'";*/

        return String.format( "update %s set %s='%s' where %s%s'%s'", table, updateColumn, newValue, conditionColumn, operation, conditionValue );

    }

    public static String update(String table, String updateColumn, String newValue, String conditionColumn, String conditionValue) {

        return String.format( "update %s set %s='%s' where %s%s'%s'", table, updateColumn, newValue, conditionColumn, "=", conditionValue );

    }


    public static String askQuery(String message, String title, Database database, String query) {

        int indexLine = 0;
        int nbrLines = 0;
        ArrayList<String> valueLines = new ArrayList<>();

        try (Statement statement = database.getConnexion().createStatement()) {

            ResultSet result = statement.executeQuery( query );

            System.out.println( "\nListe des " + title + "s" + " : " );

            while (result.next()) {

                String currentLine = result.getString( 1 );

                valueLines.add( currentLine );
                nbrLines++;

                System.out.println( "> " + title + " n°" + nbrLines + " : " + currentLine );
            }

            System.out.println();

            indexLine = Input.askInt( message, 1, nbrLines );

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return valueLines.get( indexLine - 1 );
    }
}
