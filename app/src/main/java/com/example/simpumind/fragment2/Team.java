package com.example.simpumind.fragment2;

/**
 * Created by simpumind on 4/19/15.
 */
public class Team {

    public String name;
    public String goal;
    public int id;

    public Team(int id,String name, String goal) {
        this.name = name;
        this.goal = goal;
        this.id = id;
    }

    public Team(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

}
