package IO;

import models.Minterm;
import models.RowOfVerityTable;
import models.VerityTable;

import java.util.List;

public class Output {

    public static void displayTable(VerityTable verityTable) {
        List<RowOfVerityTable> rows = verityTable.getListOfRowsOfTable();
        for (RowOfVerityTable rowOfVerityTable : rows) {
            for (Minterm minterm : rowOfVerityTable.getMinterms()) {
                System.out.print(minterm.getMintermIntegerValue());
            }
            System.out.print(" : ");
            for (int bit : rowOfVerityTable.getBinaryValue().getBinaryValue()) {
                System.out.print(bit);
            }
            System.out.println();
        }
    }

    public static void displayPrimeImplicants(List<RowOfVerityTable> notCombinedRows) {
        for (RowOfVerityTable rowOfVerityTable : notCombinedRows) {
            if (rowOfVerityTable.isHasBeenCombined()) continue;
            for (Minterm minterm : rowOfVerityTable.getMinterms()) {
                System.out.print(minterm.getMintermIntegerValue());
            }
            System.out.println("");
        }
    }

}
