package com.raf.rdd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.dao.BreedDao;
import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.domain.character.SkillValue;

/**
 * Test class for {@link SkillService}.
 * 
 * @author RAF
 */
public class SkillServiceTest extends AbstractServiceTest {

  /** The service. */
  @Resource
  private SkillService skillService;

  /** The breed dao. */
  @Resource
  private BreedDao breedDao;

  /**
   * Test method for {@link SkillService#initSkills(Figure)}.
   */
  @Test
  public void testInitSkills() {
    final Figure figure = new Figure();
    final Breed breed = this.breedDao.getById("Chafouin");
    figure.setBreed(breed);
    final Set<SkillValue> values = this.skillService.initSkills(figure);
    assertNotNull(values);
    assertFalse(values.isEmpty());
    assertEquals(67, values.size());
  }

}
