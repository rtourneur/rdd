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
    final DraconicHourEnum draconicHourEnum = DraconicHourEnum.CROWN;

    final DraconicHour draconicHour = this.draconicHourDao.getById(draconicHourEnum.getCode());
    assertNotNull(draconicHour);
    assertEquals(DraconicHourEnum.CROWN.getCode(), draconicHour.getIdentifier());
  }

  /**
   * Test method for {@link DraconicHourDao#findByExample(DraconicHour)}.
   */
  @Test
  public void testFindByExample() {
    final DraconicHourEnum draconicHourEnum = DraconicHourEnum.CROWN;
    final DraconicHour example = new DraconicHour();
    example.setIdentifier(draconicHourEnum.getCode());

    final List<DraconicHour> result = this.draconicHourDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

  /**
   * Test method for {@link DraconicHourDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<DraconicHour> result = this.draconicHourDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
