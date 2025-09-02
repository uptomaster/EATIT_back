package com.bapseguen.app.dto;

public class PostReportDTO {
    private int postReportNumber;   // 신고 번호
    private int postNumber;         // 신고된 게시글 번호
    private String postReportReason; // 신고 사유 (ADV, BADWORDS, ...)
    private String postReportDate;   // 신고 일자 (문자열 처리)
    private int postReportCount;     // 신고 횟수
    private int memberNumber;        // 신고자 회원 번호

    public PostReportDTO() {}

    public int getPostReportNumber() { return postReportNumber; }
    public void setPostReportNumber(int postReportNumber) { this.postReportNumber = postReportNumber; }

    public int getPostNumber() { return postNumber; }
    public void setPostNumber(int postNumber) { this.postNumber = postNumber; }

    public String getPostReportReason() { return postReportReason; }
    public void setPostReportReason(String postReportReason) { this.postReportReason = postReportReason; }

    public String getPostReportDate() { return postReportDate; }
    public void setPostReportDate(String postReportDate) { this.postReportDate = postReportDate; }

    public int getPostReportCount() { return postReportCount; }
    public void setPostReportCount(int postReportCount) { this.postReportCount = postReportCount; }

    public int getMemberNumber() { return memberNumber; }
    public void setMemberNumber(int memberNumber) { this.memberNumber = memberNumber; }

    @Override
    public String toString() {
        return "PostReportDTO{" +
                "postReportNumber=" + postReportNumber +
                ", postNumber=" + postNumber +
                ", postReportReason='" + postReportReason + '\'' +
                ", postReportDate='" + postReportDate + '\'' +
                ", postReportCount=" + postReportCount +
                ", memberNumber=" + memberNumber +
                '}';
    }
}
