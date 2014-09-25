package com.perfect.entity.bidding;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

/**
 * Created by yousheng on 2014/8/14.
 *
 * @author yousheng
 */
public class StrategyEntity {

    // 1 = economics
    // 2 = fast
    @Field("bdtype")
    private int strategy;

    //竞价规则 PC or 移动
    @Field("type")
    private int device;

    @Field("max")
    private BigDecimal maxPrice;

    @Field("min")
    private BigDecimal minPrice;

    // 竞价策略
    @Field("m")
    private int mode;

    // 1 = slow 60
    // 2 = medium 30
    // 3 = fast 15
    @Field("intval")
    private int interval;

    // 1 = left 1
    // 2 = left 2-3
    // 3 = right 1-3
    // 4 = right + postion
    @Field("pstra")
    private int expPosition;

    // 1 = keep current postion
    // 2 = rollback
    @Field("failed")
    private int failedStrategy;

    @Field("t")
    private Integer[] times;

    @Field("pos")
    private int position;

    @Field("rt")
    private Integer[] regionTarget;

    @Field("op")
    private BigDecimal outPrice;

    @Field("bt")
    private int runByTimes;

    @Field("a")
    private int auto;

    public int getStrategy() {
        return strategy;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getExpPosition() {
        return expPosition;
    }

    public void setExpPosition(int expPosition) {
        this.expPosition = expPosition;
    }

    public int getFailedStrategy() {
        return failedStrategy;
    }

    public void setFailedStrategy(int failedStrategy) {
        this.failedStrategy = failedStrategy;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer[] getTimes() {
        return times;
    }

    public void setTimes(Integer[] times) {
        this.times = times;
    }

    public Integer[] getRegionTarget() {
        return regionTarget;
    }

    public void setRegionTarget(Integer[] regionTarget) {
        this.regionTarget = regionTarget;
    }


    public BigDecimal getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }

    public int getRunByTimes() {
        return runByTimes;
    }

    public void setRunByTimes(int runByTimes) {
        this.runByTimes = runByTimes;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }
}
