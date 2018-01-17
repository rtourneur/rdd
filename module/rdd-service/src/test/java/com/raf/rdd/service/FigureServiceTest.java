package com.raf.rdd.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.dao.BreedDao;
import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.character.Figure;

/**
 * Test class for {@link FigureService}.
 * 
 * @author RAF
 */
public class FigureServiceTest extends AbstractServiceTest {

  /** The service. */
  @Resource
  private FigureService figureService;

  /** The breed dao. */
  @Resource
  private BreedDao breedDao;

  /**
   * Test method for {@link FigureService#find(String)}.
   */
  @Test
  public void testFind() {
    final String name = "Nit";
    final List<Figure> figures = this.figureService.find(name);
    assertNotNull(figures);
    assertFalse(figures.isEmpty());
    final Figure figure = figures.get(0);
    assertNotNull(figure);
    assertEquals("Nitouche", figure.getName());
  }

  /**
   * Test method for {@link FigureService#create()}.
   */
  @Test
  public void testCreate() {
    final Figure figure = this.figureService.create();
    assertNotNull(figure);
    assertNotNull(figure.getCharValues());
    assertNotNull(figure.getPerson());
    assertNotNull(figure.getThreshold());
    assertFalse(figure.getCharValues().isEmpty());
    assertEquals(14, figure.getCharValues().size());
    assertNull(figure.getSkillValues());
  }

  /**
   * Test method for {@link FigureService#setBreed(Figure, Breed)}.
   */
  @Test
  public void testSetBreed() {
    final Figure figure = this.figureService.create();
    assertNotNull(figure);
    initCharac(figure);
    final Breed breed = this.breedDao.getById("Chafouin");
    assertNotNull(breed);
    this.figureService.setBreed(figure, breed);
    validCharac(figure);
  }

  private void initCharac(final Figure figure) {

    figure.getCharValues().forEach(charValue -> {
      switch (charValue.getCharacteristic().getCharacteristic()) {
      case STATURE:
        charValue.setCurrent(15);
        break;
      case APPEARANCE:
      case AGILITY:
        charValue.setCurrent(10);
        break;
      case STRENGTH:
        charValue.setCurrent(15);
        break;
      default:
        charValue.setCurrent(11);
        break;
      }
    });
  }

  private void validCharac(final Figure figure) {
    figure.getCharValues().forEach(charValue -> {
      switch (charValue.getCharacteristic().getCharacteristic()) {
      case STATURE:
        assertEquals(10, charValue.getCurrent());
        break;
      case APPEARANCE:
        assertEquals(8, charValue.getCurrent());
        break;
      case STRENGTH:
      case AGILITY:
      case SMELL_TASTE:
      case HEARING:
        assertEquals(14, charValue.getCurrent());
        break;
      case DEXTERITY:
        assertEquals(12, charValue.getCurrent());
        break;
      case WILLPOWER:
        assertEquals(7, charValue.getCurrent());
        break;
      case INTELLECT:
        assertEquals(6, charValue.getCurrent());
        break;
      default:
        assertEquals(11, charValue.getCurrent());
        break;
      }
    });
  }

}
