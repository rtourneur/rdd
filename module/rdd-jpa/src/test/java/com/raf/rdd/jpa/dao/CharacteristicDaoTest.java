package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.enums.CharacteristicEnum;

/**
 * Test class for {@link CharacteristicDao}.
 *
 * @author RAF
 */
public class CharacteristicDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private CharacteristicDao characteristicDao;

  /**
   * Test method for {@link CharacteristicDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final CharacteristicEnum characteristicEnum = CharacteristicEnum.CONSTITUTION;

    final Characteristic characteristic = this.characteristicDao.getById(characteristicEnum.getCode());
    assertNotNull(characteristic);
    assertEquals(CharacteristicEnum.CONSTITUTION.getCode(), characteristic.getIdentifier());
    assertTrue(characteristic.getCharacteristic().isAnimal());
    assertTrue(characteristic.getCharacteristic().isCharacter());
    assertTrue(characteristic.getCharacteristic().isExperience());
    assertFalse(characteristic.getCharacteristic().isDerived());

    final Characteristic characteristic2 = this.characteristicDao.getById(CharacteristicEnum.STATURE.getCode());
    assertNotNull(characteristic2);
    assertFalse(characteristic2.equals(characteristic));
    assertTrue(characteristic2.compareTo(characteristic) < 0);

  }

  /**
   * Test method for {@link CharacteristicDao#findByExample(Characteristic)}.
   */
  @Test
  public void testFindByExample() {
    final CharacteristicEnum characteristicEnum = CharacteristicEnum.CONSTITUTION;
    final Characteristic example = new Characteristic();
    example.setIdentifier(characteristicEnum.getCode());

    final List<Characteristic> result = this.characteristicDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

  /**
   * Test method for {@link CharacteristicDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Characteristic> result = this.characteristicDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
