package inputs;

import java.util.Scanner;

/**
 * Securing input for an {@code int}.
 */
public class IntInput {

    /**
     * Securing input for a number.
     * <p>Ask the user to input any number.
     * <p>Example:
     *
     * <blockquote><pre>
     *     //Ask for a number while the user chooses a valid int
     *     int number = IntInput.askInt("Choose a number > ");
     * </pre></blockquote>
     *
     * @param message Message to advertise the user of the awaited action
     * @return The value typed by the user
     * @see IntInput#askInt(String, int, int)
     * @see IntInput#askInt(String, int, int, boolean)
     * @see IntInput#askIntMax(String, int)
     */
    public static int askInt(String message) {
        int value = 0;
        boolean ok = false;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.print(message);
                value = sc.nextInt();
                ok = true;
            } catch (Exception ignored) {
                System.out.println("Awaiting an integer, please try again...");
                sc.nextLine();  // Escaping the '\n'
                ok = false;
            }
        } while (!ok);

        return value;
    }

    /**
     * Securing input for a number.
     * <p>Ask the user to input a number between min and max and a boolean condition.
     *
     * <p>This method uses {@link IntInput#askInt(String)}.
     *
     * @param message Message to advertise the user of the awaited action
     * @param min     Minimum allowed value (inclusive)
     * @param max     Maximum allowed value (inclusive)
     * @return The value typed by the user
     * @see IntInput#askInt(String, int, int)
     * @see IntInput#askIntMax(String, int)
     */
    public static int askInt(String message, int min, int max, boolean bool) {
        int value = 0;

        do {
            value = askInt(message);

            if (value < min) {
                System.out.println("The value you entered is too small, expecting at least" + min + "...");
            } else if (value > max) {
                System.out.println("The value you entered is too big, expecting at most" + max + "...");
            }
        } while (value < min || value > max || !bool);

        return value;
    }

    /**
     * Securing input for a number.
     * <p>Ask the user to input a number between min and max.
     * <p>Example:
     *
     * <blockquote><pre>
     *     //Ask for a number between 0 and 100
     *     int number = IntInput.askInt("Choose a number > ", 0, 100);
     * </pre></blockquote>
     *
     * <p>This method uses {@link IntInput#askInt(String, int, int, boolean)}.
     *
     * @param message Message to advertise the user of the awaited action
     * @param min     Minimum allowed value (inclusive)
     * @param max     Maximum allowed value (inclusive)
     * @return The value typed by the user
     * @see IntInput#askInt(String)
     * @see IntInput#askIntMax(String, int)
     */
    public static int askInt(String message, int min, int max) {
        return IntInput.askInt(message, min, max, true);
    }

    /**
     * Securing input for a positive number.
     * <p>Ask the user to input a positive number less than max.
     * <p>Example:
     *
     * <blockquote><pre>
     *     //Ask for a positive number less than 100
     *     int number = IntInput.askInt("Choose a number > ", 100);
     * </pre></blockquote>
     *
     * @param message Message to advertise the user of the awaited action
     * @param max     Maximum allowed value (inclusive)
     * @return The value typed by the user
     */
    public static int askIntMax(String message, int max) {
        return IntInput.askInt(message, 0, max, true);
    }
}
