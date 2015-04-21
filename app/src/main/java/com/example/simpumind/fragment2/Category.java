package com.example.simpumind.fragment2;

/**
 * Created by simpumind on 4/19/15.
 */
public class Category {

    public String name;
    public int id;
    public String fileGroup;
    public Match matches;
    /*public Category(String id,String name, String fileGroup, Match matches){
        this.name=name;
        this.id=id;
        this.fileGroup=fileGroup;
    }*/

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

    public Match getMatches() {
        return matches;
    }

    public void setMatches(Match matches) {
        this.matches = matches;
    }
}
