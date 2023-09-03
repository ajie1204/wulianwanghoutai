package org.example.nacosspringcloudcommonentity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户等级权益(CustomerLevelIncome)实体类
 *
 * @author makejava
 * @since 2023-04-04 19:18:36
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerLevelIncome implements Serializable {
    private static final long serialVersionUID = -10528098249060688L;

    private Integer id;
    /**
     * 商品兑换券  0没权限 1已获得 2已使用
     */
    private Integer merchant;
    /**
     * 优惠券  0没权限 1已获得 2已使用
     */
    private Integer yhq;
    /**
     * 积分商城  0没权限 1已获得 2已使用
     */
    private Integer jfmerchant;
    /**
     * 财产保险  0没权限 1已获得 2已使用
     */
    private Integer ccbx;
    /**
     * 旅游权益  0没权限 1已获得 2已使用
     */
    private Integer lyqx;
    /**
     * 购物金  0没权限 1已获得 2已使用
     */
    private Integer gwj;
    /**
     * 唯一id
     */
    private Integer unid;
    /**
     * 抵扣金
     */
    private Integer dkj;


}

