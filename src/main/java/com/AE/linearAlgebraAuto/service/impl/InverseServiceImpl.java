package com.AE.linearAlgebraAuto.service.impl;

import com.AE.linearAlgebraAuto.controller.exception.DetIsZeroException;
import com.AE.linearAlgebraAuto.service.InverseService;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.stereotype.Service;

@Service
public class InverseServiceImpl implements InverseService {
    private int n;
    private Fraction[][] matrix;
    private Fraction[][] inverseMatrix;

    @Override
    public Fraction[][] calculate(Fraction[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;

        //创建单位矩阵
        inverseMatrix = new Fraction[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    inverseMatrix[i][j] = Fraction.ONE;
                } else {
                    inverseMatrix[i][j] = Fraction.ZERO;
                }
            }
        }

        //遍历行运算
        for (int i = 0; i < n; i++) {
            //如果对角为零则找非零行
            if (matrix[i][i].equals(Fraction.ZERO)) {
                //是否存在非零
                boolean flag = false;
                for (int j = i + 1; j < n; j++) {
                    if (!matrix[j][i].equals(Fraction.ZERO)) {
                        flag = true;
                        syncSwap(i, j);
                    }
                }
                //找不到非零
                if (!flag) {
                    //行列式为0异常
                    throw new DetIsZeroException("行列式为零，不存在逆矩阵");
                }
            }

            //对角行化1
            syncSingleTo1(i);

            //其他行化0
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    syncTransform(i, j);
                }
            }
        }

        return inverseMatrix;
    }

    /**
     * 同步行交换
     *
     * @param l1 行1
     * @param l2 行2
     */
    private void syncSwap(int l1, int l2) {
        Fraction t;
        for (int i = 0; i < n; i++) {
            t = matrix[l1][i];
            matrix[l1][i] = matrix[l2][i];
            matrix[l2][i] = t;
            t = inverseMatrix[l1][i];
            inverseMatrix[l1][i] = inverseMatrix[l2][i];
            inverseMatrix[l2][i] = t;
        }
    }

    /**
     * 单行同步化为1
     *
     * @param l 行位置
     */
    private void syncSingleTo1(int l) {
        Fraction x = matrix[l][l];
        matrix[l][l] = Fraction.ONE;
        for (int i = l + 1; i < n; i++) {
            matrix[l][i] = matrix[l][i].divideBy(x);
        }
        for (int i = 0; i < n; i++) {
            inverseMatrix[l][i] = inverseMatrix[l][i].divideBy(x);
        }
    }

    /**
     * 同步行变换
     *
     * @param mainLine 不变行
     * @param subLine  变化行
     */
    private void syncTransform(int mainLine, int subLine) {
        Fraction x = matrix[subLine][mainLine];
        matrix[subLine][mainLine] = Fraction.ZERO;
        for (int i = mainLine + 1; i < n; i++) {
            matrix[subLine][i] = matrix[subLine][i].subtract(matrix[mainLine][i].multiplyBy(x));
        }
        for (int i = 0; i < n; i++) {
            inverseMatrix[subLine][i] = inverseMatrix[subLine][i].subtract(inverseMatrix[mainLine][i].multiplyBy(x));
        }
    }

    private Double[][] matrixD;
    private Double[][] inverseMatrixD;

    @Override
    public Double[][] calculate(Double[][] matrix) {
        this.matrixD = matrix;
        n = matrixD.length;

        //创建单位矩阵
        inverseMatrixD = new Double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    inverseMatrixD[i][j] = 1D;
                } else {
                    inverseMatrixD[i][j] = 0D;
                }
            }
        }

        //遍历行运算
        for (int i = 0; i < n; i++) {
            //如果对角为零则找非零行
            if (matrixD[i][i] == 0) {
                //是否存在非零
                boolean flag = false;
                for (int j = i + 1; j < n; j++) {
                    if (matrixD[j][i] != 0) {
                        flag = true;
                        syncSwapD(i, j);
                    }
                }
                //找不到非零
                if (!flag) {
                    //行列式为0异常
                    throw new DetIsZeroException("行列式为零，不存在逆矩阵");
                }
            }

            //对角行化1
            syncSingleTo1D(i);

            //其他行化0
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    syncTransformD(i, j);
                }
            }
        }

        return inverseMatrixD;
    }

    /**
     * 同步行交换
     *
     * @param l1 行1
     * @param l2 行2
     */
    private void syncSwapD(int l1, int l2) {
        Double t;
        for (int i = 0; i < n; i++) {
            t = matrixD[l1][i];
            matrixD[l1][i] = matrixD[l2][i];
            matrixD[l2][i] = t;
            t = inverseMatrixD[l1][i];
            inverseMatrixD[l1][i] = inverseMatrixD[l2][i];
            inverseMatrixD[l2][i] = t;
        }
    }

    /**
     * 单行同步化为1
     *
     * @param l 行位置
     */
    private void syncSingleTo1D(int l) {
        Double x = matrixD[l][l];
        matrixD[l][l] = 1D;
        for (int i = l + 1; i < n; i++) {
            matrixD[l][i] = matrixD[l][i] / x;
        }
        for (int i = 0; i < n; i++) {
            inverseMatrixD[l][i] = inverseMatrixD[l][i] / x;
        }
    }

    /**
     * 同步行变换
     *
     * @param mainLine 不变行
     * @param subLine  变化行
     */
    private void syncTransformD(int mainLine, int subLine) {
        Double x = matrixD[subLine][mainLine];
        matrixD[subLine][mainLine] = 0D;
        for (int i = mainLine + 1; i < n; i++) {
            matrixD[subLine][i] = matrixD[subLine][i] - matrixD[mainLine][i] * x;
        }
        for (int i = 0; i < n; i++) {
            inverseMatrixD[subLine][i] = inverseMatrixD[subLine][i] - inverseMatrixD[mainLine][i] * x;
        }
    }
}
