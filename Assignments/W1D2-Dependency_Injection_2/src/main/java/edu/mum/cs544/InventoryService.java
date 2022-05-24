package edu.mum.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements IInventoryService {

    @Autowired
    private ProductService productService;

    @Override
    public int getNumberInStock(int productNumber) {
        return productNumber - 200;
    }
}
