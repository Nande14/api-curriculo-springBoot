package com.example.springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;
@Entity
@Table(name = "TB_CURRICULUMS")
public class CurriculumModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCurriculum;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String skills;

    public UUID getIdCurriculum() {
        return idCurriculum;
    }

    public void setIdCurriculum(UUID idCurriculum) {
        this.idCurriculum = idCurriculum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
