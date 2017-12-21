package com.raf.rdd.jpa.domain.character;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.raf.fwk.jpa.domain.AbstractIdEntity;
import com.raf.rdd.jpa.domain.item.Armor;
import com.raf.rdd.jpa.domain.item.Item;
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

  /** The character. */
  @ManyToOne(optional = false)
  @JoinColumn(name = "CHARACTER_ID")
  private Character character;

  /** The item category. */
  @Convert(converter = ItemCategoryConverter.class)
  @Column(name = "ITEM_CATEGORY", nullable = false, length = 9)
  private ItemCategoryEnum itemCategory;
  
  /** The item count. */
  @Column(name = "COUNT", nullable = false, precision = 3)
  private int count;
  
  /** The parent. */
  @ManyToOne
  @JoinColumn(name = "PARENT_ID")
  private CharacterItem parent;
  
  /** The item. */
  @ManyToOne
  @JoinColumn(name = "ITEM")
  private Item item;

  /** The wear. */
  @ManyToOne
  @JoinColumn(name = "WEAR")
  private Wear wear;

  /** The armor. */
  @ManyToOne
  @JoinColumn(name = "ARMOR")
  private Armor armor;
}
