package com.raf.rdd.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.raf.rdd.database.config.DatasourceConfig;
import com.raf.rdd.service.config.ServiceConfig;

/**
 * Abstract class for all DAO tests.
 *
 * @author RAF
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { ServiceConfig.class,
    DatasourceConfig.class }, loader = AnnotationConfigContextLoader.class)
public abstract class AbstractServiceTest {

  /**
   * Constructor.
   */
  public AbstractServiceTest() {
    super();
  }

}
