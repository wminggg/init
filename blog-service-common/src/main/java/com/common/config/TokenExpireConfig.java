package com.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 令牌到期配置
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Component
@ConfigurationProperties(
    prefix = "token"
)
public class TokenExpireConfig {
    private int expireSecondInterval;
    private int expireSecondIntervalPc;

    public TokenExpireConfig() {
    }

    public int getExpireSecondInterval() {
        return this.expireSecondInterval;
    }

    public int getExpireSecondIntervalPc() {
        return this.expireSecondIntervalPc;
    }

    public void setExpireSecondInterval(final int expireSecondInterval) {
        this.expireSecondInterval = expireSecondInterval;
    }

    public void setExpireSecondIntervalPc(final int expireSecondIntervalPc) {
        this.expireSecondIntervalPc = expireSecondIntervalPc;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TokenExpireConfig)) {
            return false;
        } else {
            TokenExpireConfig other = (TokenExpireConfig)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getExpireSecondInterval() != other.getExpireSecondInterval()) {
                return false;
            } else {
                return this.getExpireSecondIntervalPc() == other.getExpireSecondIntervalPc();
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TokenExpireConfig;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getExpireSecondInterval();
        result = result * 59 + this.getExpireSecondIntervalPc();
        return result;
    }

    public String toString() {
        int var10000 = this.getExpireSecondInterval();
        return "TokenExpireConfig(expireSecondInterval=" + var10000 + ", expireSecondIntervalPc=" + this.getExpireSecondIntervalPc() + ")";
    }
}