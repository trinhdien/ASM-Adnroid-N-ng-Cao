package dientcph27512.fpoly.asm_mob201_dientcph27512.TinTucLoader;

import android.graphics.Bitmap;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dientcph27512.fpoly.asm_mob201_dientcph27512.DTO.BaiBao;

public class TinTucLoader {
    private List<BaiBao> list = new ArrayList<>();
    private BaiBao baiBao;
    private String textTemp;
    private Bitmap bitmapTemp;

    public List<BaiBao> getList(InputStream inputStream) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(inputStream, null);
            int event = xmlPullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String tagName = xmlPullParser.getName();
                Log.d("zzzzzzz", "tagName: " + tagName);
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            baiBao = new BaiBao();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textTemp = xmlPullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (baiBao != null) {
                            if (tagName.equalsIgnoreCase("item")) {
                                list.add(baiBao);
                            }
                            if (tagName.equalsIgnoreCase("title")) {
                                baiBao.setName(textTemp);
                            }
                            if (tagName.equalsIgnoreCase("link")) {
                                baiBao.setLink(textTemp);
                            }
                        }
                        break;
                }
                event = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
