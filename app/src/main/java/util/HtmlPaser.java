package util;

import android.os.AsyncTask;

import com.example.tong.a81s.ItemConfgActivity;
import com.example.tong.a81s.PageFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class HtmlPaser {

    public static void parseInfo(final String loadUrl, final ItemConfgActivity itemConfgActivity) {
        new AsyncTask<String, Void, ItemConfg>() {

            @Override
            protected void onPostExecute(ItemConfg itemConfg) {
                if (itemConfgActivity != null && itemConfg != null) {
                    itemConfgActivity.fillContent(itemConfg);
                }

            }

            @Override
            protected ItemConfg doInBackground(String... params) {

                try {
                    Document document = Jsoup.parse(new URL(params[0]), 300000);

                    ItemConfg itemConfg = new ItemConfg();

                    Element movie_content = document.getElementById("movie_content");
                    itemConfg.setMovie_content(movie_content.text().toString().trim());


                    String attr = document.select(".noborder").select(".block1").select("img").attr("_src");
//                    Log.i("ifo", attr);
                    String title = document.select(".font14w").get(0).text().toString().trim();

                    String dl1 = document.select(".xunlei").select(".dlbutton1").select("a").attr("href").toString().trim();

                    itemConfg.setDl1(dl1);

                    itemConfg.setTitle(title);
                    itemConfg.setImgUrl("http:" + attr);
                    return itemConfg;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute(loadUrl);


    }


    public static void paserContent(String loadUrl, int page, final PageFragment pageFragment) {
        new AsyncTask<String, Void, ArrayList<Item>>() {
            @Override
            protected void onPostExecute(ArrayList<Item> arrayList) {

                if (pageFragment != null && arrayList != null) {
                    pageFragment.fillContent(arrayList);
                }
            }

            @Override
            protected ArrayList<Item> doInBackground(String... params) {
                return loadPage(params[0]);
            }
        }.execute(loadUrl + Integer.toString(page));

    }

    private static ArrayList<Item> loadPage(String param) {
        Document document = null;

        try {
            document = Jsoup.parse(new URL(param), 300000);
            if (document == null) return null;

            Elements elements = document.select(".me1").select(".clearfix");
            ArrayList<Item> items = new ArrayList<>();
            if (elements.size() > 0) {

                Element e = elements.get(0);
                Elements lis = e.getElementsByTag("li");

                for (Element ei : lis
                        ) {

                    Item item = new Item();
                    item.setConfg(ei.getElementsByTag("span").get(0).text());
                    item.setTitle(ei.getElementsByTag("h3").get(0).text());
                    item.setImgUrl("http:" + ei.getElementsByTag("img").get(0).attr("_src"));
                    item.setLink(GlobalResource.HOST + ei.getElementsByTag("a").get(0).attr("href"));

                    items.add(item);
                }

            }

            return items;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
