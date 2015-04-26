package com.example.simpumind.fragment2;

import java.util.List;

/**
 * Created by simpumind on 4/19/15.
 */
public class Category {

    public String name;
    public int id;
    public String fileGroup;
    public List<Match> matches;
    public Category(){
        
    }
    public Category(int id,String name, String fileGroup, List<Match> matches){
        this.name=name;
        this.id=id;
        this.fileGroup=fileGroup;
        this.matches=matches;
    }
    public Category(int id,String name, String fileGroup){
        this.name=name;
        this.id=id;
        this.fileGroup=fileGroup;
    }

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

    public String getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(String fileGroup) {
        this.fileGroup = fileGroup;
    }

    public List<Match> getMatches() {
        return matches;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
