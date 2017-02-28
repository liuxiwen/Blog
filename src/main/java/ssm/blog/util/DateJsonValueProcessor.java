package ssm.blog.util;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * json-lib 日期处理类
 * Created by liuxiwen on 2017/1/16.
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

    private String format;

    public DateJsonValueProcessor(String format) {
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {

        if (o == null) {
            return "";
        }
        if (o instanceof Timestamp) {
            String str = new SimpleDateFormat(format).format((Timestamp) o);
        }
        if (o instanceof Date) {
            String str = new SimpleDateFormat(format).format((Date) o);
            return str;
        }
        return o.toString();
    }
}
