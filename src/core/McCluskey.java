package core;

import IO.Output;
import models.BinaryValue;
import models.Minterm;
import models.RowOfVerityTable;
import models.VerityTable;

import java.util.ArrayList;
import java.util.List;

import static IO.InputToData.integerToBinaryValueRepresentation;
import static IO.InputToData.stringInputOfMintermsToIntegerArray;
import static IO.Output.displayPrimeImplicants;

public class McCluskey {

    private static final int BIT_AT_ONE = 1;
    private static final int BIT_AT_ZERO = 0;
    private static final int BIT_AT_MINUS = -1;

    List<RowOfVerityTable> notCombinedRows;

    VerityTable currentTable;

    public McCluskey() throws VerityTable.UnfoundCategoryInHashMap {
        notCombinedRows = new ArrayList<>();
        currentTable = new VerityTable();
        createInitialVerityTable(currentTable);
        launchAlgorithm();
    }

    public VerityTable getCurrentTable() {
        return currentTable;
    }

    private void createInitialVerityTable(VerityTable verityTable) {
        BinaryValue binaryValue;
        RowOfVerityTable rowOfVerityTableToAdd;
        int[] initialMinterms = stringInputOfMintermsToIntegerArray();
        for (int min : initialMinterms) {
            binaryValue = integerToBinaryValueRepresentation(min);
            Minterm[] mintermsArrayWithOneInitialElement = new Minterm[1];
            mintermsArrayWithOneInitialElement[0] = new Minterm(min, binaryValue);
            rowOfVerityTableToAdd = new RowOfVerityTable(mintermsArrayWithOneInitialElement, binaryValue, false);
            verityTable.addRowToTable(rowOfVerityTableToAdd);
        }
    }

    private void launchAlgorithm() throws VerityTable.UnfoundCategoryInHashMap {
        List<RowOfVerityTable> listOfCurrentTableRows;
        do {
            Output.displayTable(this.currentTable);
            this.currentTable = this.createNextTableFromCombinationOfRows();
            listOfCurrentTableRows = this.currentTable.getListOfRowsOfTable();
        } while (currentTable.getNumberOfRowsWhichHaveNotBeenCombined(listOfCurrentTableRows) != listOfCurrentTableRows.size());
        // We have to do it one last time so that we can get the prime implicants of the last table got
        Output.displayTable(this.currentTable);
        this.currentTable = this.createNextTableFromCombinationOfRows();
        displayPrimeImplicants(this.notCombinedRows);
    }

    public VerityTable createNextTableFromCombinationOfRows() throws VerityTable.UnfoundCategoryInHashMap {
        VerityTable newVerityTable = new VerityTable();
        List<RowOfVerityTable> rowsOfFirstCategory;
        List<RowOfVerityTable> rowsOfNextCategory;
        for (int category : this.getCurrentTable().getTable().keySet()) {
            rowsOfFirstCategory = this.getCurrentTable().getRowsOfCategory(category);
            if (this.currentTable.getTable().containsKey(category + 1)) {
                // if we are here, then there is two successives categories (one 1 of difference)
                rowsOfNextCategory = this.getCurrentTable().getRowsOfCategory(category + 1);
                for (RowOfVerityTable rowOfFirstCategory : rowsOfFirstCategory) {
                    for (RowOfVerityTable rowOfSecondCategory : rowsOfNextCategory) {
                        RowOfVerityTable combinationOfTwoRows = combineTwoRows(rowOfFirstCategory, rowOfSecondCategory);
                        if (combinationOfTwoRows != null) {
                            newVerityTable.addRowToTable(combinationOfTwoRows);
                        } else {
                            updateNotCombinedRow(rowOfFirstCategory, rowOfSecondCategory);
                        }
                    }
                }
            } else {
                for (RowOfVerityTable rowOfFirstCategory : rowsOfFirstCategory) {
                    if (!rowOfFirstCategory.isHasBeenCombined()) {
                        this.notCombinedRows.add(rowOfFirstCategory);
                    }
                }
            }
        }
        return newVerityTable;
    }

    private void updateNotCombinedRow(RowOfVerityTable firstRow, RowOfVerityTable secondRow) {
        if (firstRow.isHasBeenCombined()) {
            this.notCombinedRows.add(firstRow);
        }
        if (secondRow.isHasBeenCombined()) {
            this.notCombinedRows.add(secondRow);
        }
    }

    public RowOfVerityTable combineTwoRows(RowOfVerityTable firstRow, RowOfVerityTable secondRow) {
        BinaryValue binaryValueOfFirstRow = new BinaryValue(firstRow.getBinaryValue());
        BinaryValue binaryValueOfSecondRow = secondRow.getBinaryValue();
        // second row has more ones than the first because it has been sorted before
        int numberOfOnesOfDifference = 0; // two rows can have one 1 of diff but not have any one in common
        int indexOfTheOneToModify = -1;
        boolean areMinusCorrespondingBetweenRows = true;
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
                firstRow.setIsHasBeenCombined();
                secondRow.setIsHasBeenCombined();
                RowOfVerityTable rowOfVerityTableCopyingFirstRow = new RowOfVerityTable(mintermsCombination, binaryValueOfFirstRow, false);
                rowOfVerityTableCopyingFirstRow.getBinaryValue().updateBinaryValueWithAMinus(indexOfTheOneToModify);
                return rowOfVerityTableCopyingFirstRow;
            }
        }
        return null;
    }
}
