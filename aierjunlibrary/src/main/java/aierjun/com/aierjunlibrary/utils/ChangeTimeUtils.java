package aierjun.com.aierjunlibrary.utils;

import java.text.SimpleDateFormat;

/**
 * Created by aierJun on 2017/1/24.
 */

public class ChangeTimeUtils {

    //时间转换
    public static String changeTime(String timeNum){
        if (timeNum!=null){
            int m=Integer.valueOf(timeNum)/60;
            int s=Integer.valueOf(timeNum)%60;
            int ss=s/10;
            if (m<0){
                if (ss>0){
                    return "00:00:"+s+"";
                }
                return "00:00:0"+s+"";
            }else{
                int h=m/60;
                int f=m%60;
                int ff=m/10;
                int hh=h/10;
                if (h<=0){
                    if (ff>0){
                        if (ss<=0){
                            return "00:"+f+":0"+s;
                        }else {
                            return "00:"+f+":"+s;
                        }
                    }else{
                        if (ss<=0){
                            return "00:0"+f+":0"+s;
                        }else{
                            return "00:0"+f+":"+s;
                        }
                    }
                }else{
                    if (hh>0){
                        if (ff>0){
                            if (ss<=0){
                                return h+":"+f+":0"+s;
                            }else {
                                return h+":"+f+":"+s;
                            }
                        }else{
                            if (ss<=0){
                                return h+":0"+f+":0"+s;
                            }else{
                                return h+":0"+f+":"+s;
                            }
                        }
                    }else{
                        if (ff>0){
                            if (ss<=0){
                                return "0"+h+":"+f+":0"+s;
                            }else {
                                return h+":"+f+":"+s;
                            }
                        }else{
                            if (ss<=0){
                                return h+":0"+f+":0"+s;
                            }else{
                                return h+":0"+f+":"+s;
                            }
                        }
                    }
                }

            }
        }
        return null;
    }

    public static String changeTime(long time){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//初始化Formatter的转换格式。
        String hms = formatter.format(time);
        return hms;
    }
    public static String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;

        second = l.intValue() / 1000;

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return (getTwoLength(hour) + ":" + getTwoLength(minute)  + ":"  + getTwoLength(second));
    }

    private static String getTwoLength(final int data) {
        if(data < 10) {
            return "0" + data;
        } else {
            return "" + data;
        }
    }
}
