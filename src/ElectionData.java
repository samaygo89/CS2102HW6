import java.security.Key;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * ElectionData is a class meant to hold the internal structure of the voting machine
 */
class ElectionData {
    /**
     * Hashmap of BallotEntries where the key is the String which holds the name of the candidate
     */
    private HashMap ballot = new HashMap<String, BallotEntry>();
    /**
     * Linkedlist of all of the candidate names
     */
    private LinkedList<String> ballot_names = new LinkedList<String>();
    /**
     * Scanner for recieving keyboard inputs
     */
    private Scanner keyboard = new Scanner(System.in);
    /**
     * Holds the number of votes placed
     */
    private int total_votes = 0;
    ElectionData() {}

    /**
     * @return a String representing the name of the candidate who recived the most First votes or the one who recived over 50% of the first votes placed
     * Uses the LinkedList of names to access values in the ballot_names Hashmap
     * If there is a tie, the "Runoff required" string is returned
     */
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

    /**
     * @param currBallot
     * @return the number of points a BallotEntry has
     * Using the formula: Points = numFirstVotes*3 + numSecondVotes*2+numThirdVotes
     * This method calculates the points of a given BallotEntry
     */
    private int find_points(BallotEntry currBallot){
        return currBallot.getNumFirstVotes()*3 + currBallot.getNumSecondVotes()*2+currBallot.getNumThirdVotes();
    }

    /**
     *@return a String representing the name of the candidate who received the most points
     * Uses the find_points method to calculate the points of each candidate and uses accumulators to find the candidate with the max number of points
     */
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

    /**
     * @param FirstVoteParam string representing the name of the candidate who the voter elected first
     * @param SecondVoteParam string representing the name of the candidate who the voter elected second
     * @param ThirdVoteParam string representing the name of the candidate who the voter elected third
     * @throws DuplicateVotesException when a vote is placed for the same candidate twice
     * @throws UnknownCandidateException when a vote is placed for a candidate who is not on the ballot
     * First checks for Unknown candidates, then for duplicate candidates and throws the appropriate errors
     * then uses the addFirst, addSecond, and addThird votes methods in the BallotEntry class to place the votes in the appropriate fields in the appropriate candidates
     */
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
    // Now we can place the votes
    BallotEntry FirstVote = (BallotEntry) ballot.get(FirstVoteParam);FirstVote.addFirstVote();
    BallotEntry SecondVote = (BallotEntry) ballot.get(SecondVoteParam);SecondVote.addSecondVote();
    BallotEntry ThirdVote = (BallotEntry) ballot.get(ThirdVoteParam);ThirdVote.addThirdVote();
    }

    /**
     * @param Candidate string representing the name of the candidate to be added to the ballot
     * @throws CandidateExistsException if the name of the candidate to be added already is on the ballot, meaning the candidate already exists
     * First checks to see if the candidate already exists using the hashmap containsKey method
     * Then if the candidate doesn't already exist he or she is added to the ballot
     */
    public void addCandidate(String Candidate) throws CandidateExistsException{
        if(ballot.containsKey(Candidate)){
            throw new CandidateExistsException(Candidate);
        }
        else{
            ballot_names.add(Candidate);
            ballot.put(Candidate, new BallotEntry());
        }
    }

    /**
     * Prints out the names of all the candidates on the ballot using the Hashmap for-each loop and a lambda expression
     */
    public void printBallot() {
        System.out.println("The candidates are: ");
        ballot.forEach((key,value) -> { System.out.print(key+"\n"); });
    }

    /**
     * @throws DuplicateVotesException from the processVote method, thrown if the same candidate is voted for twice within one vote(e.g 1st and third vote for one candidate)
     * @throws UnknownCandidateException from the processVote method, thrown if a candidate who is not on the ballot is voted for
     * Prompts the user for their first, second, and third votes
     */
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
