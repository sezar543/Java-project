package model;

import javax.swing.*;

public class MyRowFilter extends RowFilter {
    private String minPrice;
    //private String maxPrice;

    public MyRowFilter(String minPrice) {
        this.minPrice = minPrice;
        //this.maxPrice = maxPrice;
    }

    @Override
    public boolean include(Entry entry) {
        double minPriceInt = Double.parseDouble(minPrice);
        return (double) entry.getValue(2) >= minPriceInt;
    }
}
