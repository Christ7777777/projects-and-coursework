package com.example.drivewayparking.Model;

import java.util.Arrays;

public class ImageResponse {

    private String fileName;
    private String content;

    public ImageResponse(){

    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "fileName='" + fileName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
