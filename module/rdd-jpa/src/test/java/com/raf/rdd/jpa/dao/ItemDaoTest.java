package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.fwk.util.Paged;
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
    assertNotNull(item.toString());
    assertEquals(name, item.getIdentifier());
    assertEquals("Cuir et Bagages", item.getItemTypeName());
    assertNotNull(item.getGenericItem());
    assertEquals(new BigDecimal("0.4"), item.getGenericItem().getEncumbrance());
    assertEquals(Integer.valueOf(100), item.getGenericItem().getMinPrice());
    assertNull(item.getGenericItem().getMaxPrice());
    assertTrue(item.isContainer());
    assertFalse(item.isScalable());
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
    assertEquals(138, result.size());
  }

  /**
   * Test method for {@link ItemDao#list(int, int)}.
   */
  @Test
  public void testList() {
    final Paged<Item> result = this.itemDao.list(10, 1);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(10, result.size());
  }

}
