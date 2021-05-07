/**
 * The BallotEntry class is meant to hold the information of one ballot for one candidate
 */
public class BallotEntry {
    /**
     * Holds the number of First votes a candidate has received
     */
    private int numFirstVotes;
    /**
     * Holds the number of Second votes a candidate has received
     */
    private int numSecondVotes;
    /**
     * Holds the number of Third votes a candidate has received
     */
    private int numThirdVotes;

    public BallotEntry() {
        numSecondVotes = numThirdVotes = numFirstVotes = 0;
    }
    /**
     * Adds one to the the number of First votes a candidate has received
     */
    public void addFirstVote() {
        numFirstVotes++;
    }
    /**
     * Adds one to the number of Second votes a candidate has received
     */
    public void addSecondVote() {
        numSecondVotes++;
    }
    /**
     * Adds one to the number of Third votes a candidate has received
     */
    public void addThirdVote() {
        numThirdVotes++;
    }

    /**
     * @return the number of first votes
     */
    public int getNumFirstVotes() {
        return numFirstVotes;
    }
    /**
     * @return the number of second votes
     */
    public int getNumSecondVotes() {
        return numSecondVotes;
    }
    /**
     * @return the number of third votes
     */
    public int getNumThirdVotes() {
        return numThirdVotes;
    }
}
