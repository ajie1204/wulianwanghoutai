package org.example.nacosspringcloudcommonentity.customer;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分记录(CustomerScore)实体类
 *
 * @author makejava
 * @since 2023-04-04 19:18:43
 */
@Getter
@Setter
@ToString
@NoArgsConstructor

public class CustomerScore implements Serializable {
    private static final long serialVersionUID = 602420747505461376L;

    private Integer id;
    /**
     * 用户唯一id
     */
    private Integer uid;
    /**
     * 积分值
     */
    private Integer score;
    /**
     * 类型，0加，1减
     */
    private Integer type;
    /**
     * 关联的订单号
     */
    private String orderSn;
    /**
     * 记录描述
     */
    private String desc;
    /**
     * 创建时间
     */
    private Date createdAt;



}

