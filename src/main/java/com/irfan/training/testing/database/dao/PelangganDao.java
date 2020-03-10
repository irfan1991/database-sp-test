package com.irfan.training.testing.database.dao;

import com.irfan.training.testing.database.entity.Pelanggan;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PelangganDao extends PagingAndSortingRepository<Pelanggan, String>{
    public Pelanggan findByEmail(String email);
}
