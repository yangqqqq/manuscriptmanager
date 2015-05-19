package com.yang.software.mm.enums;

public enum RoleEnum {
    LOW(0, "低级别"),
    HIGH(1, "高级别");
    private int id;
    private String description;

    private RoleEnum(int id, String description) {
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

    public static FactoryTypeEnum[] getFactoryTypeEnum(int roleId) {
        if (roleId == 0) {
            return new FactoryTypeEnum[]{FactoryTypeEnum.EDIT, FactoryTypeEnum.WAIT_PUBLISH, FactoryTypeEnum.PUBLISHED};
        }
        return FactoryTypeEnum.values();
    }

    public static String getRoleDescription(int roleId) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getId() == roleId) {
                return role.getDescription();
            }
        }
        return "";
    }

    public static MmOpTypeEnum[] getMmOpTypeEnumOfMy(int roleId) {
        if (roleId == 0) {
            return new MmOpTypeEnum[]{MmOpTypeEnum.DELETE, MmOpTypeEnum.DELIVER, MmOpTypeEnum.TRANSFER, MmOpTypeEnum.PERIOD};
        }
        return new MmOpTypeEnum[]{MmOpTypeEnum.DELETE, MmOpTypeEnum.DELIVER, MmOpTypeEnum.TRANSFER, MmOpTypeEnum.PERIOD};
    }

    public static MmOpTypeEnum[] getMmOpTypeEnumOfAll(int roleId) {
        if (roleId == 0) {
            return new MmOpTypeEnum[]{MmOpTypeEnum.DELETE, MmOpTypeEnum.GET, MmOpTypeEnum.DELIVER, MmOpTypeEnum.TRANSFER, MmOpTypeEnum.PERIOD};
        }
        return new MmOpTypeEnum[]{MmOpTypeEnum.DELETE, MmOpTypeEnum.GET, MmOpTypeEnum.DELIVER, MmOpTypeEnum.TRANSFER, MmOpTypeEnum.PERIOD};
    }

}
