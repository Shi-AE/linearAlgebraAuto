package com.AE.linearAlgebraAuto.service.impl;

import com.AE.linearAlgebraAuto.controller.exception.DetIsZeroException;
import com.AE.linearAlgebraAuto.service.AdjointService;
import com.AE.linearAlgebraAuto.service.DetService;
import com.AE.linearAlgebraAuto.service.InverseService;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdjointServiceImpl implements AdjointService {

    @Autowired
    private InverseService inverseService;
    @Autowired
    private DetService detService;
    private int n;

    @Override
    public Fraction[][] calculate(Fraction[][] matrix) {
        n = matrix.length;
        try {
            Fraction[][] copyMatrix = new Fraction[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    copyMatrix[i][j] = matrix[i][j].negate().negate();
                }
            }
            Fraction[][] inverseMatrix = inverseService.calculate(copyMatrix);
            Fraction det = detService.calculate(matrix);
            Fraction[][] adjointMatrix = new Fraction[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjointMatrix[i][j] = inverseMatrix[i][j].multiplyBy(det);
                }
            }
            return adjointMatrix;
        } catch (DetIsZeroException e) {
            return solveDetIsZero(matrix);
        }
    }

    private Fraction[][] solveDetIsZero(Fraction[][] matrix) {
        Fraction[][] adjointMatrix = new Fraction[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjointMatrix[j][i] = getCofactor(matrix, i, j);
            }
        }
        return adjointMatrix;
    }

    private Fraction getCofactor(Fraction[][] matrix, int x, int y) {
        Fraction[][] cofactorMatrix = new Fraction[n - 1][n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != x && j != y) {
                    cofactorMatrix[i < x ? i : i - 1][j < y ? j : j - 1] = matrix[i][j].negate().negate();
                }
            }
        }
        return detService.calculate(cofactorMatrix);
    }

    @Override
    public Double[][] calculate(Double[][] matrix) {
        n = matrix.length;
        try {
            Double[][] copyMatrix = new Double[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(matrix[i], 0, copyMatrix[i], 0, n);
            }
            Double[][] inverseMatrix = inverseService.calculate(copyMatrix);
            Double det = detService.calculate(matrix);
            Double[][] adjointMatrix = new Double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjointMatrix[i][j] = inverseMatrix[i][j] * det;
                }
            }
            return adjointMatrix;
        } catch (DetIsZeroException e) {
            return solveDetIsZero(matrix);
        }
    }

    private Double[][] solveDetIsZero(Double[][] matrix) {
        Double[][] adjointMatrix = new Double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjointMatrix[j][i] = getCofactor(matrix, i, j);
            }
        }
        return adjointMatrix;
    }

    private Double getCofactor(Double[][] matrix, int x, int y) {
        Double[][] cofactorMatrix = new Double[n - 1][n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != x && j != y) {
                    cofactorMatrix[i < x ? i : i - 1][j < y ? j : j - 1] = matrix[i][j];
                }
            }
        }
        return detService.calculate(cofactorMatrix);
    }
}
