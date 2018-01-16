package com.raf.rdd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.character.CharValue;
import com.raf.rdd.jpa.domain.character.Figure;

/**
 * Test class for {@link CharacteristicService}.
 * 
 * @author RAF
 */
public class CharacteristicServiceTest extends AbstractServiceTest {

  /** The service. */
  @Resource
  private CharacteristicService characteristicService;

  /**
   * Test method for {@link CharacteristicService#initCharacteristics(Figure)}.
   */
  @Test
  public void testInitCharacteristics() {
    final Figure figure = new Figure();
    final Set<CharValue> values = this.characteristicService.initCharacteristics(figure);
    assertNotNull(values);
    assertFalse(values.isEmpty());
    assertEquals(14, values.size());
  }

}
