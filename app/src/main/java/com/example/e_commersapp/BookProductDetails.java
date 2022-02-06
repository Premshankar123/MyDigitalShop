package com.example.e_commersapp;

public class BookProductDetails {
    String id,pctname,pctprice,pctoff,pctabout,size,qty,pctimg;

    public BookProductDetails(String id, String pctname, String pctprice, String pctoff, String pctabout, String size, String qty, String pctimg) {
        this.id = id;
        this.pctname = pctname;
        this.pctprice = pctprice;
        this.pctoff = pctoff;
        this.pctabout = pctabout;
        this.size = size;
        this.qty = qty;
        this.pctimg = pctimg;
    }

    public String getId() {
        return id;
    }

    public String getPctname() {
        return pctname;
    }

    public String getPctprice() {
        return pctprice;
    }

    public String getPctoff() {
        return pctoff;
    }

    public String getPctabout() {
        return pctabout;
    }

    public String getSize() {
        return size;
    }

    public String getQty() {
        return qty;
    }

    public String getPctimg() {
        return pctimg;
    }
}
