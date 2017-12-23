package online.morn.itime.util;

import java.util.Date;

/**
 * Date工具
 * @auther Horner 2017/12/16 16:36
 */
public class DateUtil {

    /**
     * 获得SQL时间戳
     * @auther Horner 2017/12/16 16:48
     * @return
     */
    public static Date getDate(){
        return new Date(System.currentTimeMillis());
    }

    /*public static void main(String[] args){
        System.out.println("---------" + DateUtil.getSqlDate());
    }*/
}
