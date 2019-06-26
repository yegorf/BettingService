package com.yegorf.bookmaker.dto;

import lombok.Data;

@Data
public class ResultsSumCoef {
    private int id;
    private String name;
    private float sum;
    private float coef;
    private float percent;
}
