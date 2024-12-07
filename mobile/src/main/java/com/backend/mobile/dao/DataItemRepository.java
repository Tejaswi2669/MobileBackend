package com.backend.mobile.dao;

import com.backend.mobile.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataItemRepository extends JpaRepository<Item, Long> {
}
