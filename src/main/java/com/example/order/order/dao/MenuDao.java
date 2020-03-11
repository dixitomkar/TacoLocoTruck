package com.example.order.order.dao;

import com.example.order.order.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao extends CrudRepository <Item, String>  {

    @Override
    List<Item> findAll();
}

