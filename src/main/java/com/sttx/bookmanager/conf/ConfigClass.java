package com.sttx.bookmanager.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "classpath:bookmanagerApp.xml" })
public class ConfigClass {

    public ConfigClass() {
        super();
        System.out.println("AUTO ConfigClass");
    }

}
