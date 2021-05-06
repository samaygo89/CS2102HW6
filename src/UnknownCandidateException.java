public class UnknownCandidateException extends Exception{
    private String name;

    public String getName() {
        return name;
    }

    public UnknownCandidateException(String name) {
        this.name = name;
    }
}
