package org.example.nacosspringcloudcommonentity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Place)实体类
 *
 * @author makejava
 * @since 2022-04-07 19:00:26
 */
@Data
public class Place implements Serializable {
    private static final long serialVersionUID = 495887861299704377L;
    /**
    * 场所
    */    

    private Integer placeId;
    
    /**
    * 场所名
    */    

    private String placeName;
    
    /**
    * 场所所属用户
    */    

    private Integer userId;
    
}

