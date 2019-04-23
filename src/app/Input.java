package app;

import java.util.Scanner;

/**
 * Securing input for an {@code int}.
 */
public class Input {

    /**
     * Securing input for a {@code String}.
     * <p>Ask the user to input a {@code String} with a specific length between minSize and maxSize.
     * <p>Example:
     *
     * <blockquote><pre>
     *     //Ask for a String with a length between 1 and 10 characters
     *     String str = StringInput.askString("Choose a string > ", 1, 10);
     * </pre></blockquote>
     *
     * @param message Message to advertise the user of the awaited action
     * @param minSize Minimum {@code String} length
     * @param maxSize Maximum {@code String} length
     * @return The {@code String} typed by the user
     */
    public static String askString(String message, int minSize, int maxSize) {
        String str;
        Scanner sc = new Scanner( System.in );

        do {
            System.out.print( message );
            str = sc.nextLine();

            if (str.length() < minSize) {
                System.out.println( "The size of the String you entered is too small, awaiting for at least" + minSize + "characters..." );
            } else if (str.length() > maxSize) {
                System.out.println( "The value you entered is too big, awaiting for at most" + maxSize + "characters..." );
            }
        } while (str.length() < minSize || str.length() > maxSize);

        return str;
    }

    /**
     * Securing input for an {@code Int}.
     * <p>Ask the user to input any number.
     * <p>Example:
     *
     * <blockquote><pre>
     *     //Ask for a number while the user chooses a valid int
     *     int number = Input.askInt("Choose a number > ");
     * </pre></blockquote>
     *
     * @param message Message to advertise the user of the awaited action
     * @return The value typed by the user
     * @see Input#askInt(String, int, int)
     * @see Input#askIntMax(String, int)
     */
    public static int askInt(String message) {
        int value = 0;
        boolean ok = false;
        Scanner sc = new Scanner( System.in );

        do {
            try {
                System.out.print( message );
                value = sc.nextInt();
                ok = true;
            } catch (Exception ignored) {
                System.out.println( "Awaiting an integer, please try again..." );
                sc.nextLine();  // Escaping the '\n'
            }
        } while (!ok);

        return value;
    }

    /**
     * Securing input for an {@code Int}.
     * <p>Ask the user to input a number between min and max.
     * <p>Example:
     *
     * <blockquote><pre>
     *     //Ask for a number between 0 and 100
     *     int number = Input.askInt("Choose a number > ", 0, 100);
     * </pre></blockquote>
     *
     * @param message Message to advertise the user of the awaited action
     * @param min     Minimum allowed value (inclusive)
     * @param max     Maximum allowed value (inclusive)
     * @return The value typed by the user
     * @see Input#askInt(String)
     * @see Input#askIntMax(String, int)
     */
    public static int askInt(String message, int min, int max) {
        int value;

        do {
            value = askInt( message );

            if (value < min) {
                System.out.println( "The value you entered is too small, expecting at least " + min + "..." );
            } else if (value > max) {
                System.out.println( "The value you entered is too big, expecting at most " + max + "..." );
            }
        } while (value < min || value > max);

        return value;
    }

    /**
     * Securing input for a positive {@code Int}.
     * <p>Ask the user to input a positive number less than max.
     * <p>Example:
     *
     * <blockquote><pre>
     *     //Ask for a positive number less than 100
     *     int number = Input.askInt("Choose a number > ", 100);
     * </pre></blockquote>
     *
     * @param message Message to advertise the user of the awaited action
     * @param max     Maximum allowed value (inclusive)
     * @return The value typed by the user
     */
    public static int askIntMax(String message, int max) {
        return Input.askInt( message, 0, max );
    }
}