package pl.dominikbarys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.dominikbarys.entity.Category;
import pl.dominikbarys.entity.Product;
import pl.dominikbarys.service.CategoryService;
import pl.dominikbarys.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PriceCompassApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceCompassApplication.class, args);
	}




}
