package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.item.Wear;

/**
 * Test class for {@link WearDao}.
 *
 * @author RAF
 */
public class WearDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private WearDao wearDao;

  /** The item type dao. */
  @Resource
  private ItemTypeDao itemTypeDao;

  /**
   * Test method for {@link WearDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final String name = "Pourpoint de cuir souple";
    final Wear item = this.wearDao.getById(name);
    assertNotNull(item);
    assertEquals(name, item.getIdentifier());
  }

  /**
   * Test method for {@link WearDao#findByExample(Wear)}.
   */
  @Test
  public void testFindByExample() {
    final String name = "Pourpoint ";
    final Wear example = new Wear();
    example.setIdentifier(name);
    List<Wear> result = this.wearDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(3, result.size());
    example.setItemType(this.itemTypeDao.getById("Cuir et Bagages"));
    result = this.wearDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
  }

  /**
   * Test method for {@link WearDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Wear> result = this.wearDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
