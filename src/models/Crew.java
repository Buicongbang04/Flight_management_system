package models;

import java.io.Serializable;

public class Crew implements Serializable {
    public static final long serialVersionUID = 1L;
    private String crewId;
    private String name;
    private String role;

    public Crew(String crewId, String name, String role) {
        this.crewId = crewId;
        this.name = name;
        this.role = role;
    }

    // Getters and setters

    public String getCrewId() {
        return crewId;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewId='" + crewId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
