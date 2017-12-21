package com.raf.rdd.jpa.domain.breed;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.rdd.jpa.domain.Characteristic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The embeddable class for the BREED_CHARAC database table.
 *
 * @author RAF
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BreedCharac implements Serializable, Comparable<BreedCharac> {

  /** Serial UID. */
  private static final long serialVersionUID = 6984559951156074320L;

  /** The characteristic. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CHARACTERISTIC", nullable = false)
  private Characteristic characteristic;

  /** The modifier value. */
  @Column(name = "MODIFIER", nullable = false, precision = 1)
  private int modifier;

  /** The limit value. */
  @Column(name = "LIMIT", precision = 2)
  private Integer limit;

  /**
   * Compare the characteristic values using the rank in the characteristic.
   *
   * @param charValue
   *          the characteristic value to compare
   * @see Comparable#compareTo(Object)
   */
  @Override
  public final int compareTo(final BreedCharac charValue) {
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
    builder.append("modifier", this.modifier).append("limit", this.limit);
    return builder.toString();
  }

}
