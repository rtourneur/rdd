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
  STATURE("TAILLE", false, true, true),
  /** Appearance characteristic. */
  APPEARANCE("APPARENCE", false, true, true),
  /** Constitution characteristic. */
  CONSTITUTION("CONSTITUTION", false, true, true),
  /** Strength characteristic. */
  STRENGTH("FORCE", false, true, true),
  /** Agility characteristic. */
  AGILITY("AGILITÉ", false, true, true),
  /** Dexterity characteristic. */
  DEXTERITY("DEXTÉRITÉ", false, true, true),
  /** Perception characteristic. */
  PERCEPTION("PERCEPTION", false, false, true),
  /** Sight characteristic. */
  SIGHT("VUE", false, true, false),
  /** Hearing characteristic. */
  HEARING("OUïE", false, true, false),
  /** Smell and Taste characteristic. */
  SMELL_TASTE("ODORAT-GOûT", false, true, false),
  /** Willpower characteristic. */
  WILLPOWER("VOLONTÉ", false, true, true),
  /** Intellect characteristic. */
  INTELLECT("INTELLECT", false, true, true),
  /** Empathy characteristic. */
  EMPATHY("EMPATHIE", false, true, true),
  /** Dream characteristic. */
  DREAM("RÊVE", false, true, true),
  /** Luck characteristic. */
  LUCK("CHANCE", false, true, true),
  /** Melee characteristic. */
  MELEE("Mêlée", true, true, true),
  /** Shoot characteristic. */
  SHOOT("Tir", true, true, false),
  /** Throw characteristic. */
  THROW("Lancer", true, true, false),
  /** Stealth characteristic. */
  STEALTH("Dérobée", true, true, true);

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
  
  /** character indicator. */
  private final boolean character;
  
  /** animal indicator. */
  private final boolean animal;

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
