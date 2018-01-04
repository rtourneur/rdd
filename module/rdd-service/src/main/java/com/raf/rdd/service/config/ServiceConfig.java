package com.raf.rdd.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.raf.fwk.util.config.UtilConfig;
import com.raf.rdd.jpa.config.PersistenceJpaConfig;

import lombok.NoArgsConstructor;

/**
 * Spring configuration class for services.
 * 
 * @author RAF
 */
@Configuration
@Import(value = { UtilConfig.class, PersistenceJpaConfig.class })
@ComponentScan("com.raf.rdd.service")
@EnableTransactionManagement
@NoArgsConstructor
public class ServiceConfig {
  // RAS
}
