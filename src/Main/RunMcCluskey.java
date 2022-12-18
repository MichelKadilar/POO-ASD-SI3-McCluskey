package Main;

import IO.Output;
import core.McCluskey;

import java.util.Scanner;


public class RunMcCluskey {

    public static int NOMBRE_DE_VARIABLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberOfVariables = scanner.nextLine();
        NOMBRE_DE_VARIABLES = Integer.parseInt(numberOfVariables);
        /*int[] numbers = InputToData.stringInputOfMintermsToIntegerArray();
        for (int i = 0; i < numbers.length; i++) {
            BinaryValue binaryValue = InputToData.integerToBinaryValueRepresentation(numbers[i]);
            int[] binary = binaryValue.getBinaryValue();
            System.out.print(numbers[i] + " : ");
            for (int j = 0; j < binary.length; j++) {
                System.out.print(binary[j]);
            }
            System.out.print(" ");
            System.out.println(binaryValue.getNumberOfOnes());
        }*/
        Output output = new Output();
        McCluskey mcCluskey = new McCluskey();
        output.displayTable(mcCluskey.getCurrentTable());
    }
}