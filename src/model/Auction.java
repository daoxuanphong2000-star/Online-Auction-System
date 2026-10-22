package model;

import java.util.ArrayList;
import java.util.List;

public class Auction {
    private String auctionId;
    private Item item;
    private Seller seller;
    private double currentPrice;
    private String status;
    private List<BidTransaction> bids; // Quan hệ 1 has * với BidTransaction

    public Auction(String auctionId, Item item, Seller seller, double currentPrice) {
        this.auctionId = auctionId;
        this.item = item;
        this.seller = seller;
        this.currentPrice = currentPrice;
        this.status = "OPEN";
        this.bids = new ArrayList<>();
    }

    public void startAuction() {
        this.status = "RUNNING";
    }

    public void closeAuction() {
        this.status = "FINISHED";
    }

    // Nhận lượt đặt giá mới và kiểm tra hợp lệ
    public boolean receiveBid(BidTransaction bid) {
        if ("RUNNING".equals(this.status) && bid.getAmount() > this.currentPrice) {
            this.currentPrice = bid.getAmount();
            this.bids.add(bid);
            return true;
        }
        return false;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getStatus() {
        return status;
    }

    public List<BidTransaction> getBids() {
        return bids;
    }
}