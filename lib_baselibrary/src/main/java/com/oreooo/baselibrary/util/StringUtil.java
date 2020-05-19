package com.oreooo.baselibrary.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmpty(String value) {
        if (value == null) {
            return true;
        }
        if ("".equals(value)) {
            return true;
        }
        if ("".equals(value.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String value) {
        if (isEmpty(value)) {
            return false;
        }
        return true;
    }


    public static final char UNDERLINE = '_';

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 格式化名称
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param, String underline) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        if (param.indexOf(underline) == -1) {
            return param.toLowerCase();
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile(underline).matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    public static String[] split(String value, String split) {
        if (isNotEmpty(value)) {
            String[] items = value.split(split);
            if (items != null && items.length > 0) {
                List<String> list = new ArrayList<>();
                for (String item : items) {
                    if (isNotEmpty(item)) {
                        list.add(item);
                    }
                }
                return (String[]) list.toArray();
            }
        }
        return new String[]{};
    }


    /**
     * 获取字符串的长度，中文占一个字符,英文数字占半个字符
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static float getlength(String value, EditText control) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                valueLength = valueLength + 1;
            } else {
                // 其他字符长度为0.5
                valueLength = valueLength + 0.5;
            }
        }
        // 进位取整
        return (int) (Math.ceil(valueLength) * 2);
    }

    /**
     * 获取字符串在手机屏幕上显示的大小
     *
     * @param value 指定的字符串
     * @return 字符串显示的大小，单位为px
     */
    public static float getWidth(String value, EditText control) {
        float valueWdith = 0;

        TextPaint textPaint = control.getPaint();
        for (int i = 0; i < value.length(); i++) {
            // 获取一个字符
            String temp = value.substring(i, i + 1);
            valueWdith = valueWdith + textPaint.measureText(temp);//获取该字符长度

        }
        return valueWdith;
    }

    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(EditText context, float dpValue) {
        float scale = context.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


//    /**
//     * EditText输入内容类型控制
//     *
//     * @param control    控制的EditText
//     * @param selectType 控制输入内容类型
//     */
//    public static void textInputFilter(EditText control, String selectType) {
//
//        /**
//         * 小数点后的数字的位数
//         */
//        int POINTER_LENGTH = 2;
//
//        String POINTER = ".";
//
//
//        //用于匹配输入的是0-9  .  这几个数字和字符
//        String p = "[0123456789]";
//
//        String o = "[01]";
//
//        /**
//         * 输入小写转换成大写，需要添加标志位判断是否启用
//         * */
//        InputFilter turn = new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                return null;
//            }
//        };
//        /**
//         * 限制输入字数，适用于手机号等，需要添加标志位判断是否启用
//         * */
//        InputFilter numberLength = new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                return null;
//            }
//        };
//        /*
//        if(true) {
//            turn = new InputFilter.AllCaps();
//        }*/
//        if (selectType.equals("手机")) {
//            numberLength = new InputFilter.LengthFilter(11);
//        }
//        if (selectType.equals("邮编")) {
//            numberLength = new InputFilter.LengthFilter(6);
//        }
//        /**
//         * 中文字符
//         */
//        String chinese = "/[\u4e00-\u9fa5]/";
//        /**
//         * 数字、英文字符
//         */
//        String EnglishNumber = "[0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]";
//        /**
//         * 设置输入字符过滤监听
//         */
//        InputFilter[] filter = new InputFilter[]{
//                new InputFilter() {
//                    @Override
//                    /**
//                     * @param source 输入的文字
//                     * @param start 输入-0，删除-0
//                     * @param end 输入-source文字的长度，删除-0
//                     * @param dest 原先显示的内容
//                     * @param dstart 输入-原光标位置，删除-光标删除结束位置
//                     * @param dend  输入-原光标位置，删除-光标删除开始位置
//                     * @return
//                     */
//                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                        for (int i = start; i < end; i++) {
//                            switch (selectType) {
//                                case "单据日期":
//                                    if (!Character.isDigit(source.charAt(i)) && !Character.toString(source.charAt(i)).equals("-")) {//只接收日期格式 0000-00-00
//                                        return "";
//                                    }
//                                    break;
//                                case "手机":
//                                    if (!Character.isDigit(source.charAt(i))) { //只接收数字
//                                        return "";
//                                    }
//                                    break;
//                                case "邮编":
//                                    if (!Character.isDigit(source.charAt(i))) { //只接收数字
//                                        return "";
//                                    }
//                                    break;
//                                case "ENNUCH":
//                                    if (!Character.isLetter(source.charAt(i)) && !Character.isDigit(source.charAt(i))) { //只接收数字、英文、中文
//                                        return "";
//                                    }
//                                    break;
//                                case "xxxx":
//                                    if (end > 0)//替换输入内容为xx
//                                        return "xx";
//                                    break;
//                                case "xx":
//                                    if (end > 0)//无法输入
//                                        return "";
//                                    break;
//                                default:
//                                    break;
//                            }
//                        }
//                        return null;
//                    }
//                },
//                numberLength,//限制输入字数
//                turn//转换大写
//        };
//        control.setFilters(filter);
//    }


    /**
     * 文本反转
     *
     * @param text
     * @return
     */
    public static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    /**
     * 获取字符串所占屏幕长度
     *
     * @param view
     * @param text
     * @return
     */
    public static float calcTextWidth(TextView view, String text) {
        if (StringUtil.isEmpty(text)) {
            return 0;
        }
        TextPaint textPaint = view.getPaint();
        return textPaint.measureText(text);
    }

    /**
     * 输入框文字过长时，转换字符内容为中间带省略符。。。的字符串（垃圾方法）
     */
    public static String get12Sub(String value, EditText control) {
        try {
            if (value != null && control != null) {//先判断传递value和control不为空，任意一个为空则不做处理，返回空值，否则会出现空指针错误
                boolean isLong = false;//超出长度标志位

                float textViewWdith = control.getWidth();//获取EditText长度

                StringBuilder sb = new StringBuilder();//用于设置省略字符串
                for (int i = 0; i < value.length(); i++) {//设置前端显示内容
                    // 获取一个字符
                    String temp = value.substring(i, i + 1);
                    sb.append(temp);
                    if (getWidth(sb.toString(), control) >= (textViewWdith - dip2px(control, 40))) {//当前Sb字符串长度超过EditText长度，退出
                        isLong = true;//标志位置true
                        break;
                    }
                }
                if (sb.length() > 6 && isLong) {
                    sb.delete(sb.length() - 6, sb.length());
                    sb.append("…");
                    for (int j = 3; j > 0; j--) {
                        String temp2 = value.substring(value.length() - j, value.length() - j + 1);
                        sb.append(temp2);
                    }
                    Log.d("xb", "-->输入文本长度：" + getWidth(value, control));
                    Log.d("xb", "-->输入框长度：" + control.getWidth());
                    Log.d("xb", "-->dp长度：" + dip2px(control, 40));
                    return sb.toString();
                }
            }
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * 如果文本超出控件宽度，省略部份内容
     *
     * @param view 控件
     * @param text 文本内容
     * @return
     */
    public static String Ellipsis(TextView view, String text) {
        return Ellipsis(view, text, 0);
    }

    /**
     * 如果文本超出控件宽度，省略部份内容（更好方法）
     *
     * @param view     控件
     * @param text     文本内容
     * @param position 省略位置(0：中间 1：后面)
     * @return
     */
    private static String Ellipsis(TextView view, String text, int position) {
        String replace = "…";
        float viewWdith = view.getWidth();
        if (viewWdith > 0) {
            viewWdith = viewWdith - view.getPaddingLeft() - view.getPaddingRight();
            float cutWidth = 0;//省略文本长度
            if (position == 1) {//省略位置在后的情况下，省略文本长度为控件TextView长度减省略符长度
                cutWidth = viewWdith - calcTextWidth(view, replace);
            } else {//省略位置在中间的情况下，获取控件TextView长度减省略符长度/2的长度会方便后面的计算
                cutWidth = (viewWdith - calcTextWidth(view, replace)) / 2;
            }
            boolean isCut = false;
            int cutLenght = -1;
            int endLenght = -1;
            //循环计算追加后的字符串长度是否大于文本框长度
            for (int i = 1; i <= text.length(); i++) {
                float total = calcTextWidth(view, text.substring(0, i));

                if (total >= cutWidth) {
                    if (cutLenght == -1) {
                        cutLenght = i;
                    }
                }
                if (total > viewWdith) {
                    isCut = true;
                    break;
                }
            }
            if (isCut) {
                if (position == 0) {
                    String reverse = reverse(text);//采用反转文本的方式，实现获取首位字符的同时获取对应的末位字符
                    for (int i = 1; i <= reverse.length(); i++) {
                        float total = calcTextWidth(view, reverse.substring(0, i));
                        if (total >= cutWidth) {
                            if (endLenght == -1) {
                                endLenght = i - 1;
                                break;
                            }
                        }
                    }
                    //首部文本 + "…" + 末部文本
                    return text.substring(0, cutLenght) + replace + text.substring(text.length() - endLenght);
                } else {
                    return text.substring(0, cutLenght) + replace;
                }
            }
        }
        return text;
    }

    /**
     * 日期格式化
     *
     * @param format 格式
     * @return
     */
    public static String DateFormat(String format) {
        return DateFormat(format, new Date());
    }

    /**
     * 日期格式化
     *
     * @param format 格式
     * @param date   日期
     * @return
     */
    public static String DateFormat(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 日期格式化
     *
     * @param format 格式
     * @param date   日期
     * @return
     */
    public static Date DateFormat(String format, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        return sdf.parse(date, pos);
    }

    /**
     * 小数格式化(删除小数后面的零)
     *
     * @param value 值
     * @return
     */
    public static String DoubleFormat(String value) {
        int index = value.indexOf(".");
        String replace = "";
        if (index != -1) {
            String dicContent = value.substring(index);
            char[] items = dicContent.toCharArray();
            for (int i = items.length - 1; i >= 0; i--) {
                if (String.valueOf(items[i]).equals("0")) {
                    replace = String.valueOf(items[i]) + replace;
                } else if (String.valueOf(items[i]).equals(".")) {
                    replace = String.valueOf(items[i]) + replace;
                } else {
                    break;
                }
            }
        }

        return value.replace(replace, "");
    }

    /**
     * 小数格式化(删除小数后面的零)
     *
     * @param value 值
     * @return
     */
    public static String DoubleFormat(Double value) {
        if (value == null) {
            return "";
        }
        return DoubleFormat(value.toString());
    }

    /**
     * 小数格式化
     *
     * @param value 值
     * @param dic   小数位数
     * @return
     */
    public static Double DoubleFormat(Double value, int dic) {
        if (value != null) {
            return new BigDecimal(value).setScale(dic, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return 0D;
    }

    /**
     * 小数格式化
     *
     * @param value 值
     * @param dic   小数位数
     * @return
     */
    public static Double DoubleFormat(String value, int dic) {
        if (StringUtil.isNotEmpty(value)) {
            int index = value.indexOf(".");
            if (index != -1) {
                return new BigDecimal(value).setScale(dic, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            return Double.parseDouble(value);
        }
        return 0D;
    }

    // 将日期中的“-”转换为“/”
    public static String formatStr(String oldStr) {
        return oldStr.replace("-", "/");
    }

    /**
     * 更改字符串中部分字符颜色方法
     *
     * @param str   字符串
     * @param color 更改字符颜色值
     * @param start 更改字符位于字符串的起点
     * @param end   更改字符位于字符串的末位
     * @return
     */
    public static SpannableString changeStrColor(String str, int color, int start, int end) {
        SpannableString spannableString = new SpannableString(str);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);//初始化指定字符颜色
//        BackgroundColorSpan backSpan = new BackgroundColorSpan(Color.BLACK);//初始化指定字符背景色
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
//        SpannableStringBuilder style = new SpannableStringBuilder(text);
//        //设置指定位置textview的背景颜色
//        style.setSpan(new BackgroundColorSpan(Color.RED), 2, 5, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        //设置指定位置文字的颜色
//        style.setSpan(new ForegroundColorSpan(newBill.this.getResources().getColor(R.color.check_txt_now)), 5, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
    }

}
