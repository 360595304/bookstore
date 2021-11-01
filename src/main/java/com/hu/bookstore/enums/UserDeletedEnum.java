package com.hu.bookstore.enums;

public enum UserDeletedEnum {

    DELETED(0),
    NOT_DELETE(1);

    private Integer deletedCode;

    UserDeletedEnum(Integer deletedCode){
        this.deletedCode = deletedCode;
    }

    public Integer getDeletedCode() {
        return deletedCode;
    }

    public void setDeletedCode(Integer deletedCode) {
        this.deletedCode = deletedCode;
    }
}
