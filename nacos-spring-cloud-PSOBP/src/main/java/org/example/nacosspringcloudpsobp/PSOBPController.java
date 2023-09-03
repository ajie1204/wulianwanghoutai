package org.example.nacosspringcloudpsobp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pso")
public class PSOBPController {

    @GetMapping("/getHuCurve")
    public double[] getCurve(){
        double[] input = {24,22,22,21,21,21,20,20};
        Humidity humidity = new Humidity();
        double[] output = humidity.netOutput(input);
        return output;
    }

}
