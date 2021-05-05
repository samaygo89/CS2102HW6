public class Main {
    public static void main(String[] args) {
        ElectionData newBooth = new ElectionData();
        try {
            newBooth.screen();
        }catch(UnknownCandidateException e){
            //handle
        }
        catch (DuplicateVotesException e){
            //handle
        }
    }
}
