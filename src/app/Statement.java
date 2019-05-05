package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static app.Constants.NBR_COLUMNS_DEFAULT;

public class Statement {

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
        return String.format( "select %s from %s", selection, table );
    }

    public static String select(String table) {
        return select( "*", table );
    }

    public static String select(String selection, String table, String conditionColumn, String operation, String conditionValue) {
        return String.format( "%s where %s%s'%s'", select( selection, table ), conditionColumn, operation, conditionValue );
    }

    public static String select(String table, String conditionColumn, String operation, String conditionValue) {
        return String.format( "%s where %s%s'%s'", select( table ), conditionColumn, operation, conditionValue );
    }

    public static String add(String definitionTable, Object... elements) {
        return String.format( "insert into %s values %s", definitionTable, Statement.format( elements ) );
    }

    public static String update(String table, String updateColumn, String updateValue, Object... elements) {
        StringBuilder query = new StringBuilder( String.format( "update %s set %s='%s' where ", table, updateColumn, updateValue ) );

        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];

            if (i % 2 == 0) {
                query.append( element );
                query.append( "=" );
                query.append( "'" );
            } else {
                query.append( element );
                query.append( "'" );

                if (i != elements.length - 1) {
                    query.append( " and " );
                }
            }
        }
        return query.toString();
    }

    public static String update(String table, int nbrUpdate, Object... elements) {
        StringBuilder query = new StringBuilder( String.format( "update %s set ", table ) );

        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];

            if (i % 2 == 0) {
                query.append( element );
                query.append( "=" );
                query.append( "'" );
            } else {
                query.append( element );
                query.append( "'" );

                if (i < nbrUpdate * 2) {
                    query.append( ", " );
                } else if (i != elements.length - 1) {
                    query.append( " and " );
                }
            }
        }

        return query.toString();
    }

    public static String update(String table, String updateColumn, String updateValue, String conditionColumn, String operation, String conditionValue) {
        return String.format( "update %s set %s='%s' where %s%s'%s'", table, updateColumn, updateValue, conditionColumn, operation, conditionValue );
    }

    public static String update(String table, String updateColumn, String updateValue, String conditionColumn, String conditionValue) {
        return update( table, updateColumn, updateValue, conditionColumn, "=", conditionValue );
    }

    public static String remove(String table, String conditionColumn, String operation, String conditionValue) {
        return String.format( "delete from %s where %s%s'%s'", table, conditionColumn, operation, conditionValue );
    }

    public static String remove(String table, String conditionColumn, String conditionValue) {
        return remove( table, conditionColumn, "=", conditionValue );
    }

    public static void printQuery(Database database, String query, String table, int nbrColumns) {
        try (java.sql.Statement statement = database.getConnexion().createStatement()) {

            int nbrLines = 0;
            String title = table.substring( 0, 1 ).toUpperCase() + table.substring( 1 );

            ResultSet result = statement.executeQuery( query );

            System.out.println();
            System.out.println( "Liste des " + title + "s" + " : " );

            while (result.next()) {
                nbrLines++;
                ArrayList<String> columns = new ArrayList<>();

                for (int i = 0; i < nbrColumns; i++) {
                    String currentColumn = result.getString( i + 1 );
                    columns.add( currentColumn );
                }

                System.out.print( "> " + title + " n°" + nbrLines + " : " );

                for (String column : columns) {
                    if (nbrColumns == NBR_COLUMNS_DEFAULT) {
                        System.out.print( column );
                    } else {
                        System.out.print( column + " | " );
                    }
                }
                System.out.println();
            }
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printQuery(Database database, String table, int nbrColumns) {
        printQuery( database, select( table ), table, nbrColumns );
    }

    public static String askQuery(Database database, String query, String table, String message, int nbrColumns) {

        int indexLine = 0;
        int nbrLines = 0;
        ArrayList<String> valueLines = new ArrayList<>();

        try (java.sql.Statement statement = database.getConnexion().createStatement()) {

            ResultSet result = statement.executeQuery( query );

            printQuery( database, query, table, nbrColumns );

            while (result.next()) {
                nbrLines++;
                String currentColumn = result.getString( 1 );
                valueLines.add( currentColumn );
            }

            indexLine = Input.askInt( message, 1, nbrLines );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valueLines.get( indexLine - 1 );
    }

    public static String askQuery(Database database, String table, String message, int nbrColumns) {

        return askQuery( database, select( table ), table, message, nbrColumns );
    }

    public static String askQuery(Database database, String table, String message) {

        return askQuery( database, select( table ), table, message, NBR_COLUMNS_DEFAULT );
    }

    public static ArrayList<String> askQueries(Database database, String query, String table, String message, int nbrColumns) {

        int indexLine = 0;
        int nbrLines = 0;
        ArrayList<ArrayList<String>> valueLines = new ArrayList<>();

        try (java.sql.Statement statement = database.getConnexion().createStatement()) {

            ResultSet result = statement.executeQuery( query );

            printQuery( database, query, table, nbrColumns );

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

        return valueLines.get( indexLine - 1 );
    }

    public static ArrayList<String> askQueries(Database database, String table, String message, int nbrColumns) {

        return askQueries( database, select( table ), table, message, nbrColumns );
    }

    public static ArrayList<String> askQueries(Database database, String table, String message) {

        return askQueries( database, select( table ), table, message, NBR_COLUMNS_DEFAULT );
    }
}
