package com.raf.rdd.jpa.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for item category.
 *
 * @author RAF
 */
@Getter
@RequiredArgsConstructor
public enum ItemCategoryEnum {
  /** The base item type. */
  ITEM("item"),
  /** The wear item type. */
  WEAR("wear"),
  /** The armor item type. */
  ARMOR("armor"),
  /** The weapon item type. */
  WEAPON("weapon");

  /**
   * Map for enum conversion.
   */
  private static final Map<String, ItemCategoryEnum> ITEM_CATEGORIES = new HashMap<>(ItemCategoryEnum.values().length);
  static {
    for (final ItemCategoryEnum itemCategoryEnum : ItemCategoryEnum.values()) {
      ITEM_CATEGORIES.put(itemCategoryEnum.code, itemCategoryEnum);
    }
  }

  /** Code for the enum. */
  private final String code;

  /**
   * Return the enum corresponding to the code.
   *
   * @param code
   *          Code of the enum
   * @return the enum.
   */
  public static ItemCategoryEnum get(final String code) {
    return ITEM_CATEGORIES.get(code);
  }

}
