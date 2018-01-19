package com.raf.rdd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;

import com.raf.fwk.service.ServiceException;
import com.raf.rdd.jpa.dao.BreedDao;
import com.raf.rdd.jpa.dao.CharacteristicDao;
import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.character.CharValue;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.enums.CharacteristicEnum;

/**
 * Test class for {@link CharacteristicService}.
 * 
 * @author RAF
 */
public class CharacteristicServiceTest extends AbstractServiceTest {

  /** The service. */
  @Resource
  private CharacteristicService characteristicService;

  /** The Figure service. */
  @Resource
  private FigureService figureService;

  /** The Characteristic Dao. */
  @Resource
  private CharacteristicDao characteristicDao;

  /** The Breed Dao. */
  @Resource
  private BreedDao breedDao;

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

  /**
   * Test method for {@link CharacteristicService#increment(Figure, Characteristic, int)}.
   */
  @Test
  public void testIncrement() {
    final Figure figure = new Figure();
    Characteristic characteristic = this.characteristicDao.getById(CharacteristicEnum.AGILITY.getCode());
    assertNotNull(characteristic);
    int result = this.characteristicService.increment(figure, characteristic, 10);
    assertEquals(11, result);
    result = this.characteristicService.increment(figure, characteristic, 15);
    assertEquals(15, result);
    final Breed breed = this.breedDao.getById("Chafouin");
    assertNotNull(breed);
    figure.setBreed(breed);
    result = this.characteristicService.increment(figure, characteristic, 15);
    assertEquals(16, result);
    characteristic = this.characteristicDao.getById(CharacteristicEnum.STATURE.getCode());
    assertNotNull(characteristic);
    result = this.characteristicService.increment(figure, characteristic, 10);
    assertEquals(10, result);
  }

  /**
   * Test method for {@link CharacteristicService#decrement(Figure, Characteristic, int)}.
   */
  @Test
  public void testDecrement() {
    final Figure figure = new Figure();
    Characteristic characteristic = this.characteristicDao.getById(CharacteristicEnum.AGILITY.getCode());
    assertNotNull(characteristic);
    int result = this.characteristicService.decrement(figure, characteristic, 10);
    assertEquals(9, result);
    result = this.characteristicService.decrement(figure, characteristic, 6);
    assertEquals(6, result);
    final Breed breed = this.breedDao.getById("Chafouin");
    assertNotNull(breed);
    figure.setBreed(breed);
    result = this.characteristicService.decrement(figure, characteristic, 10);
    assertEquals(10, result);
    characteristic = this.characteristicDao.getById(CharacteristicEnum.STATURE.getCode());
    assertNotNull(characteristic);
    result = this.characteristicService.decrement(figure, characteristic, 6);
    assertEquals(5, result);
  }

  @Test
  @Transactional
  public void testGetInitialCost() {
    try {
      Figure figure = this.figureService.get("Nitouche");
      assertNotNull(figure);
      assertEquals(160, this.characteristicService.getInitialCost(figure));

      figure = this.figureService.create();
      figure.setCharValues(this.characteristicService.initCharacteristics(figure));
      assertEquals(84, this.characteristicService.getInitialCost(figure));

    } catch (ServiceException e) {
      fail(e.getMessage());
    }
  }
}
