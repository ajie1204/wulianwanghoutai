package org.example.nacosspringcloudinteract.service;

import com.alibaba.fastjson.JSONObject;
import org.example.nacosspringcloudcommonentity.UplinkCommand;

public interface UpDataService {
    public void pushData(JSONObject jsonObject);

    void pushAnswer(JSONObject jsonObject);

}
