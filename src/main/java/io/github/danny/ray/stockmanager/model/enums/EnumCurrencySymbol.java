package io.github.danny.ray.stockmanager.model.enums;

public enum EnumCurrencySymbol {

    TWD("新台幣"),
    USD("美金"),
    JPY("日圓");

    private String desc;

    EnumCurrencySymbol(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
