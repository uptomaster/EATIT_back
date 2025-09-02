package com.bapseguen.app.dto;

public class MemberSuspendDTO {
    private int memberNumber;        // 정지된 회원 번호
    private String suspendStartDate; // 정지 시작일
    private String suspendEndDate;   // 정지 종료일
    private int suspendReportCount;  // 신고 횟수 (정지 사유)

    public MemberSuspendDTO() {}

    public int getMemberNumber() { return memberNumber; }
    public void setMemberNumber(int memberNumber) { this.memberNumber = memberNumber; }

    public String getSuspendStartDate() { return suspendStartDate; }
    public void setSuspendStartDate(String suspendStartDate) { this.suspendStartDate = suspendStartDate; }

    public String getSuspendEndDate() { return suspendEndDate; }
    public void setSuspendEndDate(String suspendEndDate) { this.suspendEndDate = suspendEndDate; }

    public int getSuspendReportCount() { return suspendReportCount; }
    public void setSuspendReportCount(int suspendReportCount) { this.suspendReportCount = suspendReportCount; }

    @Override
    public String toString() {
        return "MemberSuspendDTO{" +
                "memberNumber=" + memberNumber +
                ", suspendStartDate='" + suspendStartDate + '\'' +
                ", suspendEndDate='" + suspendEndDate + '\'' +
                ", suspendReportCount=" + suspendReportCount +
                '}';
    }
}
