package com.backend.mobile.service;

import com.backend.mobile.entity.Item;
import com.backend.mobile.dao.DataItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataItemService {

    @Autowired
    private DataItemRepository repository;

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return repository.findById(id);
    }

    public Item addItem(Item item) {
        return repository.save(item);
    }

    public Item updateItem(Item item) {
        return repository.save(item);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}
