/* Florian ERNST - Thomas FAUGIER */
package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static app.Constants.*;

//TODO : Use PreparedStatement against SQL Injections
public class Statement {

    private static Database database;

    public Statement() {
        database = new Database();
    }

    public Statement(Database database) {
        Statement.database = database;
    }

    /**
     * Build a where statement.
     * <p>
     * For instance :
     * // Call to the method
     * where("=", "''", "condition", "Test");
     * // Output of the method
     * where condition='Test'
     *
     * @param operation operator to use in the where clause (ex: "=", "!=", " not in ")
     * @param separator separator to surround your value (ex: "''", "()")
     * @param elements  your condition and the value of your ocndition
     * @return the query
     */
    public static String where(String operation, String separator, Object... elements) {
        StringBuilder query = new StringBuilder();

        query.append( " where " );
        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];

            if (i % 2 == 0) {
                query.append( element );
                query.append( operation );
            } else {
                query.append( separator.charAt( 0 ) );
                query.append( element );
                query.append( separator.charAt( 1 ) );

                if (i < elements.length - 1) {
                    query.append( " and " );
                }
            }
        }

        return query.toString();
    }

    /**
     * Build a select statement.
     *
     * @param selection selection of your table
     * @param table     table to select
     * @return the query
     */
    public static String select(String selection, String table) {
        return String.format( "select %s from %s", selection, table );
    }

    /**
     * Build a select statement but with default selection of "*".
     *
     * @param table table to select
     * @return
     * @see Statement#select(String, String)
     */
    public static String select(String table) {
        return select( "*", table );
    }

    /**
     * Build a join statement.
     *
     * @param selection selection of your table
     * @param table     table to select
     * @param elements
     * @return
     */
    public static String join(String selection, String table, Object... elements) {
        StringBuilder query = new StringBuilder( select( selection, table ) );

        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];

            if (i % 2 == 0) {
                query.append( " inner join " );
                query.append( element );
                query.append( " on " );
            } else {
                query.append( element );
            }
        }

        return query.toString();
    }

    /**
     * Format values for an insert statement.
     *
     * @param elements values to insert
     * @return the query
     */
    public static String values(Object... elements) {
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

    public static ResultSet executeQuery(String query) {
        try
        {
            java.sql.Statement statement = database.getConnection().createStatement();
            return statement.executeQuery(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Build an insert statement.
     *
     * @param definitionTable definition of the table
     * @param elements        values to insert in the table
     * @return the query
     */
    public static String add(String definitionTable, Object... elements) {
        /*String query = "insert into ? values ?";
        try {
            PreparedStatement statement = database.getConnection().prepareStatement( query );
            statement.setString( 1, definitionTable );
            statement.setString( 2, Statement.values( elements ) );
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/

        return String.format( "insert into %s values %s", definitionTable, Statement.values( elements ) );
    }

    /**
     * Build an update statement.
     *
     * @param table     table to update
     * @param nbrUpdate number of update that the statement contain
     * @param elements  elements to be updated
     * @return the query
     */
    public static String update(String table, int nbrUpdate, Object... elements) {
        StringBuilder query = new StringBuilder( String.format( "update %s set ", table ) );

        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];

            if (i % 2 == 0) {
                query.append( element );
                query.append( "=" );
            } else {
                query.append( "'" );
                query.append( element );
                query.append( "'" );

                if (i < (nbrUpdate * 2 - 1)) {
                    query.append( ", " );
                } else if (i == (nbrUpdate * 2 - 1)) {
                    query.append( " where " );
                } else if (i < elements.length - 1) {
                    query.append( " and " );
                }
            }
        }

        return query.toString();
    }

    /**
     * SQL Function that return the last ID inserted in the database.
     *
     * @return id of the last insert
     */
    public static String lastUpdate() {
        try (java.sql.Statement statement = database.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery( "select LAST_INSERT_ID()" );
            result.next();
            return result.getString( 1 );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Build a delete statement.
     *
     * @param table    table to use for the delete
     * @param elements elements to be deleted
     * @return the query
     */
    public static String remove(String table, Object... elements) {
        return String.format( "delete from %s ", table ) + where( EQUAL, QUOTE, elements );
    }

    /**
     * Print the result of a statement in database.
     *
     * @param query query to be printed
     * @param table table to print
     */
    public static void printQuery(String query, String table) {
        try {
            int nbrLines = 0;
            String title = table.substring( 0, 1 ).toUpperCase() + table.substring( 1 );

            PreparedStatement statement = database.getConnection().prepareStatement( query );
            ResultSet result = statement.executeQuery();

            System.out.println();
            if (table.charAt( table.length() - 1 ) == 's') {
                System.out.println( "Liste des " + title + " : " );
            } else {
                System.out.println( "Liste des " + title + "s : " );
            }

            int nbrColumns = result.getMetaData().getColumnCount();
            while (result.next()) {
                nbrLines++;
                ArrayList<String> valueColumns = new ArrayList<>();

                for (int i = 0; i < nbrColumns; i++) {
                    String currentValue = result.getString( i + 1 );
                    valueColumns.add( currentValue );
                }

                System.out.print( "> " + title + " n°" + nbrLines + " : " );

                for (String value : valueColumns) {
                    System.out.print( value + " | " );
                }
                System.out.println();
            }

            if (nbrLines == 0) {
                System.out.println( "Rien a afficher." );
            }
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print a table of the database.
     *
     * @param table table to be printed
     */
    public static void printQuery(String table) {
        printQuery( select( table ), table );
    }

    /**
     * Ask to choose an entry of the result of a statement.
     *
     * @param query   query
     * @param table   table of the query
     * @param message message to print for the user
     * @return id of the entry the user chose
     */
    public static String askQuery(String query, String table, String message) {
/*        int indexLine = 0;
        int nbrLines = 0;
        ArrayList<String> valueLines = new ArrayList<>();

        try {
            PreparedStatement statement = database.getConnection().prepareStatement( query );
            ResultSet result = statement.executeQuery();

            printQuery( query, table );

            while (result.next()) {
                nbrLines++;
                String currentColumn = result.getString( 1 );
                valueLines.add( currentColumn );
            }

            indexLine = Input.askInt( message, 0, nbrLines );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (indexLine != 0) {
            return valueLines.get( indexLine - 1 );
        } else {
            return null;
        }*/

        return askQueries( query, table, message ).get( 0 );
    }

    /**
     * Ask to choose an entry of the result of the select of a table.
     *
     * @param table   table
     * @param message message to print for the user
     * @return id of the entry the user chose
     */
    public static String askQuery(String table, String message) {
        return askQuery( select( table ), table, message );
    }

    /**
     * Ask to choose an entry of the result of a statement.
     *
     * @param query   query
     * @param table   table of the query
     * @param message message to print for the user
     * @return not only the id, but all the values of the entry chose by the user
     */
    public static ArrayList<String> askQueries(String query, String table, String message) {
        int indexLine = 0;
        int nbrLines = 0;
        ArrayList<ArrayList<String>> valueLines = new ArrayList<>();

        try {
            PreparedStatement statement = database.getConnection().prepareStatement( query );
            ResultSet result = statement.executeQuery();

            printQuery( query, table );

            int nbrColumns = result.getMetaData().getColumnCount();

            while (result.next()) {
                nbrLines++;
                ArrayList<String> valueColumns = new ArrayList<>();

                for (int i = 0; i < nbrColumns; i++) {
                    String currentValue = result.getString( i + 1 );
                    valueColumns.add( currentValue );
                }

                valueLines.add( valueColumns );
            }

            indexLine = Input.askInt( message, 1, nbrLines );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (indexLine != 0) {
            return valueLines.get( indexLine - 1 );
        } else {
            return null;
        }
    }

    /**
     * Ask to choose an entry of the result of the select of a table.
     *
     * @param table table of the query
     * @param message message to print for the user
     * @return not only the id, but all the values of the entry chose by the user
     */
    public static ArrayList<String> askQueries(String table, String message) {
        return askQueries( select( table ), table, message );
    }
}
