public class CandidateExistsException extends Exception{

public CandidateExistsException(String Name){
System.out.println("Candidate "+Name+ " already exists");
}
}
