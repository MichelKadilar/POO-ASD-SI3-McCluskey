package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerityTable {

    private Map<Integer, List<RowOfVerityTable>> table; // Integer => Category : 0, 1, 2...

    private int maxCategory;

    public VerityTable() {
        this.table = new HashMap<>();
    }

    public VerityTable(Map<Integer, List<RowOfVerityTable>> table) {
        this.table = table;
    }

    public void addRowToTable(RowOfVerityTable rowOfVerityTable) {
        if (this.table.containsKey(rowOfVerityTable.getBinaryValue().getNumberOfOnes())) {
            List<RowOfVerityTable> rowsOfCurrentNumberOfOnes = this.table.get(rowOfVerityTable.getBinaryValue().getNumberOfOnes());
            rowsOfCurrentNumberOfOnes.add(rowOfVerityTable);
            int category = rowOfVerityTable.getBinaryValue().getNumberOfOnes();
            this.table.replace(category, rowsOfCurrentNumberOfOnes);
            if (category > maxCategory) {
                maxCategory = category;
            }
        } else {
            List<RowOfVerityTable> rows = new ArrayList<>();
            rows.add(rowOfVerityTable);
            int category = rowOfVerityTable.getBinaryValue().getNumberOfOnes();
            this.table.put(category, rows);
            if (category > maxCategory) {
                maxCategory = category;
            }
        }
    }

    public List<RowOfVerityTable> getListOfRowsOfTable() {
        List<RowOfVerityTable> rows = new ArrayList<>();
        for (Integer integer : this.table.keySet()) {
            rows.addAll(this.table.get(integer));
        }
        return rows;
    }

    public Map<Integer, List<RowOfVerityTable>> getTable() {
        return table;
    }

    public List<RowOfVerityTable> getRowsOfCategory(int category) throws UnfoundCategoryInHashMap {
        if (category < 0 || category > this.maxCategory) {
            throw new UnfoundCategoryInHashMap(category);
        }
        return this.table.get(category);
    }

    /*public int getNumberOfRowsWhichHaveNotBeenCombined(List<RowOfVerityTable> list) {
        int number = 0;
        for (RowOfVerityTable rowOfVerityTable : list) {
            if (!rowOfVerityTable.isHasBeenCombined()) number++;
        }
        return number;
    }*/

    public static class UnfoundCategoryInHashMap extends Exception {
        public UnfoundCategoryInHashMap(int category) {
            super("The category you asked : " + category + " does not exist in the current table");
        }
    }
}
