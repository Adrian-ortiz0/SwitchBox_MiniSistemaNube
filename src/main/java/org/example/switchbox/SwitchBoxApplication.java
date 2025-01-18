package org.example.switchbox;

import org.example.switchbox.views.UI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SwitchBoxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SwitchBoxApplication.class, args);


        UI ui = context.getBean(UI.class);
        ui.start();
    }

}
