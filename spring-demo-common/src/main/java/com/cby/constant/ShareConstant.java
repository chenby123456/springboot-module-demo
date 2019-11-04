/*
 */
package com.cby.constant;


/**
 * @ClassName: ShareConstant
 * @Description: 共用常量
 * @Author: donglin.ni
 * @Date: 2019/8/21 15:22
 * @Version: 1.0.0
 */
public class ShareConstant {

    public final static String CHAR_0 = "0";
    public final static String CHAR_1 = "1";
    public final static String CHAR_2 = "2";
    public final static String CHAR_3 = "3";
    public final static String CHAR_4 = "4";
    public final static String CHAR_5 = "5";
    public final static String CHAR_6 = "6";
    public final static String CHAR_7 = "7";
    public final static String CHAR_8 = "8";
    public final static String CHAR_9 = "9";

    public final static int NUM_0 = 0;
    public final static int NUM_1 = 1;
    public final static int NUM_2 = 2;
    public final static int NUM_3 = 3;
    public final static int NUM_4 = 4;
    public final static int NUM_5 = 5;
    public final static int NUM_6 = 6;
    public final static int NUM_7 = 7;
    public final static int NUM_8 = 8;
    public final static int NUM_9 = 9;

    public final static long LONG_0 = 0;
    public final static long LONG_1 = 1;
    public final static long LONG_2 = 2;
    public final static long LONG_3 = 3;
    public final static long LONG_4 = 4;
    public final static long LONG_5 = 5;
    public final static long LONG_6 = 6;
    public final static long LONG_7 = 7;
    public final static long LONG_8 = 8;
    public final static long LONG_9 = 9;

    /*通用是否删除  1.未删除 2.已删除*/
    public final static byte BYTE_0 = 0;
    public final static byte BYTE_1 = 1;
    public final static byte BYTE_2 = 2;
    public final static byte BYTE_3 = 3;
    public final static byte BYTE_4 = 4;
    public final static byte BYTE_5 = 5;
    public final static byte BYTE_6 = 6;
    public final static byte BYTE_7 = 7;
    public final static byte BYTE_8 = 8;
    public final static byte BYTE_9 = 9;
    public final static byte BYTE_10 = 10;
    public final static byte BYTE_11 = 11;
    public final static byte BYTE_12 = 12;
    public final static byte BYTE_13 = 13;
    public final static byte BYTE_14 = 14;
    public final static byte BYTE_15 = 15;
    public final static byte BYTE_16 = 16;
    public final static byte BYTE_17 = 17;

    /*导入excel的列*/
    public final static int INT_0=0;
    public final static int INT_1=1;
    public final static int INT_2=2;
    public final static int INT_3=3;
    public final static int INT_4=4;
    public final static int INT_5=5;
    public final static int INT_6=6;
    public final static int INT_7=7;
    public final static int INT_8=8;
    public final static int INT_9=9;
    public final static int INT_10=10;
    public final static int INT_15=15;

    /*正则表达式验证 */
    public final static String MAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z0-9]{2,6}$";
    public final static String TEL = "^1[3|4|5|6|7|8][0-9]\\d{8}$";
    public final static String IDNO = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

    /*通用性别*/
    //1
    public final static String MAN = "男";
    //2
    public final static String WOMAN = "女";
    //3
    public final static String SEX_OTHER = "其他";

    /*通用排序方式*/
    //1
    public final static String ASC = "ASC";
    //2
    public final static String DESC = "DESC";

    /*通用婚姻状况*/
    //1
    public final static String MARRIED = "已婚";
    //2
    public final static String UN_MARRIED = "未婚";
    //3
    public final static String OTHER = "其他";
    //4
    public final static String SECRET = "保密";

    /*编码*/
    public final static String ENCODE = "UTF-8";

    /*血压判断值*/
    public final static int BLOOD_PRESSURE_80 = 80;
    public final static int BLOOD_PRESSURE_89 = 89;
    public final static int BLOOD_PRESSURE_90 = 90;
    public final static int BLOOD_PRESSURE_99 = 99;
    public final static int BLOOD_PRESSURE_100 = 100;
    public final static int BLOOD_PRESSURE_109 = 109;
    public final static int BLOOD_PRESSURE_110 = 110;
    public final static int BLOOD_PRESSURE_120 = 120;
    public final static int BLOOD_PRESSURE_139 = 139;
    public final static int BLOOD_PRESSURE_140 = 140;
    public final static int BLOOD_PRESSURE_159 = 159;
    public final static int BLOOD_PRESSURE_160 = 160;
    public final static int BLOOD_PRESSURE_179 = 179;
    public final static int BLOOD_PRESSURE_180 = 180;

    /*血糖判断值*/
    public final static double BLOOD_SUGAR_28 = 2.8;
    public final static double BLOOD_SUGAR_61 = 6.1;
    public final static double BLOOD_SUGAR_70 = 7.0;

    /*血压取检查code*/
    public final static String SYSTOLIC_ITEM_INDEX_MISCODE = "1.2.4.4";
    public final static String DIASTOLIC_ITEM_INDEX_MISCODE= "1.2.4.5";
    public final static String BLOOD_SUGAR_ITEM_INDEX_MISCODE= "2.1.4.67";

    /*个人用户excel表头值*/
    public final static String HEADER_ZERO = "姓名[必填]";
    public final static String HEADER_ONE = "性别[必填]";
    public final static String HEADER_TWO = "身份证号码[必填]";
    public final static String HEADER_THREE = "手机号码[必填]";
    public final static String HEADER_FOUR = "婚姻状况[必填]";
    public final static String HEADER_FIVE = "常住地区";
    public final static String HEADER_SIX = "QQ";
    public final static String HEADER_SEVEN = "邮箱";
    public final static String HEADER_EIGHT = "客户备注";
    public final static String HEADER_NINE = "主要问题";
}
