import java.util.ArrayList;
import java.util.List;

public class McCluskey {

    private static final int BIT_AT_ONE = 1;
    private static final int BIT_AT_ZERO = 0;
    private static final int BIT_AT_MINUS = -1;

    List<RowOfVerityTable> notCombinedRows;

    public McCluskey() {
        notCombinedRows = new ArrayList<>();
    }

    public RowOfVerityTable combineTwoRows(RowOfVerityTable firstRow, RowOfVerityTable secondRow) {
        BinaryValue binaryValueOfFirstRow = firstRow.getBinaryValue();
        BinaryValue binaryValueOfSecondRow = secondRow.getBinaryValue();
        // second row has more ones than the first because it has been sorted before
        int differenceOfNumberOfOnes = binaryValueOfSecondRow.getNumberOfOnes() - binaryValueOfFirstRow.getNumberOfOnes() ;
        int numberOfOnesOfDifference = 0; // two rows can have one 1 of diff but not have any one in common
        int indexOfTheOneToModify = -1;
        boolean areMinusCorrespondingBetweenRows = true;
        if (differenceOfNumberOfOnes == 1) { // only the number of one, not their position, they can have not any in common
            for (int index : binaryValueOfSecondRow.getIndexOfOnes()) {
                if (binaryValueOfFirstRow.getBinaryValue()[index] == BIT_AT_ZERO) {
                    indexOfTheOneToModify = index;
                    numberOfOnesOfDifference++;
                }
                if (numberOfOnesOfDifference > 1 || binaryValueOfFirstRow.getBinaryValue()[index] == BIT_AT_MINUS) {
                    indexOfTheOneToModify = -1;
                    break;
                }
            }
            if (indexOfTheOneToModify != -1) {
                for (int index : binaryValueOfSecondRow.getIndexOfMinus()) {
                    if (binaryValueOfFirstRow.getBinaryValue()[index] != BIT_AT_MINUS) {
                        areMinusCorrespondingBetweenRows = false;
                        break;
                    }
                }
                if (areMinusCorrespondingBetweenRows) {
                    Minterm[] mintermsCombination = new Minterm[firstRow.getNumberOfMinterms() + secondRow.getNumberOfMinterms()];
                    int indexOfMintermsCombination = 0;
                    for (Minterm minterm : firstRow.getMinterms()) {
                        mintermsCombination[indexOfMintermsCombination++] = minterm;
                    }
                    for (Minterm minterm : secondRow.getMinterms()) {
                        mintermsCombination[indexOfMintermsCombination++] = minterm;
                    }
                    firstRow.getBinaryValue().updateBinaryValueWithAMinus(indexOfTheOneToModify);
                    return new RowOfVerityTable(mintermsCombination, firstRow.getBinaryValue());
                }
            }
        }
        return null;
    }

}
