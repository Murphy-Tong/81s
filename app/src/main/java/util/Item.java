package util;

/**
 * Created by TONG on 2016/12/30.
 */
public class Item {
    private String imgUrl;
    private String title;
    private String confg;
    private String link;

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {

        return link;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConfg(String confg) {
        this.confg = confg;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getConfg() {
        return confg;
    }
}
