import java.util.Scanner;

public class Main {

    public static int NOMBRE_DE_VARIABLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberOfVariables = scanner.nextLine();
        NOMBRE_DE_VARIABLES = Integer.parseInt(numberOfVariables);
        int[] numbers = InputToData.stringInputOfMintermsToIntegerArray();
        for (int i = 0; i < numbers.length; i++) {
           int[] binaryValue = InputToData.integerToBinaryValueRepresentation(numbers[i]).getMintermBinaryValue();
            System.out.print(numbers[i] + " : ");
            for(int j=0; j<binaryValue.length;j++ ){
                System.out.print(binaryValue[j]);
            }
            System.out.println();
        }
    }
}