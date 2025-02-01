package edu.galaxy.bookstore.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class IsbnGeneratorImpl implements IsbnGenerator {

	@Override
	public Long generateIsbn() {
		Random random = new Random();
        long min = (long) Math.pow(10, 12);  // 10^12
        long max = (long) Math.pow(10, 13) - 1; // 10^13 - 1
        return min + ((long) (random.nextDouble() * (max - min)));
	}

}