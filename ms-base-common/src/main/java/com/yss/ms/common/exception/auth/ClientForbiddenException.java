package com.yss.ms.common.exception.auth;


import com.yss.ms.common.constant.CommonConstants;
import com.yss.ms.common.exception.BaseException;

/**
 * Created by ace on 2017/9/12.
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
