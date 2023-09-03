package org.example.nacosspringcloudprotocoldownlink.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 关于溢爱分类(AboutCategory)实体类
 *
 * @author makejava
 * @since 2022-02-22 16:01:50
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class AboutCategory implements Serializable {
    private static final long serialVersionUID = -53416229164052799L;
    /**
     * 关于溢爱子菜单id
     */
    private Integer id;
    /**
     * 子菜单名称
     */
    private String name;
    
    private String listhttp;
    
    private String contenthttp;




}

