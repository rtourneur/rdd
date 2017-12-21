package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.skill.Skill;
import com.raf.rdd.jpa.enums.SkillTypeEnum;

/**
 * Test class for {@link SkillDao}.
 *
 * @author RAF
 */
public class SkillDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private SkillDao skillDao;

  /** The skill type dao. */
  @Resource
  private SkillTypeDao skillTypeDao;

  /**
   * Test method for {@link SkillDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final String name = "Chant";
    Skill skill = this.skillDao.getById(name);
    assertNotNull(skill);
    assertEquals(name, skill.getIdentifier());
    assertEquals(SkillTypeEnum.GENERAL, skill.getSkillTypeEnum());
    assertEquals("skill.chant.description", skill.getDescriptionCode());
    
    skill = this.skillDao.getById("Corps à corps");
    assertNotNull(skill);
    assertNotNull(skill.getRule());
    assertNotNull(skill.getGroup());
  }

  /**
   * Test method for {@link SkillDao#findByExample(Skill)}.
   */
  @Test
  public void testFindByExample() {
    final String name = "Chant";
    final Skill example = new Skill();
    example.setIdentifier(name);
    List<Skill> result = this.skillDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    example.setName(null);
    example.setSkillType(this.skillTypeDao.getById(SkillTypeEnum.GENERAL.getCode()));
    result = this.skillDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    
    example.setSkillType(null);
    example.setSkillTypeEnum(SkillTypeEnum.DRACONIC);
    result = this.skillDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(4, result.size());
  }

  /**
   * Test method for {@link SkillDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Skill> result = this.skillDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
