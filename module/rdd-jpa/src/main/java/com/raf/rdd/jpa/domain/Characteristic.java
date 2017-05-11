package com.raf.rdd.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractNamedEntity;
import com.raf.rdd.jpa.enums.CharacteristicConverter;
import com.raf.rdd.jpa.enums.CharacteristicEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the CHARACTERISTIC database table.
 * 
 * @author RAF
 */
@Entity
@Table(name = "CHARACTERISTIC", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Characteristic extends AbstractNamedEntity
    implements Comparable<Characteristic> {

  /** Serial UID. */
  private static final long serialVersionUID = -1552039760014328014L;

  /** Enum value. */
  @Convert(converter = CharacteristicConverter.class)
  @Column(name = "NAME", insertable = false, updatable = false)
  private CharacteristicEnum characteristic;

  /** The rank for display. */
  @Column(name = "RANK", nullable = false, precision = 2)
  private int rank;

  /**
   * Compare the characteristics using the rank.
   * 
   * @param compare
   *          the characteristic to compare
   * 
   * @see Comparable#compareTo(Object)
   */
  @Override
  public final int compareTo(final Characteristic compare) {
    return this.rank - compare.rank;
  }

  /**
   * Append object values for the toString.
   * 
   * @param builder
   *          the to string builder
   * 
   * @see AbstractNamedEntity#appendNamed(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    builder.append("characteristic", this.characteristic).append("rank", this.rank);
  }

}
