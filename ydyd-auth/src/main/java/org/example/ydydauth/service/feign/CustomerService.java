package org.example.ydydauth.service.feign;


import org.example.nacosspringcloudcommonentity.customer.Customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "ydydMine")
public interface CustomerService {

    @GetMapping("customer/getcustomerbyopneid/{openid}")
    Customer getCustomerByOpenid(@PathVariable String openid);

    @PostMapping("customer/addcustomer")
    Customer insertCustomer(@RequestBody Customer customer);

    @GetMapping("customer/getcustomerbyrequest/{requestCode}")
    Customer getCustomerByRequestCode(@PathVariable String requestCode);

    @GetMapping("customer/getfirstparent/{firstParent}")
    Customer getFirstParent(@PathVariable Integer firstParent);



}
