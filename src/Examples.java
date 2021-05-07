import org.junit.Test;
import static org.junit.Assert.*;

public class Examples {
    ElectionData election = new ElectionData();
    VotingMachine votingMachine = new VotingMachine();

    ElectionData SetupOne() {

        ElectionData electionData = new ElectionData();

        try {
            electionData.addCandidate("gompei");
            electionData.addCandidate("husky");
            electionData.addCandidate("whale");
        } catch (Exception e) {
        }
        try {
            electionData.processVote("gompei", "husky", "whale");
            electionData.processVote("gompei", "whale", "husky");
            electionData.processVote("husky", "gompei", "whale");
        } catch (Exception e) {
        }

        return (electionData);
    }

    // now run a test on a specific election
    @Test
    public void testMostFirstWinner() {
        assertEquals("gompei", SetupOne().findWinnerMostFirstVotes());
    }

    @Test
    public void testMostFirstVotesWinner() {
        assertEquals("gompei", SetupOne().findWinnerMostFirstVotes());
    }


    public ElectionData SetupTwo() throws CandidateExistsException {
        ElectionData electionData = new ElectionData();

        electionData.addCandidate("gompei");
        electionData.addCandidate("husky");
        electionData.addCandidate("whale");

        return electionData;
    }

    @Test(expected = CandidateExistsException.class)
    public void testCandidateExistException() throws CandidateExistsException {
        SetupTwo().addCandidate("husky");
    }

    public ElectionData SetupThree() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
        ElectionData electionData = new ElectionData();

        electionData.addCandidate("husky");
        electionData.addCandidate("gompei");
        electionData.addCandidate("shark");

        electionData.processVote("shark", "shark", "gompei");

        return electionData;
    }

    @Test(expected = DuplicateVotesException.class)
    public void testDuplicateVotesException() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        SetupThree().findWinnerMostFirstVotes();
    }

    @Test(expected = UnknownCandidateException.class)
    public void testUnknownCandidateException() throws DuplicateVotesException, UnknownCandidateException, CandidateExistsException {
        election.addCandidate("Gompei");
        election.addCandidate("Husky");
        election.addCandidate("Shark");
        election.processVote("Gompei", "Giraffe", "Shark");
    }

    ElectionData SetupForTie() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
        ElectionData electionData = new ElectionData();

        electionData.addCandidate("gompei");
        electionData.addCandidate("husky");
        electionData.addCandidate("shark");

        electionData.processVote("shark", "gompei", "husky");
        electionData.processVote("shark", "gompei", "husky");
        electionData.processVote("gompei", "shark", "husky");
        electionData.processVote("gompei", "shark", "husky");

        return electionData;
    }

    @Test
    public void testForTie() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        assertEquals("gompei", SetupForTie().findWinnerMostFirstVotes());
    }

    @Test
    public void testFindWinnerMostPoints() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
        election.addCandidate("Gompei");
        election.addCandidate("Husky");
        election.addCandidate("Shark");

        election.processVote("Gompei", "Husky", "Shark");
        election.processVote("Husky", "Gompei", "Shark");
        election.processVote("Husky", "Gompei", "Shark");
        election.processVote("Husky", "Shark", "Gompei");

        assertEquals("Husky", election.findWinnerMostPoints());
    }

    @Test
    public void testFindWinnerMostFirstVotes() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
        election.addCandidate("Gompei");
        election.addCandidate("Husky");
        election.addCandidate("Shark");

        election.processVote("Gompei", "Husky", "Shark");
        election.processVote("Husky", "Gompei", "Shark");
        election.processVote("Husky", "Gompei", "Shark");
        election.processVote("Husky", "Shark", "Gompei");

        assertEquals("Husky", election.findWinnerMostFirstVotes());
    }
}

