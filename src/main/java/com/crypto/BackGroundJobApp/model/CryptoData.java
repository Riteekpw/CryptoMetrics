package com.crypto.BackGroundJobApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CryptoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String coinId;  // Bitcoin, Matic, or Ethereum
    private double price;
    private double marketCap;
    private double change24h;
    private long timestamp;

    // Constructor
    public CryptoData(String coinId, double price, double marketCap, double change24h) {
        this.coinId = coinId;
        this.price = price;
        this.marketCap = marketCap;
        this.change24h = change24h;
        this.timestamp = System.currentTimeMillis();
    }

    // Default constructor for JPA
    public CryptoData() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getChange24h() {
        return change24h;
    }

    public void setChange24h(double change24h) {
        this.change24h = change24h;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Optionally, you could override toString() for better logging and debugging
    @Override
    public String toString() {
        return "CryptoData{" +
                "id=" + id +
                ", coinId='" + coinId + '\'' +
                ", price=" + price +
                ", marketCap=" + marketCap +
                ", change24h=" + change24h +
                ", timestamp=" + timestamp +
                '}';
    }
}
