package com.AE.linearAlgebraAuto.controller;

import com.AE.linearAlgebraAuto.controller.result.Result;
import com.AE.linearAlgebraAuto.service.AdjointService;
import com.AE.linearAlgebraAuto.service.DetService;
import com.AE.linearAlgebraAuto.service.EquationsService;
import com.AE.linearAlgebraAuto.service.InverseService;
import com.AE.linearAlgebraAuto.util.DoubleFractionUtil;
import com.AE.linearAlgebraAuto.util.FractionToJsonUtil;
import org.apache.commons.lang3.math.Fraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CalculateController {

    private double startTime;
    private double endTime;
    @Autowired
    private DetService detService;
    @Autowired
    private EquationsService equationsService;
    @Autowired
    private AdjointService adjointService;
    @Autowired
    private InverseService inverseService;

    @PostMapping("/det/{method}")
    public Result det(@RequestBody Double[][] data,@PathVariable String method) {
        switch (method) {
            case "fraction" -> {
                Fraction[][] matrix = DoubleFractionUtil.D2F2(data);
                startTime = System.currentTimeMillis();
                Fraction res = detService.calculate(matrix);
                endTime = System.currentTimeMillis();
                System.out.println(res);
                System.out.println((endTime - startTime) / 1000);
                return new Result(FractionToJsonUtil.arrayToJson(res), "计算完成", (endTime - startTime) / 1000);
            }
            case "decimal" -> {
                startTime = System.currentTimeMillis();
                Double res = detService.calculate(data);
                endTime = System.currentTimeMillis();
                System.out.println(res);
                System.out.println((endTime - startTime) / 1000);
                return new Result(res, "计算完成", (endTime - startTime) / 1000);
            }
            default -> {
                return new Result("方法错误");
            }
        }
    }

    @PostMapping("/equations/{method}")
    public Result equations(@RequestBody Double[][] data,@PathVariable String method) {
        switch (method) {
            case "fraction" -> {
                Fraction[][] matrix = DoubleFractionUtil.D2F2(data);
                startTime = System.currentTimeMillis();
                Fraction[] Xi = equationsService.calculate(matrix);
                endTime = System.currentTimeMillis();
                System.out.println(Arrays.toString(Xi));
                System.out.println((endTime - startTime) / 1000);
                return new Result(FractionToJsonUtil.arrayToJson(Xi), "计算完成", (endTime - startTime) / 1000);
            }
            case "decimal" -> {
                startTime = System.currentTimeMillis();
                Double[] res = equationsService.calculate(data);
                endTime = System.currentTimeMillis();
                System.out.println(Arrays.toString(res));
                System.out.println((endTime - startTime) / 1000);
                return new Result(res, "计算完成", (endTime - startTime) / 1000);
            }
            default -> {
                return new Result("方法错误");
            }
        }
    }

    @PostMapping("/inverse/{method}")
    public Result inverse(@RequestBody Double[][] data,@PathVariable String method) {
        switch (method) {
            case "fraction" -> {
                Fraction[][] matrix = DoubleFractionUtil.D2F2(data);
                startTime = System.currentTimeMillis();
                Fraction[][] inverseMatrix = inverseService.calculate(matrix);
                endTime = System.currentTimeMillis();
                String json = FractionToJsonUtil.arrayToJson(inverseMatrix);
                System.out.println(json);
                System.out.println((endTime - startTime) / 1000);
                return new Result(json, "计算完成", (endTime - startTime) / 1000);
            }
            case "decimal" -> {
                startTime = System.currentTimeMillis();
                Double[][] res = inverseService.calculate(data);
                endTime = System.currentTimeMillis();
                System.out.println(Arrays.deepToString(res));
                System.out.println((endTime - startTime) / 1000);
                return new Result(res, "计算完成", (endTime - startTime) / 1000);
            }
            default -> {
                return new Result("方法错误");
            }
        }
    }

    @PostMapping("/adjoint/{method}")
    public Result adjoint(@RequestBody Double[][] data,@PathVariable String method) {
        switch (method) {
            case "fraction" -> {
                Fraction[][] matrix = DoubleFractionUtil.D2F2(data);
                startTime = System.currentTimeMillis();
                Fraction[][] adjointMatrix = adjointService.calculate(matrix);
                endTime = System.currentTimeMillis();
                String json = FractionToJsonUtil.arrayToJson(adjointMatrix);
                System.out.println(json);
                System.out.println((endTime - startTime) / 1000);
                return new Result(json, "计算完成", (endTime - startTime) / 1000);
            }
            case "decimal" -> {
                startTime = System.currentTimeMillis();
                Double[][] res = adjointService.calculate(data);
                endTime = System.currentTimeMillis();
                System.out.println(Arrays.deepToString(res));
                System.out.println((endTime - startTime) / 1000);
                return new Result(res, "计算完成", (endTime - startTime) / 1000);
            }
            default -> {
                return new Result("方法错误");
            }
        }
    }
}
