package yummy.advanced.config.shiro;

/**
 * @author ：tsl
 * @date ：Created in 2020/2/12 0:53
 * @description：state of customer:0：未激活，1：已注册，2：已注销
 */

public enum CustomerStateEnum {
    NotActive(0, "未激活"), Registered(1, "已注册"), Canceled(2, "已注销");

    Integer state;
    String desc;

    CustomerStateEnum(Integer state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
