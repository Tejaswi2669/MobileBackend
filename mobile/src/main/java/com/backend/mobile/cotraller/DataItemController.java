package com.backend.mobile.cotraller;

import com.backend.mobile.entity.Item;
import com.backend.mobile.service.DataItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin()
public class DataItemController {

    @Autowired
    private DataItemService service;

    @GetMapping
    public List<Item> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return service.addItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        return service.getItemById(id)
                .map(existingItem -> {
                    existingItem.setName(updatedItem.getName());
                    existingItem.setItemDetails(updatedItem.getItemDetails());
                    Item savedItem = service.updateItem(existingItem);
                    return ResponseEntity.ok(savedItem);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (service.getItemById(id).isPresent()) {
            service.deleteItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
