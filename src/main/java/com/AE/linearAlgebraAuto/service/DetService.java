package com.AE.linearAlgebraAuto.service;

import org.apache.commons.lang3.math.Fraction;

/**
 * 行列式服务
 */
public interface DetService {
    /**
     * 计算行列式
     * @return 计算结果
     */
    Fraction calculate(Fraction[][] matrix);

    Double calculate(Double[][] matrix);
}
