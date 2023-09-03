package org.example.ydyd.controller;


import org.example.nacosspringcloudcommonentity.customer.CustomerAddress;

import org.example.nacosspringcloudcommonentity.vo.Response;
import org.example.ydyd.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 客户地址控制器
 *
 * @author 31477
 * @date 2023/04/06
 */
@RestController
@RequestMapping("customeraddress")
public class CustomerAddressController {
    /**
     * 客户地址服务
     */
    @Autowired
    CustomerAddressService customerAddressService;

    /**
     * 得到客户地址
     *
     * @param unid unid
     * @return {@link Response}<{@link List}<{@link CustomerAddress}>>
     */
    @GetMapping("/get/{unid}")
    public Response<List<CustomerAddress>> getCustomerAddress(@PathVariable Integer unid){

        return Response.createSuccessResponse(customerAddressService.queryCustomerAddressByUnid(unid));
    }

    /**
     * 插入客户地址
     *
     * @param customerAddress 客户地址
     * @return {@link Response}<{@link CustomerAddress}>
     */
    @PostMapping("/insert")
    public Response<CustomerAddress> insertCustomerAddress(@RequestBody CustomerAddress customerAddress){
        Date d=new Date();
        customerAddress.setCreatedAt(d);

        List<CustomerAddress> customerAddresses = customerAddressService.queryCustomerAddressByUnid(customerAddress.getUnid());

        if(customerAddressService.insertCustomerAddress(customerAddress)>0){
            if(customerAddress.getIsDef()==1){
               for(CustomerAddress customerAddress1:customerAddresses){
                   customerAddress1.setIsDef(0);
                   customerAddress1.setUpdatedAt(d);
                   customerAddressService.updateDefById(customerAddress1);
               }
            }
            return Response.createSuccessResponse("添加成功");
        }else{
            return Response.createErrorResponse("添加失败");
        }
    }

    /**
     * 更新客户地址
     *
     * @param customerAddress 客户地址
     * @return {@link Response}<{@link CustomerAddress}>
     */
    @PostMapping("/update")
    public Response<CustomerAddress> updateCustomerAddress(@RequestBody CustomerAddress customerAddress){
        Date d=new Date();
        customerAddress.setUpdatedAt(d);

        List<CustomerAddress> customerAddresses = customerAddressService.queryCustomerAddressByUnid(customerAddress.getUnid());

        if(customerAddress.getIsDef()==1) {
            for (CustomerAddress customerAddress1 : customerAddresses) {
                System.out.println(customerAddress1.getId());
                customerAddress1.setIsDef(0);
                customerAddress1.setUpdatedAt(d);
                customerAddressService.updateDefById(customerAddress1);
            }
        }
        if (customerAddressService.updateCustomerAddressById(customerAddress) > 0) {
            return Response.createSuccessResponse("修改成功");
        } else {
            return Response.createErrorResponse("修改失败");
        }


    }


    /**
     * 删除客户地址
     *
     * @param customerAddress 客户地址
     * @return {@link Response}<{@link CustomerAddress}>
     */
    @PostMapping("/delete")
    public Response<CustomerAddress> deleteCustomerAddress(@RequestBody CustomerAddress customerAddress){
        if(customerAddressService.deleteCustomerAddressById(customerAddress)>0){
            return Response.createSuccessResponse("删除成功");
        }else{
            return Response.createErrorResponse("删除失败");
        }
    }






}
