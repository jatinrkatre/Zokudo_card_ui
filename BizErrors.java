package com.ui.product.zokudo.enums;

public enum BizErrors {

    NULL_ERROR("required parameter is empty!"),
    BAD_REQUEST_ERROR("bad request!"),
    LENGTH_EXCEED("parameter length exceed!"),
    TYPE_MISMATCH("parameter type mismatch!"),
    LENGTH_MISMATCH("parameter length mismatch!"),
    DATA_NOT_FOUND("no data found!"),
    DUPLICATE_DATA("duplicate data insertion"),
    APPLICATION_ERROR("process failed"),
    DATA_ALREADY_EXIST("data already exist");

    private final String value;

    private BizErrors(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public  String toString(){
        return  value;
    }

}
