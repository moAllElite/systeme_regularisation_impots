package com.groupeisi.secu_impots;

import com.groupeisi.secu_impots.dto.DeclarantDto;
import com.groupeisi.secu_impots.entities.Declarant;
import com.groupeisi.secu_impots.mapper.DeclarantMapper;
import com.groupeisi.secu_impots.repositories.DeclarantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecuImpotsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuImpotsApplication.class, args);
    }

  // @Bean
    CommandLineRunner commandLineRunner(
            DeclarantMapper mapper,
            DeclarantRepository declarantRepository
    )
    {
        return  args -> {
            declarantRepository.save(
                   mapper.fromEntity(
                           new DeclarantDto(
                    null,
                    "FC",
                    "london",
                    "palace@gmail.com",
                    "065056010"
                    ))
            );

        };
    }
}