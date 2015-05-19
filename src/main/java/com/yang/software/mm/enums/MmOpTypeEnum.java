package com.yang.software.mm.enums;

public enum MmOpTypeEnum {
    ADD(1, "增加"),
    DELETE(2, "删除"),
    MODIFY(3, "修改"),
    GET(4, "取稿"),
    DELIVER(5, "传递"),
    TRANSFER(6, "移库"),
    PERIOD(7, "设置期数"),
    ADVICE(8, "建议");
    private int id;

    private String opString;

    private MmOpTypeEnum(int id, String opString) {
        this.id = id;
        this.opString = opString;
    }

    public int getId() {
        return id;
    }

    public String getOpString() {
        return opString;
    }

    public static String getOpTypeDescription(int opTypeId) {
        for (MmOpTypeEnum typeEnum : MmOpTypeEnum.values()) {
            if (typeEnum.getId() == opTypeId) {
                return typeEnum.getOpString();
            }
        }
        return "";
    }

    public static MmOpTypeEnum get(int id) {
        for (MmOpTypeEnum typeEnum : MmOpTypeEnum.values()) {
            if (typeEnum.getId() == id) {
                return typeEnum;
            }
        }
        return null;
    }

    public static MmOpTypeEnum[] getRecyclerOpType() {
        return new MmOpTypeEnum[]{GET, TRANSFER};
    }
}
