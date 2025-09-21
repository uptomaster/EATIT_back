package com.bapseguen.app.dto;

public class StoreFavoriteDTO {
    private int favoriteNumber;     // 찜 PK
    private int memberNumber;       // 회원 번호
    private String businessNumber;  // 가게 사업자 번호

    // 가게 정보
    private String businessName;    // 상호명
    private String storeImageSystemName;
    private String storeImageOriginalName;
    private String openTime;
    private String closeTime;

    // 통계 정보
    private double avgRating;   // 평균 별점
    private int reviewCount;    // 리뷰 개수
    private int menuCount;      // 판매중 메뉴 개수

    // ===== Getter / Setter =====
    public int getFavoriteNumber() {
        return favoriteNumber;
    }
    public void setFavoriteNumber(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }
    public int getMemberNumber() {
        return memberNumber;
    }
    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }
    public String getBusinessNumber() {
        return businessNumber;
    }
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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
    public String getOpenTime() {
        return openTime;
    }
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    public String getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
    public double getAvgRating() {
        return avgRating;
    }
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
    public int getReviewCount() {
        return reviewCount;
    }
    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
    public int getMenuCount() {
        return menuCount;
    }
    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }

    @Override
    public String toString() {
        return "StoreFavoriteDTO [favoriteNumber=" + favoriteNumber + ", memberNumber=" + memberNumber
                + ", businessNumber=" + businessNumber + ", businessName=" + businessName
                + ", storeImageSystemName=" + storeImageSystemName + ", storeImageOriginalName=" + storeImageOriginalName
                + ", openTime=" + openTime + ", closeTime=" + closeTime
                + ", avgRating=" + avgRating + ", reviewCount=" + reviewCount + ", menuCount=" + menuCount + "]";
    }
}
