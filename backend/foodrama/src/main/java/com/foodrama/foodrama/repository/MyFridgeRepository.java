package com.foodrama.foodrama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.MyFridge;

public interface MyFridgeRepository extends JpaRepository<MyFridge, Long>{

}
