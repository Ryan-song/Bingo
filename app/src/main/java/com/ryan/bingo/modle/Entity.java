package com.ryan.bingo.modle;

import android.util.Log;

import com.ryan.bingo.R;
import com.ryan.bingo.support.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * Created by air on 16/2/28.
 */

/**
 *
 * 获得R.raw.news_api里的信息 通过Doc的方式
 * newsUrl:     [国内, 国际, 军事, 图片, 娱乐, 艺术, 科技, 华人, 金融, 财经, 时政, 汽车, 法制, 教育]
 * newsTitle:   [http://www.xinhuanet.com/local/news_province.xml,~~~]
 *
 */
public class Entity {
    private static String[] newsUrl = null;
    private static String[] newsTitle = null;
    private static Document document = null;

    public static String[] getNewsUrl() {
        if (newsUrl == null) {
            if (document == null) {
                InputStream is = Utils.readFileFromRaw(R.raw.news_api);
                document = Utils.getDocumentByIS(is);
//                Log.d("F_Entity","document_data: "+document);
            }
            NodeList urlList = document.getElementsByTagName("url");
            newsUrl = new String[urlList.getLength()];
            for (int i = 0; i < urlList.getLength(); i++) {
                newsUrl[i] = urlList.item(i).getTextContent();
                System.out.println("Entity_getNewsUrl[ "+i+ "]"+urlList.item(i).getTextContent().toString());
            }
//            Log.d("Entity",newsUrl.toString());
        }
        return newsUrl;
    }

    public static String[] getNewsTitle() {
        if (newsTitle == null) {
            if (document == null) {
                InputStream is = Utils.readFileFromRaw(R.raw.news_api);
                document = Utils.getDocumentByIS(is);
            }
            NodeList titleList = document.getElementsByTagName("name");
            newsTitle = new String[titleList.getLength()];
            for (int i = 0; i < titleList.getLength(); i++) {
                newsTitle[i] = titleList.item(i).getTextContent();
                System.out.println("Entity_getNewsTitle[ "+i+"] "+titleList.item(i).getTextContent().toString());
            }
        }
        return newsTitle;
    }
}
