import java.security.Key;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
    private HashMap ballot = new HashMap<String, BallotEntry>();
    private LinkedList<String> votes = new LinkedList<String>();
    private Scanner keyboard = new Scanner(System.in);
    private Integer total_votes = 0;
    ElectionData() {
    }
    public void processVote(String FirstVoteParam, String SecondVoteParam, String ThirdVoteParam)throws DuplicateVotesException, UnknownCandidateException{
    total_votes++;
    BallotEntry FirstVote = (BallotEntry) ballot.get(FirstVoteParam);FirstVote.addFirstVote();
    BallotEntry SecondVote = (BallotEntry) ballot.get(SecondVoteParam);SecondVote.addSecondVote();
    BallotEntry ThirdVote = (BallotEntry) ballot.get(ThirdVoteParam);ThirdVote.addThirdVote();
    }

    public void addCandidate(String Candidate) throws CandidateExistsException{
        if(ballot.containsKey(Candidate)){
            throw new CandidateExistsException(Candidate);
        }
        else{
            ballot.put(Candidate, new BallotEntry());
        }
    }
    public void printBallot() {
        System.out.println("The candidates are: ");
        ballot.forEach((key,value) -> { System.out.print(key+", "); });
    }

    public void screen() throws DuplicateVotesException, UnknownCandidateException {
        String FirstVote;
        String ThirdVote;
        String SecondVote;
        this.printBallot();
        System.out.println("Who do you want to vote for first?");
        FirstVote = keyboard.next();
        System.out.println("Who do you want to vote for next?");
        SecondVote = keyboard.next();
        System.out.println("Who do you want to vote for last?");
        ThirdVote = keyboard.next();
        processVote(FirstVote,SecondVote,ThirdVote);

    }


}
