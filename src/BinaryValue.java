public class BinaryValue {

    private final int[] mintermBinaryValue;
    private final int numberOfOnes;

    public BinaryValue(int[] binaryValue, int numberOfOnes) {
        this.mintermBinaryValue = binaryValue;
        this.numberOfOnes = numberOfOnes;
    }

    public int[] getMintermBinaryValue() {
        return mintermBinaryValue;
    }

    public int getNumberOfOnes() {
        return numberOfOnes;
    }
}
