package entities;

import entities.enums.OrderStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private Date moment;
    private OrderStatus status;

    private Client client;
    private List<OrderItem> orderItems = new ArrayList<>();


    public Order(){
    }
    public Order(Date moment, OrderStatus status, Client client){
        this.moment = moment;
        this.status = status;
        this.client = client;
    }


    public Date getMoment() {
        return moment;
    }
    public void setMoment(Date moment) {
        this.moment = moment;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addItem(OrderItem item){
        orderItems.add(item);
    }
    public void removeItem(OrderItem item){
        orderItems.remove(item);
    }
    public double total(){
        double sum = 0;
        for (OrderItem oi: orderItems){
            sum+= oi.subTotal();
        } return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ORDER SUMMARY: \n");
        sb.append("Order moment: ");
        sb.append(sdf.format(moment)+"\n");
        sb.append("Order status: ");
        sb.append(status+"\n");
        sb.append("Client: ");
        sb.append(client.getName()+" ");
        sb.append("("+sdf1.format(client.getBirthDate())+") - ");
        sb.append(client.getEmail()+"\n");
        sb.append("Order items:\n");
        for(OrderItem oi: orderItems){
            sb.append(oi.product.getName()+", ");
            sb.append(String.format("$ %.2f", oi.getPrice()));
            sb.append(", Quantity: ");
            sb.append(oi.getQuantity());
            sb.append(", Subtotal: ");
            sb.append(String.format("$ %.2f", oi.subTotal()));
            sb.append("\n");
        }
        sb.append("Total price: ");
        sb.append(String.format("$ %.2f", total()));

        return sb.toString();
    }
}