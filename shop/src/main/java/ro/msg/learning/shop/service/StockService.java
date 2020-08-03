package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public void update(Stock stock) {
        Optional<Stock> optionalStock = stockRepository.findById(stock.getId());
        optionalStock.ifPresentOrElse(
                foundStock -> {
                    Stock stock1 = optionalStock.get();
                    stock1.setProduct(stock.getProduct());
                    stock1.setQuantity(stock.getQuantity());
                    stock1.setLocation(stock.getLocation());
                    stockRepository.save(stock1);
                },
                () -> {
                    throw new IllegalArgumentException("The update cannot be done");
                }
        );
    }
}
