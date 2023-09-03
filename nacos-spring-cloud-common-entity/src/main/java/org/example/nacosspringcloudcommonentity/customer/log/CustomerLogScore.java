package org.example.nacosspringcloudcommonentity.customer.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户积分记录(CusotomerLogScore)实体类
 *
 * @author makejava
 * @since 2023-05-16 14:56:44
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerLogScore implements Serializable {
    private static final long serialVersionUID = 985796352667384338L;

    private Integer id;
    /**
     * 用户唯一id
     */
    private Integer unid;
    /**
     * 变动金额正数为入账，负数为出账
     */
    private Integer integral;
    /**
     * 发生时间
     */
    private Date createdAt;
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 支付单号
     */
    private String paySn;
    /**
     * 购物商品id
     */
    private Integer goodsId;
    /**
     * 流水备注
     */
    private String desc;
    /**
     * 0锁定中，1正常，2无效（退款或取消，除新增出账记录外此处标记无效，方便后期数据处理）
     */
    private Integer status;
    /**
     * 后台设置人员id
     */
    private Integer adminId;




}

