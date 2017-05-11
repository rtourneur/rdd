package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.SkillType;
import com.raf.rdd.jpa.enums.SkillTypeEnum;

/**
 * 
 * Test class for {@link SkillTypeDao}.
 * 
 * @author RAF
 */
public class SkillTypeDaoTest extends AbstractDaoTest {
  
  @Resource
  private SkillTypeDao skillTypeDao;

  /**
   * Test method for {@link SkillTypeDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    SkillTypeEnum skillTypeEnum = SkillTypeEnum.GENERAL;
    
    SkillType skillType =  this.skillTypeDao.getById(skillTypeEnum.getName());
    assertNotNull(skillType);
    assertEquals(SkillTypeEnum.GENERAL.getName(), skillType.getIdentifier());
  }

  /**
   * Test method for {@link SkillTypeDao#findByExample(SkillType)}.
   */
  @Test
  public void testFindByExample() {
    SkillTypeEnum skillTypeEnum = SkillTypeEnum.GENERAL;
    SkillType example = new SkillType();
    example.setIdentifier(skillTypeEnum.getName());
    
    List<SkillType> result = this.skillTypeDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

  /**
   * Test method for {@link SkillTypeDao#listAll()}.
   */
  @Test
  public void testListAll() {
    List<SkillType> result = this.skillTypeDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
