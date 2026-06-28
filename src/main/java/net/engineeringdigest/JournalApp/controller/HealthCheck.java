package net.engineeringdigest.JournalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // this class has already @Restcontroller  but also all its end point and url path will convert into json on what the class return.
public class HealthCheck { // method
   @GetMapping("/health-check") // means the function healthcheck() will map on end point
   public String healthcheck(){  //restcontroller has special type so this string is the special type.
        return "ok";
// now the function healthcheck() map on health-check this endpoint.
    }
}
// if u run localhost 8080 health-check with get then controler will be on String healthcheck() function.

