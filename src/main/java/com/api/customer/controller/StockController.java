package com.api.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.customer.entities.Stock;
import com.api.customer.enums.Type;
import com.api.customer.services.StockService;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    public StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.listAllStock();
        if (stocks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(stocks);
    }

    @PostMapping
    public ResponseEntity<Stock> postStock(@Validated @RequestBody Stock stock) {
        if (Type.valueOf(stock.getType().getType()) == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Stock newStock = stockService.createStock(stock);

        if (newStock != null) {
            return new ResponseEntity<>(newStock, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> GetById(@PathVariable(value = "id") int id) {
        Stock stock = stockService.findById(id);
        if (stock != null) {
            return ResponseEntity.ok().body(stock);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> putStock(@PathVariable(value = "id") int id, @Validated @RequestBody Stock stock) {
        stock = stockService.changeStock(id, stock);
        if (stock != null) {
            return ResponseEntity.ok().body(stock);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
