package com.example.simpumind.fragment2;

/**
 * Created by simpumind on 4/19/15.
 */
public class Match {

    public int id;
    public String fixId;
    public String status;
    public String formatted_date;
    public String time;
    public Team localTeam;
    public Team vistorTeam;
   // public Event[] events;

    public Match(){}

    public Match( int id,String fixId, String status,String formatted_date,String time,Team localTeam,Team vistorTeam) {
        this.id = id;
        this.fixId = fixId;
        this.status = status;
        this.formatted_date = formatted_date;
        this.time = time;
        this.localTeam = localTeam;
        this.vistorTeam = vistorTeam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFixId() {
        return fixId;
    }

    public void setFixId(String fixId) {
        this.fixId = fixId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormatted_date() {
        return formatted_date;
    }

    public void setFormatted_date(String formatted_date) {
        this.formatted_date = formatted_date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
    }

    public Team getVistorTeam() {
        return vistorTeam;
    }

    public void setVistorTeam(Team vistorTeam) {
        this.vistorTeam = vistorTeam;
    }
}
