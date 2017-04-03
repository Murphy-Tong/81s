package util;

import java.util.ArrayList;

/**
 * Created by TONG on 2016/12/31.
 */
public class ItemConfg {
    private ArrayList<String> imgUrls, infos;
    private int size;
    private String imgUrl;

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    private String movie_content;

    public String getMovie_content() {
        return movie_content;
    }

    public void setMovie_content(String movie_content) {
        this.movie_content = movie_content;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setImgUrls(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }

    public void setInfos(ArrayList<String> infos) {
        this.infos = infos;
    }

    public ArrayList<String> getInfos() {
        return infos;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDl1() {
        return dl1;
    }

    private String dl1;

    public void setDl1(String dl1) {
        this.dl1 = dl1;
    }
}
