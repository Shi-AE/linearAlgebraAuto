package com.AE.linearAlgebraAuto.service;

import org.apache.commons.lang3.math.Fraction;

/**
 * 求伴随矩阵
 */
public interface AdjointService {
    Fraction[][] calculate(Fraction[][] matrix);

    Double[][] calculate(Double[][] matrix);
}
