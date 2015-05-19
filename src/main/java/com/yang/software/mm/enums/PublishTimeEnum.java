package com.yang.software.mm.enums;

public enum PublishTimeEnum {
    Default(0, "未分期"),
    January_Up(1, "第1期"),
    January_Down(2, "第2期"),
    February_Up(3, "第3期"),
    February_Down(4, "第4期"),
    March_Up(5, "第5期"),
    March_Down(6, "第6期"),
    April_Up(7, "第7期"),
    April_Down(8, "第8期"),
    May_Up(9, "第9期"),
    May_Down(10, "第10期"),
    June_Up(11, "第11期"),
    June_Down(12, "第12期"),
    July_Up(13, "第13期"),
    July_Down(14, "第14期"),
    August_Up(15, "第15期"),
    August_Down(16, "第16期"),
    September_Up(17, "第17期"),
    September_Down(18, "第18期"),
    October_Up(19, "第19期"),
    October_Down(20, "第20期"),
    November_Up(21, "第21期"),
    November_Down(22, "第22期"),
    December_Up(23, "第23期"),
    December_Down(24, "第24期"),;
    int id;
    String description;

    PublishTimeEnum(int id, String decription) {
        this.id = id;
        this.description = decription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getPublicTimeDescription(int id) {
        for (PublishTimeEnum time : PublishTimeEnum.values()) {
            if (id == time.getId()) {
                return time.getDescription();
            }
        }
        return "";
    }


}
