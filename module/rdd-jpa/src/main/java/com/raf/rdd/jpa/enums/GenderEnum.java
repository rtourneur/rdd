package com.raf.rdd.jpa.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for gender.
 *
 * @author RAF
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum {
  /** The Male gender. */
  MALE("M"),
  /** The Female gender. */
  FEMALE("F");

  /**
   * Map for enum conversion.
   */
  private static final Map<String, GenderEnum> GENDERS = new HashMap<>(GenderEnum.values().length);
  static {
    for (final GenderEnum genderEnum : GenderEnum.values()) {
      GENDERS.put(genderEnum.code, genderEnum);
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
  public static GenderEnum get(final String code) {
    return GENDERS.get(code);
  }

}
