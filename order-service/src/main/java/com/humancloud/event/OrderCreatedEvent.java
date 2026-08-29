package com.humancloud.event;

public class OrderCreatedEvent {

    private Long orderId;
    private String customerName;
    private String product;
    private Double amount;

    public OrderCreatedEvent() 
    {
    }

    public OrderCreatedEvent(Long orderId, String customerName,
                             String product, Double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.product = product;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}