package org.example.scene.service.feiginService;

import org.example.nacosspringcloudcommonentity.DownData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "custom-protocol-gateway")
public interface CommandService {
    @PostMapping("/gateway/down")
    void downForward(@RequestBody DownData downData);
}
