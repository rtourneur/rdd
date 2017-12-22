package com.raf.rdd.jpa.domain.character;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractIdEntity;
import com.raf.rdd.jpa.domain.item.Armor;
import com.raf.rdd.jpa.domain.item.Item;
import com.raf.rdd.jpa.domain.item.Weapon;
import com.raf.rdd.jpa.domain.item.Wear;
import com.raf.rdd.jpa.enums.ItemCategoryConverter;
import com.raf.rdd.jpa.enums.ItemCategoryEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the CHARACTER_ITEM database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "CHARACTER_ITEM", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class CharacterItem extends AbstractIdEntity {

  /** Serial UID. */
  private static final long serialVersionUID = -40806065267264408L;

  /** The character. */
  @ManyToOne(optional = false)
  @JoinColumn(name = "FIGURE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_CHAR_ITEM_FIGURE"))
  private Figure character;

  /** The item name. */
  @Column(name = "ITEM", length = 30)
  private String itemName;

  /** The item name. */
  @Column(name = "WEAR", length = 30)
  private String wearName;

  /** The item name. */
  @Column(name = "ARMOR", length = 30)
  private String armorName;

  /** The item name. */
  @Column(name = "WEAPON", length = 30)
  private String weaponName;

  /** The item count. */
  @Column(name = "COUNT", nullable = false, precision = 3)
  private int count;

  /** The item category. */
  @Convert(converter = ItemCategoryConverter.class)
  @Column(name = "ITEM_CATEGORY", nullable = false, length = 9)
  private ItemCategoryEnum itemCategory;

  /** The parent container (of type item). */
  @ManyToOne
  @JoinColumn(name = "CONTAINER_ID", foreignKey = @ForeignKey(name = "FK_CHAR_ITEM_PARENT"))
  private CharacterItem container;

  /** The item. */
  @ManyToOne
  @JoinColumn(name = "ITEM", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CHAR_ITEM_ITEM"))
  private Item item;

  /** The wear. */
  @ManyToOne
  @JoinColumn(name = "WEAR", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CHAR_ITEM_WEAR"))
  private Wear wear;

  /** The armor. */
  @ManyToOne
  @JoinColumn(name = "ARMOR", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CHAR_ITEM_ARMOR"))
  private Armor armor;

  /** The weapon. */
  @ManyToOne
  @JoinColumn(name = "WEAPON", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CHAR_ITEM_WEAPON"))
  private Weapon weapon;

  /**
   * Append the object values for the to string builder.
   *
   * @param builder
   *          the to string builder
   * @see AbstractIdEntity#appendId(ToStringBuilder)
   */
  @Override
  protected void appendId(final ToStringBuilder builder) {
    if (this.itemName != null) {
      builder.append("itemName", this.itemName);
    }
    if (this.wearName != null) {
      builder.append("wearName", this.wearName);
    }
    if (this.armorName != null) {
      builder.append("armorName", this.armorName);
    }
    if (this.weaponName != null) {
      builder.append("weaponName", this.weaponName);
    }
    builder.append("count", this.count).append("category", this.itemCategory.getCode());
    if (this.container != null && CharacterItem.class.equals(this.container.getClass())) {
      builder.append("container", this.container.getItemName());
    }
    appendItem(builder);
  }

  /**
   * Append the item description for the to string builder.
   * 
   * @param builder
   *          the to string builder
   */
  private void appendItem(final ToStringBuilder builder) {
    if (this.item != null && Item.class.equals(this.item.getClass())) {
      builder.append("item", this.item);
    }
    if (this.wear != null && Wear.class.equals(this.wear.getClass())) {
      builder.append("wear", this.wear);
    }
    if (this.armor != null && Armor.class.equals(this.armor.getClass())) {
      builder.append("armor", this.armor);
    }
    if (this.weapon != null && Weapon.class.equals(this.weapon.getClass())) {
      builder.append("weapon", this.weapon);
    }
  }

}
