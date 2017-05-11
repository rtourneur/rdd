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
  private static final Map<String, GenderEnum> GENDERS = new HashMap<>(
      GenderEnum.values().length);
  static {
    for (GenderEnum genderEnum : GenderEnum.values()) {
      GENDERS.put(genderEnum.name, genderEnum);
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
  public static GenderEnum get(final String name) {
    return GENDERS.get(name);
  }

}
