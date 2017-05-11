package com.raf.rdd.jpa.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.raf.rdd.database.config.DatasourceConfig;
import com.raf.rdd.jpa.config.PersistenceJpaConfig;

/**
 * 
 * Abstract class for all DAO tests.
 * 
 * @author RAF
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { PersistenceJpaConfig.class, DatasourceConfig.class }, loader = AnnotationConfigContextLoader.class)
public abstract class AbstractDaoTest  {

  /**
   * Constructor.
   */
  public AbstractDaoTest() {
    super();
  }

}
