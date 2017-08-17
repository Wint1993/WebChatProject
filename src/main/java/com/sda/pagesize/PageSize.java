package com.sda.pagesize;

public enum PageSize {

    FIVE(5),TEN(10),TWENTY(20);

    public int pSize;

    PageSize(int pSize){
        this.pSize = pSize;
    }

    public int getpSize() {
        return pSize;
    }

    public void setpSize(int pSize) {
        this.pSize = pSize;
    }
}
