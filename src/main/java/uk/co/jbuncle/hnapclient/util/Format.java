/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.util;

import java.util.List;
import java.util.Map;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class Format {

    private static String format(Map<String, Object> map, int indent) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            sb.append(getIndent(indent)).append(key).append(" => ").append(format(value, indent + 1)).append("\n");
        }
        return sb.toString();
    }

    private static String format(List<Object> map, int indent) {
        StringBuilder sb = new StringBuilder();
        for (Object value : map) {
            sb.append(format(value, indent));
        }
        return sb.toString();
    }

    private static String getIndent(int indent) {
        String str = "\n";
        for (int i = 0; i < indent; i++) {
            str += '\t';
        }
        return str;
    }

    private static String format(String value, int index) {
        return getIndent(index) + value;
    }

    public static String format(Object value) {
        return format(value, 0);
    }
    public static String format(Object value, int indent) {
        if (value instanceof Map) {
            return format((Map) value, indent);
        } else if (value instanceof List) {
            return format((List) value, indent);
        } else {
            return format(value.toString(), indent);
        }
    }
}
