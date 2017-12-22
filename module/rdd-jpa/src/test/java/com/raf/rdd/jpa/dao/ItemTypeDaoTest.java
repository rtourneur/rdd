package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.fwk.util.Paged;
import com.raf.rdd.jpa.domain.item.ItemType;

/**
 * Test class for {@link ItemTypeDao}.
 *
 * @author RAF
 */
public class ItemTypeDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private ItemTypeDao itemTypeDao;

  /**
   * Test method for {@link ItemTypeDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final String itemTypeName = "Cuir et Bagages";

    final ItemType itemType = this.itemTypeDao.getById(itemTypeName);
    assertNotNull(itemType);
    assertNotNull(itemType.toString());
    assertEquals(itemTypeName, itemType.getIdentifier());
  }

  /**
   * Test method for {@link ItemTypeDao#findByExample(ItemType)}.
   */
  @Test
  public void testFindByExample() {
    final String itemTypeName = "He";
    final ItemType example = new ItemType();
    example.setIdentifier(itemTypeName);

    final List<ItemType> result = this.itemTypeDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(2, result.size());
  }

  /**
   * Test method for {@link ItemTypeDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<ItemType> result = this.itemTypeDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(20, result.size());
  }

  /**
   * Test method for {@link ItemTypeDao#list(int, int)}.
   */
  @Test
  public void testList() {
    final Paged<ItemType> result = this.itemTypeDao.list(10, 1);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(10, result.size());
  }

}
