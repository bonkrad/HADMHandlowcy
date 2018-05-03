package pl.radekbonk.HADMHandlowcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HadmHandlowcyApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(HadmHandlowcyApplication.class);
    }

    public static void main(String[] args) {
        //User user = new User("Radosław","Bonk","bonkrad","yy","yy");
        SpringApplication.run(HadmHandlowcyApplication.class, args);
    }

    public static String getUploadPath() {
        if (System.getProperty("os.name").contains("Windows")) {
            return "C://raport/";
        } else {
            return "/home/pi/hadm/";
        }
    }
}
