/* Author:     Ryan McAllister-Grum
 * UIN:        661880584
 * Class:      20FA - Algorithms & Computation (20FA-OL-CSC482A-15037)
 * Assignment: 2 (Implement a Graph Library and a Graph Language Interpreting and Display Program)
 */

import java.io.IOException;
import java.io.InputStreamReader;

/** Runner executes a command-line interface for testing the GLParser's capability
 *  of reading .gl files and the capabilities of the various Graph subclasses.
 */
public class Runner {
    private static GLParser parser; // parser points to the program's instance of a GLParser.
    
    /** welcomeMessage returns a String containing this program's welcome message.
     * 
     * @return A String containing the welcome message for this program.
     */
    private static String welcomeMessage() {
        return "Welcome to Ryan McAllister-Grum's Assignment 2 GLParser and Graph Testing Program!";
    }
    
    public static void main(String[] args) {
        String input = ""; // input captures the user's command-line input.
        
        System.out.println(welcomeMessage()); // Output our welcome message.
        
        try (InputStreamReader reader = new InputStreamReader(System.in)) {
            char[] buff = new char[1000];
            int length;
            while(!input.equalsIgnoreCase("q")) {
                input = ""; // Reset the input buffer.
                System.out.println(); // Output a newline to ease reading error output.
                
                if (parser != null) {
                    System.out.println(parser);
                    parser = null;
                }
                
                System.out.println("Please enter in a .gl file to parse, or enter 'q' to quit:");
                length = reader.read(buff, 0, buff.length);
                if (length != 0)
                    input = String.copyValueOf(buff, 0, length).replace("\"", "").replace(System.lineSeparator(), "").replace("\n", "");
                
                if (!input.isBlank() && !input.isEmpty())
                    if (!input.equalsIgnoreCase("q"))
                        try {
                            parser = new GLParser(input);
                        } catch(IllegalArgumentException | SecurityException | IOException e) {
                            System.err.println("Error while creating a new GLParser: " + e);
                        }
            }
            
        } catch (IOException e) {
            System.err.println("Error while executing Runner: " + e);
        }
    }
}