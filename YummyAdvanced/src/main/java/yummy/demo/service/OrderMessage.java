package yummy.demo.service;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author ：tsl
 * @date ：Created in 2019/10/3 0:05
 * @description：order message for delayedQueue
 */

public class OrderMessage implements Delayed {

    private final int orderId;
    private final long delayTime;//延迟时间
    private final long expireTime;//到期时间

    public OrderMessage(int orderId, long delayTime) {
        this.orderId = orderId;
        this.delayTime = delayTime;
        this.expireTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long timeDiff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        if (timeDiff == 0) return 0;
        return timeDiff > 0 ? 1 : -1;
    }

    public int getOrderId() {
        return orderId;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public long getExpireTime() {
        return expireTime;
    }
}
