package com.bapseguen.app.dto;

public class MemberSuspendDTO {
    private int memberNumber;          // 정지된 회원 번호
    private String memberName;         // 회원 이름 (조인으로 가져옴)
    private String memberType;         // 회원 유형 (GENERAL / SELLER)
    private String suspendStartDate;   // 정지 시작일
    private String suspendEndDate;     // 정지 종료일
    private int suspendReportCount;    // 신고 횟수 (정지 사유)

    public MemberSuspendDTO() {}

    public int getMemberNumber() { return memberNumber; }
    public void setMemberNumber(int memberNumber) { this.memberNumber = memberNumber; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getMemberType() { return memberType; }
    public void setMemberType(String memberType) { this.memberType = memberType; }

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
                ", memberName='" + memberName + '\'' +
                ", memberType='" + memberType + '\'' +
                ", suspendStartDate='" + suspendStartDate + '\'' +
                ", suspendEndDate='" + suspendEndDate + '\'' +
                ", suspendReportCount=" + suspendReportCount +
                '}';
    }
}
