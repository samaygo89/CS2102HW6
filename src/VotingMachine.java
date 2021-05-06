import java.util.Scanner;

public class VotingMachine {
    private Scanner keyboard = new Scanner(System.in);
    private ElectionData newBooth = new ElectionData();
    public void addWriteIn(String name) {
        try{
        newBooth.addCandidate(name);}
        catch(CandidateExistsException e){
            //no need to handle this, this is never reached, cant have a write in that already exists, control would never get here
        }
    }
    public void startVoting()  {
        while (true) {
            try{
                newBooth.screen();
            } catch (UnknownCandidateException e) {
                System.out.println(e.getName()+" not found. Would you like to add this candidate to the ballot? Enter y for yes, Enter anything else to restart voting screen");
                String decision = keyboard.next();
                if(decision.equalsIgnoreCase("y")){
                    addWriteIn(e.getName());
                    continue;
                }
                else{
                    continue;
                }
            } catch (DuplicateVotesException e) {
            System.out.println("You may not vote for " +e.getName()+ " more than once");
            continue;
            }
        }
        }
    }

