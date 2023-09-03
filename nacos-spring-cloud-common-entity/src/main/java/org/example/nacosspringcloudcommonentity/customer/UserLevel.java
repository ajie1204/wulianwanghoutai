package org.example.nacosspringcloudcommonentity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户等级(UserLevel)实体类
 *
 * @author makejava
 * @since 2023-04-04 14:12:23
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserLevel implements Serializable {
    private static final long serialVersionUID = 522791152801308057L;

    private Integer id;
    /**
     * 等级名称
     */
    private String name;
    /**
     * 会员权益标题
     */
    private String title;
    /**
     * 等级对应的最低成长值
     */
    private Integer growthMin;
    /**
     * 等级对应的最高成长值
     */
    private Integer growthMax;
    /**
     * 自购升级金额
     */
    private Double purchaseAmount;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 直接升级购买的商品id
     */
    private String goodsIds;
    /**
     * 升级赠送抵扣金数量
     */
    private Double deductionMoney;
    /**
     * 升级赠送优惠券数据
     */
    private Object couponData;


}

