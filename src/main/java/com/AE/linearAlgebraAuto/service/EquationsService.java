package com.AE.linearAlgebraAuto.service;

import org.apache.commons.lang3.math.Fraction;

/**
 * 多原一次方程求解服务
 */
public interface EquationsService {
    Fraction[] calculate(Fraction[][] matrix);

    Double[] calculate(Double[][] matrix);
}
