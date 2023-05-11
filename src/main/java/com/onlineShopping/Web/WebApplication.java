package com.onlineShopping.Web;

import com.onlineShopping.Web.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class WebApplication implements CommandLineRunner {

	PaymentsRepository paymentsRepository;

	AddressRepository addressRepository;

	OrdersRepository ordersRepository;

	ProductsRepository productsRepository;

	UsersRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		AtomicInteger count = new AtomicInteger(0);
//		List<Users> users = usersRepository.findAll();
//		List<Products> products = productsRepository.findAll();
//		List<Payments> payments = paymentsRepository.findAll();
//		AtomicInteger finalCount2 = count;
//		ordersRepository.findAll().forEach(i->{
//			//i.getProducts().add(products.get(count.get()));
//			//i.setPayment(payments.get(count.get()));
//			i.setUser(users.get(finalCount2.get()));
//			ordersRepository.save(i);
//		});
//		count = new AtomicInteger(0);
//		AtomicInteger finalCount = count;
//		ordersRepository.findAll().forEach(i->{
//			Products p1 = products.get(finalCount.get());
////			p1.setAddress(addressRepository.findAll().get(finalCount.get()));
////			p1.getOrders().add(i);
////			productsRepository.save(p1);
//			i.getProducts().add(p1);
//			ordersRepository.save(i);
//		});
//
//		AtomicInteger finalCount1 = count;
//		ordersRepository.findAll().forEach(i->{
//			Payments p = payments.get(finalCount1.getAndIncrement());
//			//paymentsRepository.save(p);
//			i.setPayment(p);
//			ordersRepository.save(i);
//		});
//
	}
}
