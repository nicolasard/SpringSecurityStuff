package ar.nic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

   @RequestMapping(value = "/hello")
   public String getProduct() {
      return "Hello world!";
   }
}