package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Main.RunMcCluskey.NOMBRE_DE_VARIABLES;

public class BinaryValue {

    private final int[] binaryValue;
    private final List<Integer> indexOfOnes;

    private final List<Integer> indexOfMinus;

    public BinaryValue(int[] binaryValue, List<Integer> indexOfOnes, List<Integer> indexOfMinus) {
        this.binaryValue = new int[NOMBRE_DE_VARIABLES];
        for (int i = 0; i < binaryValue.length; i++) {
            this.binaryValue[i] = binaryValue[i];
        }
        this.indexOfOnes = new ArrayList<>(indexOfOnes);
        this.indexOfMinus = new ArrayList<>(indexOfMinus);
    }

    public BinaryValue(BinaryValue binaryValue) {
        this.binaryValue = new int[NOMBRE_DE_VARIABLES];
        for (int i = 0; i < binaryValue.binaryValue.length; i++) {
            this.binaryValue[i] = binaryValue.binaryValue[i];
        }
        this.indexOfOnes = new ArrayList<>(binaryValue.indexOfOnes);
        this.indexOfMinus = new ArrayList<>(binaryValue.indexOfMinus);
    }

    public void updateBinaryValueWithAMinus(int indexOfBinaryValueToUpdate) {
        this.binaryValue[indexOfBinaryValueToUpdate] = -1;
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
