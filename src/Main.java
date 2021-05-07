public class Main {

    public static void main(String[] args) {
        VotingMachine test = new VotingMachine();
        test.addWriteIn("Gompei");
        test.addWriteIn("Husky");
        test.addWriteIn("Whale");
        test.startVoting();
    }
}
