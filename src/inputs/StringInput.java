package inputs;

import java.util.Scanner;

/**
 * Securing input for a {@code String}.
 */
public class StringInput {

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
        String str = "";
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
}
