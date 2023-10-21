package project.projectucvwebsystem;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import project.projectucvwebsystem.service.SupplierService;

@SpringBootApplication
public class ProjectUcvWebSystemApplication {

	public static void main(String[] args) {
		/*byte[] bytes = "c3lzdGVtd2VicHJvamVjdHVjdmluZ3NvZnR3YXJl".getBytes();
		int longBytes = bytes.length * 8;
		System.out.println(longBytes);*/
		SpringApplication.run(ProjectUcvWebSystemApplication.class, args);
	}
	//Add history about activities of storage control and sales.

	/*@Bean
	public CommandLineRunner creatPassword () {
		return args -> {
			System.out.println(passwordEncoder.encode("123"));
		};
	}*/
}
