/**
 * The CandidateExistsException is a subclass of exception meant to capture the exception of adding a candiadte to the ballot that already exists
 */
public class CandidateExistsException extends Exception {
    /**
     * Holds the name of the candidate who already exists
     */
String name;
    /**
     * Constructor
     * @param Name
     * Takes in the name of the candidate that already exists and prints the name of the candidate to the screen
     *
     */
    public CandidateExistsException(String Name) {
        this.name = Name;
        System.out.println("Candidate " + Name + " already exists");
    }
}
