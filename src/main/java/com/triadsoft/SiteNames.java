package com.triadsoft;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 9:46 AM
 */
public class SiteNames {
    private String name;
    private String url;

    public SiteNames(String url){
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
