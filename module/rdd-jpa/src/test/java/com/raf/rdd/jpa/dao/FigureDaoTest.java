package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.fwk.util.Paged;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.enums.DraconicHourEnum;
import com.raf.rdd.jpa.enums.GenderEnum;
import com.raf.rdd.jpa.enums.HandEnum;

/**
 * Test class for {@link FigureDao}.
 *
 * @author RAF
 */
public class FigureDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private FigureDao figureDao;

  /**
   * Test method for {@link FigureDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final Figure figure = this.figureDao.getById(Integer.valueOf(1));
    assertNotNull(figure);
    assertNotNull(figure.toString());
    assertEquals("Nitouche", figure.getName());
    assertTrue(figure.isDreamer());
    assertNotNull(figure.getPerson());
    assertEquals(DraconicHourEnum.DRAGON, figure.getPerson().getBirthTime().getDraconicHour());
    assertEquals(GenderEnum.FEMALE, figure.getPerson().getGender());
    assertEquals(19, figure.getPerson().getAge());
    assertEquals(61, figure.getPerson().getWeight());
    assertEquals(169, figure.getPerson().getSize());
    assertEquals("Blonde", figure.getPerson().getHairs());
    assertEquals("Noirs", figure.getPerson().getEyes());
    assertEquals(13, figure.getPerson().getBeauty());
    assertEquals(HandEnum.RIGHT_HANDED, figure.getPerson().getHand());

    assertEquals("Humain", figure.getBreedName());
    assertEquals(1000, figure.getWealth());

    assertNotNull(figure.getCharValues());
    assertEquals(14, figure.getCharValues().size());

    assertNotNull(figure.getSkillValues());
    assertEquals(67, figure.getSkillValues().size());
    
    assertNotNull(figure.getDerivedValue());
    assertEquals(4, figure.getDerivedValue().size());

  }

  /**
   * Test method for {@link FigureDao#findByExample(Figure)}.
   */
  @Test
  public void testFindByExample() {
    final String name = "Nit";
    final Figure example = new Figure();
    example.setName(name);
    final List<Figure> result = this.figureDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
  }

  /**
   * Test method for {@link FigureDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Figure> result = this.figureDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
  }

  /**
   * Test method for {@link FigureDao#list(int, int)}.
   */
  @Test
  public void testList() {
    final Paged<Figure> result = this.figureDao.list(10, 1);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
  }
}
