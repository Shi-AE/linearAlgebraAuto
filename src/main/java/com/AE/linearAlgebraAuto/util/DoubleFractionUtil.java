package com.AE.linearAlgebraAuto.util;

import org.apache.commons.lang3.math.Fraction;

/**
 * double类型和Fraction类型转换工具
 */
public class DoubleFractionUtil {

    private DoubleFractionUtil() {}

    /**
     * double类型二维数组转换Fraction类型二维数组
     * @param doubles double类型二维数组
     * @return Fraction类型二维数组
     */
    public static Fraction[][] D2F2(Double[][] doubles) {
        int n = doubles.length;
        int m = doubles[0].length;
        Fraction[][] fractions = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                fractions[i][j] = Fraction.getFraction(doubles[i][j]);
            }
        }
        return fractions;
    }

    /**
     * Fraction类型二维数组转换double类型二维数组
     * @param fractions Fraction类型二维数组
     * @return double类型二维数组
     */
    public static Double[][] F2D2(Fraction[][] fractions) {
        int n = fractions.length;
        int m = fractions[0].length;
        Double[][] doubles = new Double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                doubles[i][j] = fractions[i][j].doubleValue();
            }
        }
        return doubles;
    }
}
