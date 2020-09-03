package com.example.management.model;

import lombok.Data;

@Data
public class Info {
    private String point;
    private String explanation;
    private String ID;
    public Info(String id, String point, String explannation){
        this.setID(id);
        this.setExplanation(explannation);
        this.setPoint(point);
    }

    public Info(){}

}
