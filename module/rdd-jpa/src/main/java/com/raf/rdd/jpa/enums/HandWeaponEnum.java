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
public enum HandWeaponEnum {
  /** The one hand value. */
  ONE_HAND("1 main"),
  /** The Left-handed value. */
  ONE_TWO_HANDS("1 / 2 mains"),
  /** The two hand value. */
  TWO_HANDS("2 mains");

  /**
   * Map for enum conversion.
   */
  private static final Map<String, HandWeaponEnum> HAND_WEAPONS = new HashMap<>(HandWeaponEnum.values().length);
  static {
    for (final HandWeaponEnum handWeaponEnum : HandWeaponEnum.values()) {
      HAND_WEAPONS.put(handWeaponEnum.code, handWeaponEnum);
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
  public static HandWeaponEnum get(final String code) {
    return HAND_WEAPONS.get(code);
  }

}
