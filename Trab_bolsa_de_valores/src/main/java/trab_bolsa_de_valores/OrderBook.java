package trab_bolsa_de_valores;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class OrderBook {
    private List<Order> buyOrders; // Ordens de compra
    private List<Order> sellOrders; // Ordens de venda

    public OrderBook() {
        this.buyOrders = new ArrayList<>();
        this.sellOrders = new ArrayList<>();
    }

    public void addBuyOrder(Order order) {
        buyOrders.add(order);
        matchOrders();
    }

    public void addSellOrder(Order order) {
        sellOrders.add(order);
        matchOrders();
    }

    private void matchOrders() {
        Iterator<Order> buyIterator = buyOrders.iterator();
        while (buyIterator.hasNext()) {
            Order buyOrder = buyIterator.next();
            Iterator<Order> sellIterator = sellOrders.iterator();
            while (sellIterator.hasNext()) {
                Order sellOrder = sellIterator.next();
                if (buyOrder.getSymbol().equals(sellOrder.getSymbol()) && sellOrder.getPrice() <= buyOrder.getPrice()) {
                    int quantityToMatch = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                    buyOrder.setQuantity(buyOrder.getQuantity() - quantityToMatch);
                    sellOrder.setQuantity(sellOrder.getQuantity() - quantityToMatch);
                    System.out.println("Transação: " + quantityToMatch + " de " + buyOrder.getSymbol() + " a R$" + buyOrder.getPrice());
                    if (buyOrder.getQuantity() == 0) {
                        buyIterator.remove();
                    }
                    if (sellOrder.getQuantity() == 0) {
                        sellIterator.remove();
                    }
                }
            }
        }
    }

}

