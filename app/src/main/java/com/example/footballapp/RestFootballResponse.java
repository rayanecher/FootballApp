package com.example.footballapp;

import java.util.List;

public class RestFootballResponse {

    private String name;
    private String shortName;
    private String website;
    private Integer founded;
    private String clubColors;
    private List<psgTeam> squad;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getWebsite() {
        return website;
    }

    public Integer getFounded() {
        return founded;
    }

    public String getClubColors() {
        return clubColors;
    }

    public List<psgTeam> getSquad() {
        return squad;
    }
}
