package com.sttx.bookmanager.util.exception;

import org.slf4j.Logger;

import com.sttx.ddp.logger.DdpLoggerFactory;

public class UserException extends RuntimeException {
    private static Logger log = DdpLoggerFactory.getLogger(UserException.class);
    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description: TODO
     */
    private static final long serialVersionUID = 1L;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable e) {
        super(message, e);
    }

    public UserException(String errorCode, String message) {
        super(message);
        log.error(">>>>>>>>>>>>>>>>>>>>>errorCode:{}", errorCode);
    }

}
