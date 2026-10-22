package model;

import java.util.ArrayList;
import java.util.List;

public class AuctionManager {
    private static AuctionManager instance;
    private List<Auction> auctions; // Quan hệ manages * với Auction

    private AuctionManager() {
        this.auctions = new ArrayList<>();
    }

    public static synchronized AuctionManager getInstance() {
        if (instance == null) {
            instance = new AuctionManager();
        }
        return instance;
    }

    public void addAuction(Auction a) {
        if (a != null) {
            this.auctions.add(a);
        }
    }

    public List<Auction> getAuctions() {
        return auctions;
    }
}