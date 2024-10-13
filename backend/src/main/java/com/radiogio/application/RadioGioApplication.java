package com.radiogio;

import com.radiogio.models.Country;
import com.radiogio.models.Location;
import com.radiogio.models.State;
import com.radiogio.models.Radio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@SpringBootApplication
public class RadioGioApplication {

    private static final Logger logger = LoggerFactory.getLogger(RadioGioApplication.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RadioGioApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (!mongoTemplate.collectionExists(Location.class)) {
                mongoTemplate.createCollection(Location.class);
                mongoTemplate.createCollection(Radio.class);

                // Crear datos de prueba
                Radio radioArgentina1 = Radio.builder()
                        .name("Radio Argentina 1")
                        .description("Descripción de Radio Argentina 1")
                        .url("http://argentina1.com/stream")
                        .imageUrl("http://argentina1.com/image.jpg")
                        .continent("South America")
                        .country("Argentina")
                        .state("Buenos Aires")
                        .build();

                Radio radioArgentina2 = Radio.builder()
                        .name("Radio Argentina 2")
                        .description("Descripción de Radio Argentina 2")
                        .url("http://argentina2.com/stream")
                        .imageUrl("http://argentina2.com/image.jpg")
                        .continent("South America")
                        .country("Argentina")
                        .state("Buenos Aires")
                        .build();

                Radio radioBrazil1 = Radio.builder()
                        .name("Radio Brazil 1")
                        .description("Descrição de Radio Brazil 1")
                        .url("http://brazil1.com/stream")
                        .imageUrl("http://brazil1.com/image.jpg")
                        .continent("South America")
                        .country("Brazil")
                        .state("São Paulo")
                        .build();

                Radio radioBrazil2 = Radio.builder()
                        .name("Radio Brazil 2")
                        .description("Descrição de Radio Brazil 2")
                        .url("http://brazil2.com/stream")
                        .imageUrl("http://brazil2.com/image.jpg")
                        .continent("South America")
                        .country("Brazil")
                        .state("São Paulo")
                        .build();

                State buenosAires = State.builder()
                        .name("Buenos Aires")
                        .radios(Arrays.asList(radioArgentina1, radioArgentina2))
                        .build();

                State saoPaulo = State.builder()
                        .name("São Paulo")
                        .radios(Arrays.asList(radioBrazil1, radioBrazil2))
                        .build();

                Country argentina = Country.builder()
                        .name("Argentina")
                        .states(Arrays.asList(buenosAires))
                        .build();

                Country brazil = Country.builder()
                        .name("Brazil")
                        .states(Arrays.asList(saoPaulo))
                        .build();

                Location southAmerica = Location.builder()
                        .continent("South America")
                        .countries(Arrays.asList(argentina, brazil))
                        .build();

                mongoTemplate.save(southAmerica);
                mongoTemplate.save(radioArgentina1);
                mongoTemplate.save(radioArgentina2);
                mongoTemplate.save(radioBrazil1);
                mongoTemplate.save(radioBrazil2);

                logger.info("Base de datos inicializada con datos de prueba.");
            } else {
                logger.info("La base de datos ya existe.");
            }
        };
    }
}
