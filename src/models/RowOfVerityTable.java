package models;

public class RowOfVerityTable {

    private Minterm[] minterms;
    private BinaryValue binaryValue;

    public RowOfVerityTable(Minterm[] minterms, BinaryValue binaryValue) {
        this.minterms = minterms;
        this.binaryValue = binaryValue;
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

    @Override
    public String toString() {
        String str = "";
        for (int bit : this.getBinaryValue().getBinaryValue()) {
            str = bit + " ";
        }
        return str;
    }
}
