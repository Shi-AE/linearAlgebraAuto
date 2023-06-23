package com.AE.linearAlgebraAuto.service;

import org.apache.commons.lang3.math.Fraction;

/**
 * 求解逆矩阵
 */
public interface InverseService {
    Fraction[][] calculate(Fraction[][] matrix);

    Double[][] calculate(Double[][] matrix);
}
