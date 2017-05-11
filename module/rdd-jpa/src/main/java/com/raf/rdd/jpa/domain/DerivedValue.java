package com.raf.rdd.jpa.domain;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.rdd.jpa.enums.CharacteristicEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for derived values.
 * 
 * @author RAF
 */
@Getter
@Setter
@NoArgsConstructor
public class DerivedValue implements Serializable {

  /** Serial UID. */
  private static final long serialVersionUID = -4405593465604713095L;

  /** The derived characteristic. */
  private CharacteristicEnum characteristic;

  /** The value. */
  private int value;

  /**
   * Return the string representation for this object.
   * 
   * @see Object#toString()
   */
  @Override
  public final String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    builder.append("characteristic", this.characteristic).append("value", this.value);
    return builder.toString();
  }

}
