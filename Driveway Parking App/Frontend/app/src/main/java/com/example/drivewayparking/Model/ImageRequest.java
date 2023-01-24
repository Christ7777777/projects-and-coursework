package com.example.drivewayparking.Model;

public class ImageRequest {
    private Long userId;
    private Long propertyId;
    private Long disputeId;
    private Long adminId;
    private String fileName;

    public ImageRequest() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getDisputeId() {
        return disputeId;
    }

    public void setDisputeId(Long disputeId) {
        this.disputeId = disputeId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ImageRequest{" +
                "userId=" + userId +
                ", propertyId=" + propertyId +
                ", disputeId=" + disputeId +
                ", adminId=" + adminId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
