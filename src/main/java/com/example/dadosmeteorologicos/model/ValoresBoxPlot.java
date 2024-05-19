package com.example.dadosmeteorologicos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValoresBoxPlot {

    private double min;
    private double q1;
    private double mediana;
    private double q3;
    private double max;
    
}
