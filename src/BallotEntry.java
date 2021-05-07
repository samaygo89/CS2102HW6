public class BallotEntry {
    private int numFirstVotes;
    private int numSecondVotes;
    private int numThirdVotes;

    public BallotEntry() {
        numSecondVotes = numThirdVotes = numFirstVotes = 0;
    }

    public void addFirstVote() {
        numFirstVotes++;
    }

    public void addSecondVote() {
        numSecondVotes++;
    }

    public void addThirdVote() {
        numThirdVotes++;
    }

    public int getNumFirstVotes() {
        return numFirstVotes;
    }

    public int getNumSecondVotes() {
        return numSecondVotes;
    }

    public int getNumThirdVotes() {
        return numThirdVotes;
    }
}
