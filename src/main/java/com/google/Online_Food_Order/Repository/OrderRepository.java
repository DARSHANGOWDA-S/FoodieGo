package com.google.Online_Food_Order.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.Online_Food_Order.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
