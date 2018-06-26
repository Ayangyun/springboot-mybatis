package cn.yasung.utils;

import cn.yasung.constants.Constant;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;


import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadQNImg {

    public String upload(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类
       // Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(new Configuration());
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(Constant.accessKey,Constant.secretKey);
            String upToken = auth.uploadToken(Constant.bucket);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = Constant.url + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private int orderDate(Date date) {
        int dateSum = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        System.out.println(dateStr);
        int year = Integer.valueOf(dateStr.substring(0, 4));
        int month = Integer.valueOf(dateStr.substring(5, 7));
        int day = Integer.valueOf(dateStr.substring(8, 10));
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dateSum += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dateSum += 30;
                    break;
                case 2:
                    if (((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0))
                        dateSum += 29;
                    else dateSum += 28;
            }
        }
        return dateSum = dateSum + day;
    }

}
