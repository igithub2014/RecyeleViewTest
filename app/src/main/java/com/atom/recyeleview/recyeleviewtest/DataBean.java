package com.atom.recyeleview.recyeleviewtest;

import java.util.ArrayList;

/**
 *
 * Created by atom on 2016/5/24.
 */
public class DataBean {

    private String name;
    private String imgUrl;

    public DataBean(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
