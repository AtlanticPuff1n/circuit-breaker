package com.order.demo.controller.api;

import com.order.demo.entity.Order;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public interface OrderController {

    @ApiOperation(value = "Place an order")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "The order has been successfully placed!")
            }
    )
    @PostMapping
    String placeOrder(@RequestBody Order order);

}
