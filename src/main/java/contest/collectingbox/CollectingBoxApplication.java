package contest.collectingbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CollectingBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectingBoxApplication.class, args);
    }

}
