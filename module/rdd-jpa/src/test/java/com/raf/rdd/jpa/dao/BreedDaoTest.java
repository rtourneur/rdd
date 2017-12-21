package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.breed.BreedCharac;
import com.raf.rdd.jpa.domain.skill.Skill;
import com.raf.rdd.jpa.enums.CharacteristicEnum;

/**
 * Test class for {@link BreedDao}.
 *
 * @author RAF
 */
public class BreedDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private BreedDao breedDao;

  /** The characteristic dao. */
  @Resource
  private CharacteristicDao characteristicDao;

  /** The skill dao. */
  @Resource
  private SkillDao skillDao;

  
  /**
   * Test method for {@link BreedDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final String name = "Gigant";
    final Breed breed = this.breedDao.getById(name);
    assertNotNull(breed);
    assertNotNull(breed.toString());
    assertEquals(name, breed.getIdentifier());
    assertEquals(2, breed.getDefense());
    assertNotNull(breed.getCharBreeds());
    assertEquals(14, breed.getCharBreeds().size());
    final BreedCharac breedCharac = new BreedCharac();
    breedCharac.setCharacteristic(this.characteristicDao.getById(CharacteristicEnum.STATURE.getCode()));
    breedCharac.setModifier(8);
    breedCharac.setLimit(Integer.valueOf(23));
    assertTrue(breed.getCharBreeds().contains(breedCharac));
    assertNotNull(breed.getForbiddens());
    assertEquals(11, breed.getForbiddens().size());
    final Skill skill = this.skillDao.getById("Survie en cité");
    assertTrue(breed.getForbiddens().contains(skill));

  }

  /**
   * Test method for {@link BreedDao#findByExample(Breed)}.
   */
  @Test
  public void testFindByExample() {
    final String name = "G";
    final Breed example = new Breed();
    example.setIdentifier(name);
    final List<Breed> result = this.breedDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(5, result.size());
  }

  /**
   * Test method for {@link BreedDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Breed> result = this.breedDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
