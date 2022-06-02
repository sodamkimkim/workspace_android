package com.example.movie_1.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YTSData {

    private String status;
    @SerializedName("status_message")
    @Expose
    private String status_Message;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_Message() {
        return status_Message;
    }

    public void setStatus_Message(String status_Message) {
        this.status_Message = status_Message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
