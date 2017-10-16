package com.chunyang.util;

import net.sf.json.JsonConfig;  
import net.sf.json.processors.JsonValueProcessor;  
  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Locale;  
  
/**
 * 主要是对json数据格式的日期以及json的数据展示形式进行控制和解析
 * @author qinxuegang
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {  
  
    /** 
     * 日期显示格式 
     */  
    private String datePattern = "yyyy-MM-dd HH:mm:ss";  
  
    /** 
     * JsonDateValueProcessor 
     */  
    public JsonDateValueProcessor() {  
        super();  
    }  
  
    /** 
     * @param format 
     */  
    public JsonDateValueProcessor(String format) {  
        super();  
        this.datePattern = format;  
    }  
  
    /** 
     * @param value 
     * @param jsonConfig 
     * @return Object 
     */  
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
        return process(value);  
    }  
  
    /** 
     * @param key 
     * @param value 
     * @param jsonConfig 
     * @return Object 
     */  
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {  
        return process(value);  
    }  
  
    /** 
     * process 
     * 
     * @param value 
     * @return 
     */  
    private Object process(Object value) {  
        try {  
            if (value instanceof Date) {  
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.UK);  
                return sdf.format((Date) value);  
            }  
            return value == null ? "" : value.toString();  
        } catch (Exception e) {  
            return "";  
        }  
  
    }  
  
    /** 
     * @return the datePattern 
     */  
    public String getDatePattern() {  
        return datePattern;  
    }  
  
    /** 
     * @param pDatePattern the datePattern to set 
     */  
    public void setDatePattern(String pDatePattern) {  
        datePattern = pDatePattern;  
    }  
  
}  