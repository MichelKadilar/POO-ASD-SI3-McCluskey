package models;

import java.util.Arrays;

public class RowOfVerityTable {

    private Minterm[] minterms;
    private BinaryValue binaryValue;

    private boolean hasBeenCombined;

    public RowOfVerityTable(Minterm[] minterms, BinaryValue binaryValue, boolean hasBeenCombined) {
        this.minterms = minterms;
        this.binaryValue = binaryValue;
        this.hasBeenCombined = hasBeenCombined;
    }

    public int getNumberOfMinterms() {
        return this.minterms.length;
    }

    public BinaryValue getBinaryValue() {
        return binaryValue;
    }

    public Minterm[] getMinterms() {
        return minterms;
    }

    public boolean isHasBeenCombined() {
        return hasBeenCombined;
    }

    public void setIsHasBeenCombined() {
        this.hasBeenCombined = true;
    }

    @Override
    public String toString() {
        String str = "";
        for (int bit : this.getBinaryValue().getBinaryValue()) {
            str = bit + " ";
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RowOfVerityTable that = (RowOfVerityTable) o;

        if (this.getNumberOfMinterms() != that.getNumberOfMinterms()) {
            return false;
        } else {
            boolean mintermInCommon = false;
            for (Minterm min : that.getMinterms()) {
                for (Minterm min2 : this.minterms) {
                    if (min2 == min) {
                        mintermInCommon = true;
                        break;
                    }
                }
                if (!mintermInCommon) {
                    return false;
                }
                mintermInCommon = false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(minterms);
        result = 31 * result + (binaryValue != null ? binaryValue.hashCode() : 0);
        return result;
    }
}
