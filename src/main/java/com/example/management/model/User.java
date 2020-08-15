package com.example.management.model;

import lombok.Data;

@Data
public class User {
    private String point;
    private String explanation;
    private String ID;
    public User(String id, String point, String explannation){
        this.setID(id);
        this.setExplanation(explannation);
        this.setPoint(point);
    }

    public User(){}

}
