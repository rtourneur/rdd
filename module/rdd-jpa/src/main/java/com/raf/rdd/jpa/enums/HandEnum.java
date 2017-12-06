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
  private static final Map<String, HandEnum> HANDS = new HashMap<>(HandEnum.values().length);
  static {
    for (final HandEnum handEnum : HandEnum.values()) {
      HANDS.put(handEnum.code, handEnum);
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
  public static HandEnum get(final String code) {
    return HANDS.get(code);
  }

}
