package org.example.nacosspringcloudcommonentity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Scene)实体类
 *
 * @author makejava
 * @since 2022-04-11 18:50:59
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Scene implements Serializable {
    private static final long serialVersionUID = 429429603385921363L;
    /**
     * 场景id
     */
    private Integer scenesId;
    /**
     * 场景名
     */
    private String scenesName;
    /**
     * 场景所属用户
     */

    private Integer userId;

}

