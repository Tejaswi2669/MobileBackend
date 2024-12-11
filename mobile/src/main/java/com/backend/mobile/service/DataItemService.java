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

    public Item updateItem(Long id, Item updatedItem) {
        Optional<Item> existingItemOpt = repository.findById(id);
        if (existingItemOpt.isPresent()) {
            Item existingItem = existingItemOpt.get();
            // Update only fields that are provided in the updated item
            existingItem.setName(updatedItem.getName());
            existingItem.setItemDetails(updatedItem.getItemDetails());
            existingItem.setAmountPaid(updatedItem.getAmountPaid());
            existingItem.setActualAmount(updatedItem.getActualAmount());
            existingItem.setPhoneNumber(updatedItem.getPhoneNumber());
            existingItem.setUserName(updatedItem.getUserName());
            return repository.save(existingItem);
        } else {
            throw new IllegalArgumentException("Item not found with ID: " + id);
        }
    }

    public void deleteItem(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Item not found with ID: " + id);
        }
    }
}