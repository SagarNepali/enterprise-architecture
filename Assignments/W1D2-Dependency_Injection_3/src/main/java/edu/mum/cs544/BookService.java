package edu.mum.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements IBookService {
	@Autowired
	public List<IBookSupplier> suppliers ;

	@Autowired
	@Qualifier("amazon")
	private IBookSupplier amazon;

	@Autowired
	@Qualifier("eBooks")
	private IBookSupplier eBooks;

	@Autowired
	@Qualifier("barnesAndNoble")
	private IBookSupplier barnesAndNoble;


	public BookService(List<IBookSupplier> suppliers) {

		this.suppliers = suppliers;

		suppliers.add(amazon);
		suppliers.add(barnesAndNoble);
		suppliers.add(eBooks);
	}

	public void buy(Book book) {
		double lowestPrice = 0;
		IBookSupplier cheapestSupplier = null;
		// find the cheapest supplier
		for (IBookSupplier supplier : suppliers) {
			double price = supplier.computePrice(book.getIsbn());
			if (cheapestSupplier == null) {
				cheapestSupplier = supplier;
				lowestPrice = price;
			} else {
				if (price < lowestPrice) {
					cheapestSupplier = supplier;
					lowestPrice = price;
				}
			}
		}
		// buy with the cheapest supplier
		if (cheapestSupplier != null) {
			cheapestSupplier.order(book);
		}

	}
}