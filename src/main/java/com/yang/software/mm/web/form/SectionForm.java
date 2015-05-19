package com.yang.software.mm.web.form;

import com.yang.software.mm.data.section.Section;

public class SectionForm {
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

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public Section getSection() {
        return new Section(id, sectionName, hidden);
    }
}
