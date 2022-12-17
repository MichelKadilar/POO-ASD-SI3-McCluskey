import java.util.Scanner;

public class InputToData {

    // exceptions to raise
    public static int[] stringInputOfMintermsToIntegerArray() {
        Scanner scanner = new Scanner(System.in);
        String[] stringMinterms = scanner.nextLine().split(",");
        int[] minterms = new int[stringMinterms.length];
        for (int i = 0; i < stringMinterms.length; i++) {
            minterms[i] = stringToInteger(stringMinterms[i]);
        }
        return minterms;
    }

    public static int stringToInteger(String numberInString) {
        return Integer.parseInt(numberInString);
    }

    public static BinaryValue integerToBinaryValueRepresentation(int numberInInteger) {
        int[] nombreBinary = new int[Main.NOMBRE_DE_VARIABLES];
        int i = 3;
        int tmp = numberInInteger;
        int numberOfOneInBinaryValue = 0;
        int rest = 0;
        while (tmp > 0) {
            rest = tmp % 2;
            nombreBinary[i--] = rest;
            if (rest == 1) {
                numberOfOneInBinaryValue++;
            }
            tmp = tmp / 2;

        }
        return new BinaryValue(nombreBinary, numberOfOneInBinaryValue);
    }

}
