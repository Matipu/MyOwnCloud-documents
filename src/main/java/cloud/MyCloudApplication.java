package cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(RestController.class))
public class MyCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCloudApplication.class, args);
    }
}
