/**
 * The DuplicateVotesException is a subclass of exception meant to capture when a voter inputs the same candidate twice on his or her voting
 */
public class DuplicateVotesException extends Exception {
    /**
     * Holds name of candidate who was repeated during the voting
     */
    private String name;

    /**
     * Constructor
     * @param name
     * Takes in the name of the candidate that was repeated and assigns it to the name field of the class
     */
    public DuplicateVotesException(String name) {
        this.name = name;
    }

    /**
     * Default getter for the String field name
     * @return name of repeated candidate
     */
    public String getName() {
        return name;
    }
}
