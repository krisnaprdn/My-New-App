package com.example.mynewapplication.Model;

import com.google.gson.annotations.SerializedName;

public class Results {
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("ingredients")
    private String ingredients;
    @SerializedName("href")
    private String href;
    @SerializedName("title")
    private String title;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
