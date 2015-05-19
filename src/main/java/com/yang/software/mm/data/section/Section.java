package com.yang.software.mm.data.section;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SECTION")
public class Section {
    @Id
    @GeneratedValue
    private int id;

    private String sectionName;

    private int hidden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public String toString() {
        return "Section [id=" + id + ", sectionName=" + sectionName + "]";
    }

    public Section(int id, String sectionName, int hidden) {
        super();
        this.id = id;
        this.sectionName = sectionName;
        this.hidden = hidden;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public Section() {
        super();
    }
}
