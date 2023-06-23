package com.AE.linearAlgebraAuto.service.impl;

import com.AE.linearAlgebraAuto.service.DetService;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.stereotype.Service;

@Service
public class DatServiceImpl implements DetService {
    @Override
    public Fraction calculate(Fraction[][] matrix) {
        int n = matrix.length;
        return dfs(matrix, n - 1);
    }

    /**
     * 递归计算行列式
     * 高斯消元
     *
     * @param matrix 矩阵数据
     * @param n      阶数
     * @return 结果
     */
    private Fraction dfs(Fraction[][] matrix, int n) {
        if (n >= 0) {
            if (isZero(matrix, n)) {
                return Fraction.ZERO;
            }
            Fraction x = matrix[n][n];
            for (int i = 0; i < n; i++) {
                if (matrix[n][i].equals(Fraction.ZERO)) {
                    continue;
                }
                Fraction y = x.divideBy(matrix[n][i]);
                for (int j = 0; j < n; j++) {
                    try {
                        matrix[j][i] = matrix[j][i].subtract(matrix[j][n].divideBy(y));
                    } catch (ArithmeticException e) {
                        System.out.println(matrix[j][n]);
                        System.out.println(y);
                        System.out.println(matrix[j][i]);
                    }
                }
                matrix[n][i] = Fraction.ZERO;
            }
            return matrix[n][n].multiplyBy(dfs(matrix, n - 1));
        } else {
            return Fraction.ONE;
        }
    }

    private boolean isZero(Fraction[][] matrix, int n) {
        if (matrix[n][n].equals(Fraction.ZERO)) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (!matrix[n][i].equals(Fraction.ZERO)) {
                    flag = false;
                    Fraction t;
                    for (int j = 0; j <= n; j++) {
                        t = matrix[j][i].multiplyBy(Fraction.getFraction(-1));
                        matrix[j][i] = matrix[j][n];
                        matrix[j][n] = t;
                    }
                    break;
                }
            }
            return flag;
        } else {
            return false;
        }
    }

    @Override
    public Double calculate(Double[][] matrix) {
        int n = matrix.length;
        return dfs(matrix, n - 1);
    }

    public Double dfs(Double[][] matrix, int n) {
        if (n >= 0) {
            if (isZero(matrix, n)) {
                return 0D;
            }
            double x = matrix[n][n];
            for (int i = 0; i < n; i++) {
                if (matrix[n][i] == 0) {
                    continue;
                }
                double y = x / matrix[n][i];
                for (int j = 0; j < n; j++) {
                    matrix[j][i] = matrix[j][i] - matrix[j][n] / y;
                }
                matrix[n][i] = 0D;
            }
            return matrix[n][n] * dfs(matrix, n - 1);
        } else {
            return 1D;
        }
    }

    public boolean isZero(Double[][] matrix, int n) {
        if (matrix[n][n] == 0) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (matrix[n][i] != 0) {
                    flag = false;
                    double t;
                    for (int j = 0; j <= n; j++) {
                        t = -matrix[j][i];
                        matrix[j][i] = matrix[j][n];
                        matrix[j][n] = t;
                    }
                    break;
                }
            }
            return flag;
        } else {
            return false;
        }
    }
}
