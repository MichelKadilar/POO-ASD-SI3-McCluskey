package IO;

import Main.RunMcCluskey;
import models.BinaryValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputToData {

    // exceptions to raise when checking nextline
    public static int[] stringInputOfMintermsToIntegerArray() {
        Scanner scanner = new Scanner(System.in);
        String[] stringMinterms = scanner.nextLine().split(",");
        int[] minterms = new int[stringMinterms.length];
        for (int i = 0; i < stringMinterms.length; i++) {
            minterms[i] = stringToInteger(stringMinterms[i]);
            if (minterms[i] >= Math.pow(2, RunMcCluskey.NOMBRE_DE_VARIABLES)) {
                // raise exception
            }
        }
        return minterms;
    }

    public static int stringToInteger(String numberInString) {
        return Integer.parseInt(numberInString);
    }

    public static BinaryValue integerToBinaryValueRepresentation(int numberInInteger) {
        int[] nombreBinary = new int[RunMcCluskey.NOMBRE_DE_VARIABLES];
        int i = RunMcCluskey.NOMBRE_DE_VARIABLES - 1;
        int tmp = numberInInteger;
        List<Integer> indexOfOneInBinaryValue = new ArrayList<>();
        int rest;
        while (tmp > 0) {
            rest = tmp % 2;
            nombreBinary[i] = rest;
            if (rest == 1) {
                indexOfOneInBinaryValue.add(i);
            }
            tmp = tmp / 2;
            i--;
        }
        Collections.sort(indexOfOneInBinaryValue);
        return new BinaryValue(nombreBinary,
                indexOfOneInBinaryValue,
                new ArrayList<>());
    }

}
