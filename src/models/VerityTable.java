package models;

import models.RowOfVerityTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerityTable {

    private Map<Integer, List<RowOfVerityTable>> table; // Integer => Category : 0, 1, 2...

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
            this.table.replace(rowOfVerityTable.getBinaryValue().getNumberOfOnes(), rowsOfCurrentNumberOfOnes);
        } else {
            List<RowOfVerityTable> rows = new ArrayList<>();
            rows.add(rowOfVerityTable);
            this.table.put(rowOfVerityTable.getBinaryValue().getNumberOfOnes(), rows);
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
        if (category < 0 || category > this.table.size()) {
            throw new UnfoundCategoryInHashMap(category);
        }
        return this.table.get(category);
    }

    public static class UnfoundCategoryInHashMap extends Exception {
        public UnfoundCategoryInHashMap(int category) {
            super("The category you asked : " + category + " does not exist in the current table");
        }
    }
}
