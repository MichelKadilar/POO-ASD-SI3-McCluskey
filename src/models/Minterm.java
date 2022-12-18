package models;

public class Minterm {

    private final int mintermIntegerValue;
    private final BinaryValue mintermBinaryValueAndInfo;

    public Minterm(int minterm, BinaryValue mintermBinaryValue) {
        this.mintermIntegerValue = minterm;
        this.mintermBinaryValueAndInfo = mintermBinaryValue;
    }

    public int getMintermIntegerValue() {
        return mintermIntegerValue;
    }

    public BinaryValue getMintermBinaryValue() {
        return mintermBinaryValueAndInfo;
    }
}
