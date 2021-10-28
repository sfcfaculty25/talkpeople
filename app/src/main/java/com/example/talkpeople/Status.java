package com.example.talkpeople;

public class Status {
    private String imageUri;
    private long timeStamp;

    public Status() {

    }

    public Status(String imageUri, long timeStamp) {
        this.imageUri = imageUri;
        this.timeStamp = timeStamp;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
