package com.raf.rdd.jpa.domain;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The embeddable class for the CHARACTERISTIC_VALUE database table.
 * 
 * @author RAF
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CharValue implements Serializable, Comparable<CharValue> {

  /** Serial UID. */
  private static final long serialVersionUID = 6407723377668612038L;

  /** The characteristic. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CHARACTERISTIC", nullable = false)
  private Characteristic characteristic;

  /** The value. */
  @Column(name = "VALUE", nullable = false, precision = 2)
  private int value;

  /** The experience points. */
  @Column(name = "EXPERIENCE", nullable = false, precision = 2)
  private int experience;

  /**
   * Compare the characteristic values using the rank in the characteristic.
   * 
   * @param charValue
   *          the characteristic value to compare
   * 
   * @see Comparable#compareTo(Object)
   */
  @Override
  public final int compareTo(final CharValue charValue) {
    return CompareToBuilder.reflectionCompare(this.characteristic, charValue.characteristic);
  }

  /**
   * Return the string representation for this object.
   * 
   * @see Object#toString()
   */
  @Override
  public final String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    if (this.characteristic != null && Characteristic.class.equals(this.characteristic.getClass())) {
      builder.append("characteristic", this.characteristic);
    }
    builder.append("value", this.value).append("experience", this.experience);
    return builder.toString();
  }

}
