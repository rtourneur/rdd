package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.rdd.jpa.domain.item.Weapon;

/**
 * Test class for {@link WeaponDao}.
 *
 * @author RAF
 */
public class WeaponDaoTest extends AbstractDaoTest {

  /** The dao. */
  @Resource
  private WeaponDao weaponDao;

  /** The item type dao. */
  @Resource
  private ItemTypeDao itemTypeDao;

  /**
   * Test method for {@link WeaponDao#getById(java.io.Serializable)}.
   */
  @Test
  public void testGetById() {
    final String name = "Épée gnome";
    final Weapon item = this.weaponDao.getById(name);
    assertNotNull(item);
    assertEquals(name, item.getIdentifier());
  }

  /**
   * Test method for {@link WeaponDao#findByExample(Weapon)}.
   */
  @Test
  public void testFindByExample() {
    final String name = "Épée";
    final Weapon example = new Weapon();
    example.setIdentifier(name);
    List<Weapon> result = this.weaponDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(5, result.size());
    example.setItemType(this.itemTypeDao.getById("Armes de mêlée"));
    result = this.weaponDao.findByExample(example);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(5, result.size());
  }

  /**
   * Test method for {@link WeaponDao#listAll()}.
   */
  @Test
  public void testListAll() {
    final List<Weapon> result = this.weaponDao.listAll();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}
