package org.example.nacosspringcloudcommonentity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户活动统计表(CustomerActivity)实体类
 *
 * @author makejava
 * @since 2023-04-04 19:17:06
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerActivity implements Serializable {
    private static final long serialVersionUID = -33797753199040018L;

    private Integer id;
    /**
     * 用户唯一id
     */
    private Integer unid;
    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 活动达成次数
     */
    private Integer num;
    /**
     * 第一次参与活动时间
     */
    private Date createdAt;
    /**
     * 最后一次参与活动时间
     */
    private Date updatedAt;



}

