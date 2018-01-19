package com.raf.rdd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.DraconicHour;
import com.raf.rdd.jpa.enums.DraconicHourEnum;

/**
 * Test class for {@link DraconicHourService}.
 * 
 * @author RAF
 */
public class DraconicHourServiceTest extends AbstractServiceTest {

  /** The service. */
  @Resource
  private DraconicHourService draconicHourService;

  /**
   * Test method for {@link DraconicHourService#get(DraconicHourEnum)}.
   */
  @Test
  public void testGet() {
    final DraconicHourEnum draconicHourEnum = DraconicHourEnum.DRAGON;
    final DraconicHour draconicHour = this.draconicHourService.get(draconicHourEnum);
    assertNotNull(draconicHour);
    assertEquals(draconicHourEnum, draconicHour.getDraconicHour());
    assertEquals(draconicHourEnum.getCode(), draconicHour.getName());
  }

}
