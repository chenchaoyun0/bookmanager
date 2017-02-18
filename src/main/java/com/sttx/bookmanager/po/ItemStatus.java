package com.sttx.bookmanager.po;

public enum ItemStatus {
    LEND_REQUEST(0), LEND_DENIAL(-1), LEND_ACCEPT(1), RETURN_REQUEST(2), RETURN_DENIAL(-3), RETURN_ACCEPT(3);

    private int status;

    public int getStatus() {
        return status;
    }

    private ItemStatus(int status) {
        this.status = status;
    }

}
