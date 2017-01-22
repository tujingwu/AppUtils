package com.example.apputil.emoticonskeyboard.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sj.keyboard.data.EmoticonEntity;
import sj.keyboard.data.EmoticonPageSetEntity;
import sj.keyboard.utils.imageloader.ImageBase;

public class ParseDataUtils {

    public static ArrayList<EmoticonEntity> ParseQqData(HashMap<String, Integer> data) {
        Iterator iter = data.entrySet().iterator();
        if(!iter.hasNext()){
            return null;
        }
        ArrayList<EmoticonEntity> emojis = new ArrayList<>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            EmoticonEntity entity = new EmoticonEntity();
            entity.setContent((String) key);
            entity.setIconUri("" + val);
            emojis.add(entity);
        }
        return emojis;
    }

    public static ArrayList<EmoticonEntity> ParseXhsData(String[] arry, ImageBase.Scheme scheme) {
        try {
            ArrayList<EmoticonEntity> emojis = new ArrayList<>();
            for (int i = 0; i < arry.length; i++) {
                if (!TextUtils.isEmpty(arry[i])) {
                    String temp = arry[i].trim().toString();
                    String[] text = temp.split(",");
                    if (text != null && text.length == 2) {
                        String fileName;
                        if (scheme == ImageBase.Scheme.DRAWABLE) {
                            if (text[0].contains(".")) {
                                fileName = scheme.toUri(text[0].substring(0, text[0].lastIndexOf(".")));
                            } else {
                                fileName = scheme.toUri(text[0]);
                            }
                        } else {
                            fileName = scheme.toUri(text[0]);
                        }
                        String content = text[1];
                        EmoticonEntity bean = new EmoticonEntity(fileName, content);
                        emojis.add(bean);
                    }
                }
            }
            return emojis;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<EmoticonEntity> parseKaomojiData(Context context) {
        ArrayList<EmoticonEntity> textEmotionArray = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open("kaomoji"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                EmoticonEntity bean = new EmoticonEntity(line.trim());
                textEmotionArray.add(bean);
            }
            return textEmotionArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static EmoticonPageSetEntity<EmoticonEntity> parseDataFromFile(Context context, String filePath, String assetsFileName, String xmlName) {
        String xmlFilePath = filePath + "/" + xmlName;
      //  Log.e("ddd","路径："+xmlFilePath+"===zip:"+assetsFileName);
        File file = new File(xmlFilePath);
        if (!file.exists()) {
            try {
               // Log.e("ddd","文件不存在存在：");
                FileUtils.unzip(context.getAssets().open(assetsFileName), filePath);
            } catch (IOException e) {
               // Log.e("ddd","异常："+e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        //Log.e("ddd","文件存在：");
        XmlUtil xmlUtil = new XmlUtil(context);
        return xmlUtil.ParserXml(filePath, xmlUtil.getXmlFromSD(xmlFilePath));
    }
}
