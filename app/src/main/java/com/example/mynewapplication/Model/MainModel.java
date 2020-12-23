package com.example.mynewapplication.Model;

public class MainModel {

    private int iv_homepagecontent;
    private String btn_homepagecontent, tv_title_homepage, tv_homepagedetail_list;

    public MainModel(){
        this.iv_homepagecontent = iv_homepagecontent;
        this.btn_homepagecontent = btn_homepagecontent;
        this.tv_title_homepage = tv_title_homepage;
        this.tv_homepagedetail_list = tv_homepagedetail_list;
    }

    public int getIv_homepagecontent() {
        return iv_homepagecontent;
    }

    public void setIv_homepagecontent(int iv_homepagecontent) {
        this.iv_homepagecontent = iv_homepagecontent;
    }

    public String getBtn_homepagecontent() {
        return btn_homepagecontent;
    }

    public void setBtn_homepagecontent(String btn_homepagecontent) {
        this.btn_homepagecontent = btn_homepagecontent;
    }

    public String getTv_title_homepage() {
        return tv_title_homepage;
    }

    public void setTv_title_homepage(String tv_title_homepage) {
        this.tv_title_homepage = tv_title_homepage;
    }

    public String getTv_homepagedetail_list() {
        return tv_homepagedetail_list;
    }

    public void setTv_homepagedetail_list(String tv_homepagedetail_list) {
        this.tv_homepagedetail_list = tv_homepagedetail_list;
    }
}
