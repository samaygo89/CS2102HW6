/**
 * The UnknownCandidateException is a subclass of exception meant to capture when a candidate who is not on the ballot is voted for by a voter
 */
public class UnknownCandidateException extends Exception {
    /**
     * The name of the candidate who is not on the ballot but was voted for
     */
    private String name;

    /**
     * Getter
     * @return the name of the candidate who is not on the ballot but was voted for
     */
    public String getName() {
        return name;
    }

    /**
     * Constructor
     * @param name of the candidate who is not on the ballot but was voted for
     */
    public UnknownCandidateException(String name) {
        this.name = name;
    }
}
