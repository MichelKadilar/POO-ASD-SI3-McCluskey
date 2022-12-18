package models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Minterm minterm = (Minterm) o;

        return (mintermIntegerValue != minterm.mintermIntegerValue);
    }

    @Override
    public int hashCode() {
        int result = mintermIntegerValue;
        result = 31 * result + (mintermBinaryValueAndInfo != null ? mintermBinaryValueAndInfo.hashCode() : 0);
        return result;
    }
}
