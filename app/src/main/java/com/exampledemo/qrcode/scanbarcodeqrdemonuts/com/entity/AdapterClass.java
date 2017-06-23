package com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity;

/**
 * Created by TONMOYPC on 6/22/2017.
 */
public class AdapterClass {

    private int id;
    private String scannedbyname;
    private String scannedresult;
    private String date;
    private String note;

    public AdapterClass() {

    }

    public void setAll(int id, String scannedbyname, String scannedresult,String date,String note) {
        this.id = id;
        this.scannedbyname = scannedbyname;
        this.scannedresult = scannedresult;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScannedbyname() {
        return scannedbyname;
    }

    public void setScannedbyname(String scannedbyname) {
        this.scannedbyname = scannedbyname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScannedresult() {
        return scannedresult;
    }

    public void setScannedresult(String scannedresult) {
        this.scannedresult = scannedresult;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public AdapterClass(String scannedbyname, String scannedresult, String date, String note) {
        this.scannedbyname = scannedbyname;
        this.scannedresult = scannedresult;
        this.date = date;
        this.note = note;
    }

    public AdapterClass(int id, String scannedbyname, String scannedresult, String date, String note) {
        this.id = id;
        this.scannedbyname = scannedbyname;
        this.scannedresult = scannedresult;
        this.date = date;
        this.note = note;
    }

    public AdapterClass(String scannedbyname, String scannedresult, String date) {
        this.scannedbyname = scannedbyname;
        this.scannedresult = scannedresult;
        this.date = date;
    }

}