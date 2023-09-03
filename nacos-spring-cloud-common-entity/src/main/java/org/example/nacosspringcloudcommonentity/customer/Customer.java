package org.example.nacosspringcloudcommonentity.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户
 * 用户表(Customer)实体类
 *
 * @author makejava
 * @date 2023/04/03
 * @since 2023-04-03 17:48:28
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = -84224778429743315L;

    private Integer id;
    /**
     * 唯一id
     */
    private Integer unid;
    /**
     * 云店小程序openid
     */
    private String openid;
    /**
     * 溢爱人小程序openid
     */
    private String yarOpenid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 密码盐值
     */
    private String salt;
    /**
     * 姓名
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 微信appid
     */
    private String wxAppid;
    /**
     * 溢爱人appid
     */
    private String yarAppid;

    private Date createdAt;
    /**
     * 省 id
     */
    private Integer provinceId;
    /**
     * 市 id
     */
    private Integer cityId;
    /**
     * 区 id
     */
    private Integer districtId;
    /**
     * 详细地址
     */
    private String addr;
    /**
     * 用户等级id
     */
    private Integer levelId;
    /**
     * 1正常，停止
     */
    private Integer status;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否为永久绑定，1是，0否
     */
    private Integer isPerpetual;
    /**
     * 直接推荐人
     */
    private Integer firstParent;
    /**
     * 间接推荐人
     */
    private Integer secondParent;
    /**
     * 所属服务商商户id
     */
    private Integer shopId;
    /**
     * 存unid商户管理账户业务员id,(与推荐用户二者只存其一)
     */
    private Integer shopUserId;
    /**
     * 绑定时间
     */
    private Date bindDate;
    /**
     * 邀请码
     */
    private String requestCode;
    /**
     * 个人最低提现额度
     */
    private Double withdrawal;
    /**
     * 备注信息，一般用于销售后台备注
     */
    private String description;
    /**
     * vip最后认证时间
     */
    private Date vipupdateAt;
    /**
     * 会员卡号
     */
    private String vipcard;
    /**
     * 身份证号
     */
    private String cardId;
    /**
     * 身份证姓名
     */
    private String cardName;
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 赠送的无目标设备时长天数临时存储（激活或者续时长时自动使用）
     */
    private Integer giveDays;


}

