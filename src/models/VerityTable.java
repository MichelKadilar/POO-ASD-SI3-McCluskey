package models;

import models.RowOfVerityTable;

import java.util.ArrayList;
import java.util.List;

public class VerityTable {

    private List<RowOfVerityTable> table;

    public VerityTable() {
        this.table = new ArrayList<>();
    }

    public VerityTable(List<RowOfVerityTable> table) {
        this.table = table;
    }

    public void addRowToTable(RowOfVerityTable rowOfVerityTable) {
        this.table.add(rowOfVerityTable);
    }

    public List<RowOfVerityTable> getListOfRowsOfTable() {
        return table;
    }
}
