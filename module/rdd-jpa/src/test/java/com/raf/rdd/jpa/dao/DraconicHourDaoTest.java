package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.DraconicHour;
import com.raf.rdd.jpa.enums.DraconicHourEnum;

/**
 * 
 * Test class for {@link DraconicHourDao}.
 * 
 * @author RAF
 */
public class DraconicHourDaoTest extends AbstractDaoTest {
  
  @Resource
  private DraconicHourDao draconicHourDao;

  /**
   * Test method for {@link DraconicHourDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    DraconicHourEnum draconicHourEnum = DraconicHourEnum.CROWN;
    
    DraconicHour draconicHour =  this.draconicHourDao.getById(draconicHourEnum.getName());
    assertNotNull(draconicHour);
    assertEquals(DraconicHourEnum.CROWN.getName(), draconicHour.getIdentifier());
  }

  /**
   * Test method for {@link DraconicHourDao#findByExample(DraconicHour)}.
   */
  @Test
  public void testFindByExample() {
    DraconicHourEnum draconicHourEnum = DraconicHourEnum.CROWN;
    DraconicHour example = new DraconicHour();
    example.setIdentifier(draconicHourEnum.getName());
    
    List<DraconicHour> result = this.draconicHourDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

  /**
   * Test method for {@link DraconicHourDao#listAll()}.
   */
  @Test
  public void testListAll() {
    List<DraconicHour> result = this.draconicHourDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
