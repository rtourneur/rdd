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
  STATURE("TAILLE", false),
  /** Appearance characteristic. */
  APPEARANCE("APPARENCE", false),
  /** Constitution characteristic. */
  CONSTITUTION("CONSTITUTION", false),
  /** Strength characteristic. */
  STRENGTH("FORCE", false),
  /** Agility characteristic. */
  AGILITY("AGILITÉ", false),
  /** Dexterity characteristic. */
  DEXTERITY("DEXTÉRITÉ", false),
  /** Perception characteristic. */
  PERCEPTION("PERCEPTION", false),
  /** Sight characteristic. */
  SIGHT("VUE", false),
  /** Hearing characteristic. */
  HEARING("OUïE", false),
  /** Smell and Taste characteristic. */
  SMELL_TASTE("ODORAT-GOûT", false),
  /** Willpower characteristic. */
  WILLPOWER("VOLONTÉ", false),
  /** Intellect characteristic. */
  INTELLECT("INTELLECT", false),
  /** Empathy characteristic. */
  EMPATHY("EMPATHIE", false),
  /** Dream characteristic. */
  DREAM("RÊVE", false),
  /** Luck characteristic. */
  LUCK("CHANCE", false),
  /** Melee characteristic. */
  MELEE("Mêlée", true),
  /** Shoot characteristic. */
  SHOOT("Tir", true),
  /** Throw characteristic. */
  THROW("Lancer", true),
  /** Stealth characteristic. */
  STEALTH("Dérobée", true);

  /**
   * Map for enum conversion.
   */
  private static final Map<String, CharacteristicEnum> CHARACTERISTICS = new HashMap<>(
      CharacteristicEnum.values().length);
  static {
    for (CharacteristicEnum characteristicEnum : CharacteristicEnum.values()) {
      CHARACTERISTICS.put(characteristicEnum.name, characteristicEnum);
    }
  }

  /** Name for the enum. */
  private final String name;

  /** Derived value indicator. */
  private final boolean derived;

  /**
   * Return the enum corresponding to the name.
   * 
   * @param name
   *          Name of the enum
   * @return the enum.
   */
  public static CharacteristicEnum get(final String name) {
    return CHARACTERISTICS.get(name);
  }

}
