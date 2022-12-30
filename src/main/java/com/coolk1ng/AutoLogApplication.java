package com.coolk1ng;

import com.coolk1ng.aop.OrderService;
import com.coolk1ng.aop.SaveOrder;
import com.coolk1ng.aop.UpdateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoLogApplication implements CommandLineRunner {
    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(AutoLogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SaveOrder saveOrder = new SaveOrder();
        saveOrder.setSaveOrderId(1L);
        orderService.saveOrder(saveOrder);

        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.setUpdateOrderId(2L);
        orderService.updateOrder(updateOrder);
    }
}
