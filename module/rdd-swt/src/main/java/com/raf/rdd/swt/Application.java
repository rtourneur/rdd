package com.raf.rdd.swt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.raf.fwk.util.config.UtilConfig;
import com.raf.rdd.database.config.DatasourceConfig;
import com.raf.rdd.jpa.config.PersistenceJpaConfig;
import com.raf.rdd.service.config.ServiceConfig;
import com.raf.rdd.swt.ui.RddDisplay;

import lombok.NoArgsConstructor;

@Configuration
@Import(value = { UtilConfig.class, DatasourceConfig.class, PersistenceJpaConfig.class, ServiceConfig.class })
@ComponentScan("com.raf.rdd.swt")
@NoArgsConstructor
public class Application {

  /** The Messages source. */
  @Bean
  public MessageSource messageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("label.messages");
    return messageSource;
  }

  /** The Errors source. */
  @Bean
  public MessageSource errorSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("label.errors");
    return messageSource;
  }

  public static void main(String[] args) {
    final ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

    final RddDisplay main = context.getBean(RddDisplay.class);
    main.run(main.init());

  }
}
