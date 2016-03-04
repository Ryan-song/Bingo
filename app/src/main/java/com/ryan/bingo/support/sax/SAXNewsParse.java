package com.ryan.bingo.support.sax;

import com.ryan.bingo.modle.NewsBean;
import com.ryan.bingo.support.sax.SAXNewsHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by air on 16/2/28.
 */
public class SAXNewsParse {
    public static List<NewsBean> items;
    public static List<NewsBean> parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        SAXNewsHandler saxNewsHandler = new SAXNewsHandler();
        xmlReader.setContentHandler(saxNewsHandler);
        xmlReader.parse(new InputSource(is));
        items = saxNewsHandler.getItems();
//        for(int i = 0 ;i < items.size() ; i++){
//            System.out.println("F_SAXNewsParse: "+items.get(i).getTitle());
//        }
        return items;
    }
}
