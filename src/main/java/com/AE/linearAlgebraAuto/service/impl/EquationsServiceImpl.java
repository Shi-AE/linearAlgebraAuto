package com.AE.linearAlgebraAuto.service.impl;

import com.AE.linearAlgebraAuto.controller.exception.InfinitelySolutionException;
import com.AE.linearAlgebraAuto.controller.exception.NoSolutionException;
import com.AE.linearAlgebraAuto.service.DetService;
import com.AE.linearAlgebraAuto.service.EquationsService;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EquationsServiceImpl implements EquationsService {

    @Autowired
    private DetService detService;

    @Override
    public Fraction[] calculate(Fraction[][] matrix) {
        int n = matrix[0].length;

        Fraction[][] squareMatrix = new Fraction[n][n];
        for (int i = 0; i < n; i++) {
            squareMatrix[i] = Arrays.copyOf(matrix[i], n);
        }
        Fraction Dx = detService.calculate(squareMatrix);

        boolean allZero = true;
        Fraction[] Di = new Fraction[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                squareMatrix[j] = Arrays.copyOf(matrix[j], n);
            }
            squareMatrix[i] = Arrays.copyOf(matrix[n], n);
            Di[i] = detService.calculate(squareMatrix);
            if (allZero && !Di[i].equals(Fraction.ZERO)) {
                allZero = false;
            }
        }

        if (Dx.equals(Fraction.ZERO)) {
            if (allZero) {
                throw new InfinitelySolutionException("无穷解");
            } else {
                throw new NoSolutionException("无解");
            }
        }

        for (int i = 0; i < n; i++) {
            Di[i] = Di[i].divideBy(Dx);
        }

        return Di;
    }

    @Override
    public Double[] calculate(Double[][] matrix) {
        int n = matrix[0].length;

        Double[][] squareMatrix = new Double[n][n];
        for (int i = 0; i < n; i++) {
            squareMatrix[i] = Arrays.copyOf(matrix[i], n);
        }
        Double Dx = detService.calculate(squareMatrix);

        boolean allZero = true;
        Double[] Di = new Double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                squareMatrix[j] = Arrays.copyOf(matrix[j], n);
            }
            squareMatrix[i] = Arrays.copyOf(matrix[n], n);
            Di[i] = detService.calculate(squareMatrix);
            if (allZero && Di[i] != 0) {
                allZero = false;
            }
        }

        if (Dx == 0) {
            if (allZero) {
                throw new InfinitelySolutionException("无穷解");
            } else {
                throw new NoSolutionException("无解");
            }
        }

        for (int i = 0; i < n; i++) {
            Di[i] = Di[i] / Dx;
        }

        return Di;
    }
}
