package yummy.demo.exception;

public enum BusinessExceptionEnum {
    OUT_OF_STOCK(001,"库存不足"),
    OUT_OF_BALANCE(001,"余额不足"),
    DATABASE_ERROR(005,"数据库错误")
    ;
    private int code;
    private String msg;

    BusinessExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
