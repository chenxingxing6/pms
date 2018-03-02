package com.peace.pms.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
    @Value("#{APP_PROP['jdbc.driver']}")
    private String productName;

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        System.out.println(properties.productName);
    }
}
