package id.sch.smktelkom_mlg.learn.yourdailynews.models;

import com.orm.SugarRecord;

/**
 * Created by Administrator on 10/12/2016.
 */

public class NewsDB extends SugarRecord {
    private String title;
    private String description;
    private String url;

    public NewsDB() {
    }

    public NewsDB(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
}
