package com.bapseguen.app.dto;

public class StoreDTO {

    private String businessNumber;
    private int memberNumber;
    private String storeName;
    private String storeOpenDate;
    private String storeTel;
    private String storeAddress;
    private String storeAddressDetail;
    private String storeZipCode;
    private String storeOpenTime;
    private String storeCloseTime;
    private double latitude;
    private double longitude;
    private double distance;

    // ================= 상품 관련 =================
    private int itemNumber;
    private String itemName;
    private int itemPrice;
    private int itemImageNumber;
    private String itemImageSystemName;
    private String itemImageOriginalName;

    // ================= 가게 이미지 관련 =================
    private int storeImageNumber;
    private String storeImageSystemName;
    private String storeImageOriginalName;

    // ---------------- Getter / Setter ----------------
    public String getBusinessNumber() {
        return businessNumber;
    }
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
    public int getMemberNumber() {
        return memberNumber;
    }
    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getStoreOpenDate() {
        return storeOpenDate;
    }
    public void setStoreOpenDate(String storeOpenDate) {
        this.storeOpenDate = storeOpenDate;
    }
    public String getStoreTel() {
        return storeTel;
    }
    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel;
    }
    public String getStoreAddress() {
        return storeAddress;
    }
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
    public String getStoreAddressDetail() {
        return storeAddressDetail;
    }
    public void setStoreAddressDetail(String storeAddressDetail) {
        this.storeAddressDetail = storeAddressDetail;
    }
    public String getStoreZipCode() {
        return storeZipCode;
    }
    public void setStoreZipCode(String storeZipCode) {
        this.storeZipCode = storeZipCode;
    }
    public String getStoreOpenTime() {
        return storeOpenTime;
    }
    public void setStoreOpenTime(String storeOpenTime) {
        this.storeOpenTime = storeOpenTime;
    }
    public String getStoreCloseTime() {
        return storeCloseTime;
    }
    public void setStoreCloseTime(String storeCloseTime) {
        this.storeCloseTime = storeCloseTime;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public int getItemNumber() {
        return itemNumber;
    }
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
    public int getItemImageNumber() {
        return itemImageNumber;
    }
    public void setItemImageNumber(int itemImageNumber) {
        this.itemImageNumber = itemImageNumber;
    }
    public String getItemImageSystemName() {
        return itemImageSystemName;
    }
    public void setItemImageSystemName(String itemImageSystemName) {
        this.itemImageSystemName = itemImageSystemName;
    }
    public String getItemImageOriginalName() {
        return itemImageOriginalName;
    }
    public void setItemImageOriginalName(String itemImageOriginalName) {
        this.itemImageOriginalName = itemImageOriginalName;
    }

    public int getStoreImageNumber() {
        return storeImageNumber;
    }
    public void setStoreImageNumber(int storeImageNumber) {
        this.storeImageNumber = storeImageNumber;
    }
    public String getStoreImageSystemName() {
        return storeImageSystemName;
    }
    public void setStoreImageSystemName(String storeImageSystemName) {
        this.storeImageSystemName = storeImageSystemName;
    }
    public String getStoreImageOriginalName() {
        return storeImageOriginalName;
    }
    public void setStoreImageOriginalName(String storeImageOriginalName) {
        this.storeImageOriginalName = storeImageOriginalName;
    }

    @Override
    public String toString() {
        return "StoreDTO [businessNumber=" + businessNumber 
                + ", memberNumber=" + memberNumber 
                + ", storeName=" + storeName 
                + ", storeTel=" + storeTel 
                + ", storeAddress=" + storeAddress 
                + ", storeImageSystemName=" + storeImageSystemName
                + ", storeImageOriginalName=" + storeImageOriginalName
                + "]";
    }
}
