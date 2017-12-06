package com.raf.rdd.jpa.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for characteristics.
 *
 * @author RAF
 */
@Getter
@RequiredArgsConstructor
public enum CharacteristicEnum {
  /** Stature characteristic. */
  STATURE("TAILLE", false, true, true, false),
  /** Appearance characteristic. */
  APPEARANCE("APPARENCE", false, true, true, true),
  /** Constitution characteristic. */
  CONSTITUTION("CONSTITUTION", false, true, true, true),
  /** Strength characteristic. */
  STRENGTH("FORCE", false, true, true, true),
  /** Agility characteristic. */
  AGILITY("AGILITÉ", false, true, true, true),
  /** Dexterity characteristic. */
  DEXTERITY("DEXTÉRITÉ", false, true, true, true),
  /** Perception characteristic. */
  PERCEPTION("PERCEPTION", false, false, true, false),
  /** Sight characteristic. */
  SIGHT("VUE", false, true, false, true),
  /** Hearing characteristic. */
  HEARING("OUïE", false, true, false, true),
  /** Smell and Taste characteristic. */
  SMELL_TASTE("ODORAT-GOûT", false, true, false, true),
  /** Willpower characteristic. */
  WILLPOWER("VOLONTÉ", false, true, true, true),
  /** Intellect characteristic. */
  INTELLECT("INTELLECT", false, true, true, true),
  /** Empathy characteristic. */
  EMPATHY("EMPATHIE", false, true, true, true),
  /** Dream characteristic. */
  DREAM("RÊVE", false, true, true, true),
  /** Luck characteristic. */
  LUCK("CHANCE", false, true, true, true),
  /** Melee characteristic. */
  MELEE("Mêlée", true, true, true, false),
  /** Shoot characteristic. */
  SHOOT("Tir", true, true, false, false),
  /** Throw characteristic. */
  THROW("Lancer", true, true, false, false),
  /** Stealth characteristic. */
  STEALTH("Dérobée", true, true, true, false);

  /**
   * Map for enum conversion.
   */
  private static final Map<String, CharacteristicEnum> CHARACTERISTICS = new HashMap<>(
      CharacteristicEnum.values().length);
  static {
    for (final CharacteristicEnum charEnum : CharacteristicEnum.values()) {
      CHARACTERISTICS.put(charEnum.code, charEnum);
    }
  }

  /** Code for the enum. */
  private final String code;

  /** Derived value indicator. */
  private final boolean derived;

  /** character indicator. */
  private final boolean character;

  /** animal indicator. */
  private final boolean animal;

  /** experience indicator. */
  private final boolean experience;

  /**
   * Return the enum corresponding to the code.
   *
   * @param code
   *          Code of the enum
   * @return the enum.
   */
  public static CharacteristicEnum get(final String code) {
    return CHARACTERISTICS.get(code);
  }

}
