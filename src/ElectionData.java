import java.security.Key;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
    private HashMap ballot = new HashMap<String, BallotEntry>();
    private LinkedList<String> ballot_names = new LinkedList<String>();
    private Scanner keyboard = new Scanner(System.in);
    private int total_votes = 0;
    ElectionData() {}
    public String findWinnerMostFirstVotes(){
        double req_votes_for_win = total_votes/2;
        for(String e: ballot_names){
            BallotEntry currBallot = (BallotEntry) ballot.get(e);
            if(currBallot.getNumFirstVotes() >= req_votes_for_win){
                return e;
            }
        }
        return "Runoff required";
    }
    private int find_points(BallotEntry currBallot){
        return currBallot.getNumFirstVotes()*3 + currBallot.getNumSecondVotes()*2+currBallot.getNumThirdVotes();
    }
    public String findWinnerMostPoints(){
        int currMaxPoints = 0;
        String currWinner = "";
        for(String e: ballot_names){
            BallotEntry currBallot = (BallotEntry) ballot.get(e);
            int currPoints = find_points(currBallot);
            if(currPoints > currMaxPoints){
                currMaxPoints = currPoints;
                currWinner = e;
            }
        }
        return currWinner;
    }
    public void processVote(String FirstVoteParam, String SecondVoteParam, String ThirdVoteParam)throws DuplicateVotesException, UnknownCandidateException{
    total_votes++;
    if(!ballot.containsKey(FirstVoteParam)){
        throw new UnknownCandidateException(FirstVoteParam);
    }
    if(!ballot.containsKey(SecondVoteParam)){
        throw new UnknownCandidateException(SecondVoteParam);
    }
    if(!ballot.containsKey(ThirdVoteParam)){
        throw new UnknownCandidateException(ThirdVoteParam);
    }
    if(FirstVoteParam.equalsIgnoreCase(SecondVoteParam) || FirstVoteParam.equalsIgnoreCase(ThirdVoteParam) ){
        throw new DuplicateVotesException(FirstVoteParam);
    }
    if(SecondVoteParam.equalsIgnoreCase(ThirdVoteParam)){
        throw new DuplicateVotesException(SecondVoteParam);
    }

    BallotEntry FirstVote = (BallotEntry) ballot.get(FirstVoteParam);FirstVote.addFirstVote();
    BallotEntry SecondVote = (BallotEntry) ballot.get(SecondVoteParam);SecondVote.addSecondVote();
    BallotEntry ThirdVote = (BallotEntry) ballot.get(ThirdVoteParam);ThirdVote.addThirdVote();
    }

    public void addCandidate(String Candidate) throws CandidateExistsException{
        if(ballot.containsKey(Candidate)){
            throw new CandidateExistsException(Candidate);
        }
        else{
            ballot_names.add(Candidate);
            ballot.put(Candidate, new BallotEntry());
        }
    }
    public void printBallot() {
        System.out.println("The candidates are: ");
        ballot.forEach((key,value) -> { System.out.print(key+"\n"); });
    }

    public void screen() throws DuplicateVotesException, UnknownCandidateException {
        String FirstVote;
        String ThirdVote;
        String SecondVote;
        this.printBallot();
        System.out.println("Enter Votes separated by enter key");
        FirstVote = keyboard.next();
        System.out.print("\n");
        SecondVote = keyboard.next();
        System.out.print("\n");

        ThirdVote = keyboard.next();
        processVote(FirstVote,SecondVote,ThirdVote);
    }


}
