package com.janflpk.collectionsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
//public class CollectionsManagerApplication extends SpringBootServletInitializer { //} implements CommandLineRunner {

/*    @Autowired
    private ApplicationContext appContext;*/

    //public static void main(String[] args) {
        //SpringApplication.run(CollectionsManagerApplication.class, args);
    //}

/*    @Override
    public void run(String... args) throws Exception {

        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans) {
            System.out.println(bean);
        }

    }
}*/
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class CollectionsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectionsManagerApplication.class, args);
    }

}
