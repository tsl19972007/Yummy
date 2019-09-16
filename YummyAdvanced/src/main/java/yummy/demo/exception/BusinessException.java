package yummy.demo.exception;

public class BusinessException extends RuntimeException{
    private int code;

    private String msg;

    public BusinessException(BusinessExceptionEnum e) {
        super(e.getMsg());
        this.code=e.getCode();
        this.msg=e.getMsg();
    }
}
