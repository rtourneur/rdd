package com.raf.rdd.jpa.enums;

import javax.persistence.AttributeConverter;

/**
 * Converter class for {@link GenderEnum}.
 *
 * @author RAF
 */
public final class GenderConverter implements AttributeConverter<GenderEnum, String> {

  /**
   * Return the toString value for the enum.
   *
   * @param attribute
   *          the enum attribute
   * @throws IllegalArgumentException
   *           if enum attribute is null
   * @see AttributeConverter#convertToDatabaseColumn(Object)
   */
  @Override
  public String convertToDatabaseColumn(final GenderEnum attribute) {
    if (attribute == null) {
      throw new IllegalArgumentException("GenderEnum is null");
    }
    return attribute.toString();
  }

  /**
   * Return the enum found in the enum map from the provided value.
   *
   * @param name
   *          the provided value for the name
   * @throws IllegalArgumentException
   *           if enum is not found
   * @see AttributeConverter#convertToEntityAttribute(Object)
   */
  @Override
  public GenderEnum convertToEntityAttribute(final String name) {
    final GenderEnum value = GenderEnum.get(name);
    if (value == null) {
      throw new IllegalArgumentException("GenderEnum not found for " + name);
    }
    return value;
  }

}
