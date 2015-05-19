package com.yang.software.mm.enums;

public enum FactoryTypeEnum {
    EDIT(1, "未编库"),
    WAIT_PUBLISH(2, "已编库"),
    PUBLISH(3, "待发库"),
    PUBLISHED(4, "已发库");

    int id;
    String description;

    private FactoryTypeEnum(int id, String description) {
        this.id = id;
        this.description = description;
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

    public final static int EDIT_ID = 1;
    public final static int WAIT_PUBLISH_ID = 2;
    public final static int PUBLISH_ID = 3;
    public final static int PUBLISHED_ID = 4;

    public static String getDescription(int id) {
        switch (id) {
            case EDIT_ID:
                return EDIT.getDescription();
            case WAIT_PUBLISH_ID:
                return WAIT_PUBLISH.getDescription();
            case PUBLISH_ID:
                return PUBLISH.getDescription();
            case PUBLISHED_ID:
                return PUBLISHED.getDescription();
            default:
                return "";
        }
    }

}
