import java.util.Scanner;

public class Main {

    public static int NOMBRE_DE_VARIABLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberOfVariables = scanner.nextLine();
        NOMBRE_DE_VARIABLES = Integer.parseInt(numberOfVariables);
        int[] numbers = InputToData.stringInputOfMintermsToIntegerArray();
        for (int i = 0; i < numbers.length; i++) {
            BinaryValue binaryValue = InputToData.integerToBinaryValueRepresentation(numbers[i]);
            int[] binary = binaryValue.getBinaryValue();
            System.out.print(numbers[i] + " : ");
            for (int j = 0; j < binary.length; j++) {
                System.out.print(binary[j]);
            }
            System.out.print(" ");
            System.out.println(binaryValue.getNumberOfOnes());
        }
        Minterm minterm = new Minterm(numbers[0], InputToData.integerToBinaryValueRepresentation(numbers[0]));
        Minterm[] minterms = new Minterm[1];
        minterms[0] = minterm;
        Minterm minterm2 = new Minterm(numbers[1], InputToData.integerToBinaryValueRepresentation(numbers[1]));
        Minterm[] minterms2 = new Minterm[1];
        minterms2[0] = minterm2;
        RowOfVerityTable rowOfVerityTable = new RowOfVerityTable(minterms, InputToData.integerToBinaryValueRepresentation(numbers[0]));
        RowOfVerityTable rowOfVerityTable1 = new RowOfVerityTable(minterms2, InputToData.integerToBinaryValueRepresentation(numbers[1]));
        McCluskey mcCluskey = new McCluskey();
        RowOfVerityTable rowOfVerityTable2 = mcCluskey.combineTwoRows(rowOfVerityTable, rowOfVerityTable1);
        System.out.println(rowOfVerityTable2.getNumberOfMinterms());
    }
}