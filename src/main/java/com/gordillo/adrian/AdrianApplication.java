package com.gordillo.adrian;

import com.gordillo.adrian.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@SpringBootApplication
public class AdrianApplication implements CommandLineRunner {
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AdrianApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Solo para desarrollo se puede establecer que vaya borrando
            //    uploadFileService.deleteAll();
            uploadFileService.init();
        } catch (IOException ex) {
        }
        String passBCript = passwordEncoder.encode("adrian");
        System.out.println("Contraseña encriptada inicial de admin: " + passBCript);
    }
}
