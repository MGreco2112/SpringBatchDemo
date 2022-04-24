package com.batch.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BitcoinRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unixTimestamp;
    private String dateTime;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volumeBTC;
    private String volumeCurrency;
    private String weightedPrice;
    private String price;

    public BitcoinRecord(Long id, String unixTimestamp, String dateTime, String open, String high, String low, String close, String volumeBTC, String volumeCurrency, String weightedPrice, String price) {
        this.id = id;
        this.unixTimestamp = unixTimestamp;
        this.dateTime = dateTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volumeBTC = volumeBTC;
        this.volumeCurrency = volumeCurrency;
        this.weightedPrice = weightedPrice;
        this.price = price;
    }

    private BitcoinRecord() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnixTimestamp() {
        return unixTimestamp;
    }

    public void setUnixTimestamp(String unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolumeBTC() {
        return volumeBTC;
    }

    public void setVolumeBTC(String volumeBTC) {
        this.volumeBTC = volumeBTC;
    }

    public String getVolumeCurrency() {
        return volumeCurrency;
    }

    public void setVolumeCurrency(String volumeCurrency) {
        this.volumeCurrency = volumeCurrency;
    }

    public String getWeightedPrice() {
        return weightedPrice;
    }

    public void setWeightedPrice(String weightedPrice) {
        this.weightedPrice = weightedPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
