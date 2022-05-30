package com.fonada.srana.test;

import com.fonada.srana.test.service.CsvService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ExecutionException;

/**
 * @author sandeep.rana
 */
@SpringBootApplication
public class ProgrammingTestApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ApplicationContext app = SpringApplication.run(ProgrammingTestApplication.class, args);
		CsvService csvService = app.getBean(CsvService.class);

		// start the service
		csvService.letsGo();
	}

}
