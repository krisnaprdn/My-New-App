package com.example.mynewapplication.javapackage.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResponseModel {

    @SerializedName("results")
    private List<Results> results;
    @SerializedName("href")
    private String href;
    @SerializedName("version")
    private double version;
    @SerializedName("title")
    private String title;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
