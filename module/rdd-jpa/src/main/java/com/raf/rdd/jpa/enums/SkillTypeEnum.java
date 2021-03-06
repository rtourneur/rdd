package com.raf.rdd.jpa.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for Skill types.
 *
 * @author RAF
 */
@Getter
@RequiredArgsConstructor
public enum SkillTypeEnum {

  /** The General skills. */
  GENERAL("Général", -4),
  /** The Melee combat skills. */
  MELEE_COMBAT("Mêlée", -6),
  /** The Specific Skills. */
  SPECIFIC("Particulière", -8),
  /** The Shooting and Throwing skills. */
  SHOOT_THROW("Tir et Lancer", -8),
  /** The Specialist skills. */
  SPECIALIST("Spécialisée", -11),
  /** The Knowledge. */
  KNOWLEDGE("Connaissance", -11),
  /** The Draconic. */
  DRACONIC("Draconic", -11);

  /**
   * Map for enum conversion.
   */
  private static final Map<String, SkillTypeEnum> SKILL_TYPES = new HashMap<>(SkillTypeEnum.values().length);
  static {
    for (final SkillTypeEnum skillTypeEnum : SkillTypeEnum.values()) {
      SKILL_TYPES.put(skillTypeEnum.code, skillTypeEnum);
    }
  }

  /** Code for the enum. */
  private final String code;

  /** Base value. */
  private final int base;

  /**
   * Return the enum corresponding to the code.
   *
   * @param code
   *          Name of the enum
   * @return the enum.
   */
  public static SkillTypeEnum get(final String code) {
    return SKILL_TYPES.get(code);
  }

}
