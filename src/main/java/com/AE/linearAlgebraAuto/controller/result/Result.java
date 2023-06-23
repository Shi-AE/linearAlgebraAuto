package com.AE.linearAlgebraAuto.controller.result;

import lombok.Data;

@Data
public class Result {
    private Object data;
    private String msg;
    private Double time;

    public Result() {
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(Object data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public Result(Object data, String msg, Double time) {
        this.data = data;
        this.msg = msg;
        this.time = time;
    }
}
