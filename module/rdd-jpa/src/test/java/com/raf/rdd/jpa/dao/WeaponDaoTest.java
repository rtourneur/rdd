package com.raf.rdd.jpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.raf.fwk.util.Paged;
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
    final String name = "Épée bâtarde";
    final Weapon item = this.weaponDao.getById(name);
    assertNotNull(item);
    assertNotNull(item.toString());
    assertEquals(name, item.getIdentifier());
    assertEquals("Armes de mêlée", item.getItemTypeName());
    assertNotNull(item.getGenericItem());
    assertEquals(new BigDecimal("3.0"), item.getGenericItem().getEncumbrance());
    assertEquals(Integer.valueOf(3000), item.getGenericItem().getMinPrice());
    assertNull(item.getGenericItem().getMaxPrice());
    assertNotNull(item.getSkill1());
    assertEquals("Épée à une main", item.getSkillName1());
    assertNotNull(item.getSkill2());
    assertEquals("Épée à deux mains", item.getSkillName2());
    assertNotNull(item.getHandWeapon());
    assertEquals("1 / 2 mains", item.getHandWeapon().getCode());
    assertEquals(Integer.valueOf(14), item.getRes());
    assertEquals(Integer.valueOf(4), item.getBonusDom1());
    assertEquals(Integer.valueOf(5), item.getBonusDom2());
    assertEquals(Integer.valueOf(13), item.getStrength1());
    assertEquals(Integer.valueOf(12), item.getStrength2());
    assertFalse(item.isNonlethal());
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
    assertEquals(32, result.size());
  }

  /**
   * Test method for {@link WeaponDao#list(int, int)}.
   */
  @Test
  public void testList() {
    final Paged<Weapon> result = this.weaponDao.list(10, 1);
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(10, result.size());
  }

}
