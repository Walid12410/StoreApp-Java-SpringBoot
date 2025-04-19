package com.example.ecommerce.storeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class ConnectionCheckController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/check-connection")
    public String checkConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(1)) { // 1 second timeout
                return "Database connection is OK ✅";
            } else {
                return "Database connection is NOT valid ❌";
            }
        } catch (Exception e) {
            return "Database connection failed ❌: " + e.getMessage();
        }
    }
}
