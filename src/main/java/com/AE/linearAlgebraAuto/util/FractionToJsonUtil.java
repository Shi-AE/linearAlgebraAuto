package com.AE.linearAlgebraAuto.util;

import org.apache.commons.lang3.math.Fraction;

import java.lang.reflect.Array;

/**
 * 分数类解析成JSON字符串
 */
public class FractionToJsonUtil {
    private FractionToJsonUtil() {
    }

    /**
     * 解析单个分数
     *
     * @param object 分数
     * @return json
     */
    static public String singleToJson(Object object) {
        if (!(object instanceof Fraction)) {
            throw new ClassCastException();
        }
        StringBuilder json = new StringBuilder(object.toString());
        json.insert(0, '"').append('"');
        return json.toString();
    }

    /**
     * 解析所有维度的分数数组
     * @param a 分数数组
     * @return json
     */
    static public String arrayToJson(Object a) {
        if (a == null)
            return "null";

        if (a.getClass().isArray()) {
            int iMax = Array.getLength(a) - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(arrayToJson(Array.get(a, i)));
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        } else {
            return singleToJson(a);
        }
    }
}
