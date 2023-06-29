package com.api.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.customer.entities.Stock;
import com.api.customer.respositories.StockRepository;

@Service
public class StockService {

    @Autowired
    private StockRepository sRepository;

    public Stock createStock(Stock stock) {
        return sRepository.save(stock);

    }

    public List<Stock> listAllStock() {
        return sRepository.findAll();

    }

    public Stock findById(int id) {
        Optional<Stock> optionalStock = sRepository.findById(id);
        return optionalStock.orElse(null);
    }

    public Stock changeStock(int id, Stock stock) {
        Optional<Stock> optionalStock = sRepository.findById(id);
        if (optionalStock.isPresent()) {
            stock.setId(id);
            return sRepository.save(stock);
        }
        return null;
    }

    public void deleteStock(int id) {
        sRepository.deleteById(id);
    }
}
