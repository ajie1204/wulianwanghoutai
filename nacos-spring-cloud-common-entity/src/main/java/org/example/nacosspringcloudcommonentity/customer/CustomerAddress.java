package org.example.nacosspringcloudcommonentity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户地址(CustomerAddress)实体类
 *
 * @author makejava
 * @since 2023-04-04 19:17:21
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerAddress implements Serializable {
    private static final long serialVersionUID = 893147672025578254L;

    private Integer id;
    /**
     * 用户唯一id
     */
    private Integer unid;
    /**
     * 名称
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 是否默认地址
     */
    private Integer isDef;

    private Integer provinceId;
    /**
     * 省
     */
    private String province;

    private Integer cityId;
    /**
     * 市
     */
    private String city;

    private Integer areaId;
    /**
     * 区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 经度
     */
    private Double lon;
    /**
     * 纬度
     */
    private Double lat;

    private Date createdAt;

    private Date updatedAt;



}

