package org.example.nacosspringcloudcommonentity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户详情(CustomerDetail)实体类
 *
 * @author makejava
 * @since 2023-04-04 19:18:10
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerDetail implements Serializable {
    private static final long serialVersionUID = 761999059903846332L;

    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 账户余额
     */
    private Double money;
    /**
     * 冻结金额
     */
    private Double frozenMoney;
    /**
     * 累计出账
     */
    private Double totalRecharge;
    /**
     * 累计入账
     */
    private Double totalMoney;
    /**
     * 积分值
     */
    private Integer score;
    /**
     * 购物金
     */
    private Double shopingMoney;
    /**
     * 消费总金额
     */
    private Double useTotal;
    /**
     * 抵扣金
     */
    private Double deductionMoney;
    /**
     * 成长值
     */
    private Double growthValue;




}

