import java.util.Scanner;

public class VotingMachine {
    private Scanner keyboard = new Scanner(System.in);
    private ElectionData newBooth = new ElectionData();

    /**
     * @param name of the candidate to be written in/added to the ballot
     * tries to add the candidate to the ballot using the addCandidate method in ElectionDat
     * Catches the CandidateExistsException and returns because if the addWrite in method is called, that means the Candidate doesn't exist
     */
    public void addWriteIn(String name) {
        try {
            newBooth.addCandidate(name);
        } catch (CandidateExistsException e) {
           return;
        }
    }

    /**
     * Starts the voting process, in a while loop, tries the screen method in ElectionData class which prompts the user for their first, second, and third votes
     * Catches the UnknownCandidateException if a candidate who is not on the ballot is voted for by asking the user if he or she would like to add a write-in to the ballot(Adding the unknown candidate to the ballot)
     * Catches the DuplicateVotesException if a candidate is voted for more than once and tells the user this may not happen and restarts the voting process
     */
    public void startVoting() {
        while (true) {
            try {
                newBooth.screen();
            } catch (UnknownCandidateException e) {
                System.out.println(e.getName() + " not found. Would you like to add this candidate to the ballot? Enter y for yes, Enter anything else to restart voting screen");
                String decision = keyboard.next();
                if (decision.equalsIgnoreCase("y")) {
                    addWriteIn(e.getName());
                }
            } catch (DuplicateVotesException e) {
                System.out.println("You may not vote for " + e.getName() + " more than once");
            }
        }
    }
}

