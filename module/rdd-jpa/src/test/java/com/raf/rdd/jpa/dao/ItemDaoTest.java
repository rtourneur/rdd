package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.item.Item;

/**
 * Test class for {@link ItemDao}.
 *
 * @author RAF
 */
public class ItemDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private ItemDao itemDao;

  /** The item type dao. */
  @Resource
  private ItemTypeDao itemTypeDao;

  /**
   * Test method for {@link ItemDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final String name = "Sac à dos";
    final Item item = this.itemDao.getById(name);
    assertNotNull(item);
    assertEquals(name, item.getIdentifier());
  }

  /**
   * Test method for {@link ItemDao#findByExample(Item)}.
   */
  @Test
  public void testFindByExample() {
    final String name = "Sac ";
    final Item example = new Item();
    example.setIdentifier(name);
    List<Item> result = this.itemDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(3, result.size());
    example.setItemType(this.itemTypeDao.getById("Cuir et Bagages"));
    result = this.itemDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
  }

  /**
   * Test method for {@link ItemDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Item> result = this.itemDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
