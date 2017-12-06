package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.item.Armor;

/**
 * Test class for {@link ArmorDao}.
 *
 * @author RAF
 */
public class ArmorDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private ArmorDao armorDao;

  /** The item type dao. */
  @Resource
  private ItemTypeDao itemTypeDao;

  /**
   * Test method for {@link ArmorDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final String name = "Cuir souple";
    final Armor item = this.armorDao.getById(name);
    assertNotNull(item);
    assertEquals(name, item.getIdentifier());
  }

  /**
   * Test method for {@link ArmorDao#findByExample(Armor)}.
   */
  @Test
  public void testFindByExample() {
    final String name = "Cuir";
    final Armor example = new Armor();
    example.setIdentifier(name);
    List<Armor> result = this.armorDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(3, result.size());
    example.setItemType(this.itemTypeDao.getById("Armures"));
    result = this.armorDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(3, result.size());
  }

  /**
   * Test method for {@link ArmorDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Armor> result = this.armorDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
