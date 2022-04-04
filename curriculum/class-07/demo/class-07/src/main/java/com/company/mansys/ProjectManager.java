package com.company.mansys;

import com.company.mansys.behaviors.Manage;

public class ProjectManager extends Employee implements Manage {
    private int teamsManaged;

    public ProjectManager(String name, String position, Long lastToBeHired) {
        super(name, position, lastToBeHired);
    }

    @Override
    public void work() {
        System.out.println("I shout at people all day");
    }

    public int getTeamsManaged() {
        return teamsManaged;
    }

    public void setTeamsManaged(int teamsManaged) {
        this.teamsManaged = teamsManaged;
    }

    @Override
    public void meeting() {

    }

    @Override
    public void organise() {

    }
}
