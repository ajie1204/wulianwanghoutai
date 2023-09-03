package org.example.nacosspringcloudcommonentity.water.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WaterQualityForJson {
    private Float old_tds;
    private Float new_tds;
}
