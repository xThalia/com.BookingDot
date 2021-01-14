package model;

public class OfferContent {
    private String where;

    private String whatPrice;

    private String whatHotel;

    private String rest;

    public OfferContent(String where, String whatPrice, String whatHotel, String rest) {
        this.where = where;
        this.whatPrice = whatPrice;
        this.whatHotel = whatHotel;
        this.rest = rest;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhatPrice() {
        return whatPrice;
    }

    public void setWhatPrice(String whatPrice) {
        this.whatPrice = whatPrice;
    }

    public String getWhatHotel() {
        return whatHotel;
    }

    public void setWhatHotel(String whatHotel) {
        this.whatHotel = whatHotel;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }
}
