import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryValue {

    private final int[] binaryValue;
    private final List<Integer> indexOfOnes;

    private final List<Integer> indexOfMinus;

    public BinaryValue(int[] binaryValue, List<Integer> indexOfOnes, List<Integer> indexOfMinus) {
        this.binaryValue = binaryValue;
        this.indexOfOnes = indexOfOnes;
        this.indexOfMinus = indexOfMinus;
    }

    public void updateBinaryValueWithAMinus(int indexOfBinaryValueToUpdate) {
        this.binaryValue[indexOfBinaryValueToUpdate] = -1;
        this.indexOfOnes.remove(indexOfBinaryValueToUpdate);
        this.indexOfMinus.add(indexOfBinaryValueToUpdate);
        Collections.sort(this.indexOfMinus);
    }

    public int[] getBinaryValue() {
        return binaryValue;
    }

    public int getNumberOfOnes() {
        return this.indexOfOnes.size();
    }

    public int getNumberOfMinus() {
        return this.indexOfMinus.size();
    }

    public List<Integer> getIndexOfMinus() {
        return indexOfMinus;
    }

    public List<Integer> getIndexOfOnes() {
        return indexOfOnes;
    }
}
