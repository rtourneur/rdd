package com.raf.rdd.jpa.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for right and left hand.
 * 
 * @author RAF
 */
@Getter
@RequiredArgsConstructor
public enum HandEnum {
  /** The Right-handed value. */
  RIGHT_HANDED("R"),
  /** The Left-handed value. */
  LEFT_HANDED("L"),
  /** The Ambidextrous value. */
  AMBIDEXTROUS("A");

  /**
   * Map for enum conversion.
   */
  private static final Map<String, HandEnum> HANDS = new HashMap<>(
      HandEnum.values().length);
  static {
    for (HandEnum handEnum : HandEnum.values()) {
      HANDS.put(handEnum.name, handEnum);
    }
  }

  /** Name for the enum. */
  private final String name;

  /**
   * Return the enum corresponding to the name.
   * 
   * @param name
   *          Name of the enum
   * @return the enum.
   */
  public static HandEnum get(final String name) {
    return HANDS.get(name);
  }

}
