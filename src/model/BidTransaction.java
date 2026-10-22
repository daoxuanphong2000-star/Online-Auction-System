package model;

public class BidTransaction {
    private String bidId;
    private Bidder bidder;
    private double amount;
    private String time;

    public BidTransaction(String bidId, Bidder bidder, double amount, String time) {
        this.bidId = bidId;
        this.bidder = bidder;
        this.amount = amount;
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public String getBidId() {
        return bidId;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public String getTime() {
        return time;
    }
}