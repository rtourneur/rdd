package com.raf.rdd.jpa.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the ARMOR database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "ARMOR", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Armor extends AbstractItem {

  /** Serial UID. */
  private static final long serialVersionUID = -787589646456628322L;

  /** The protection value. */
  @Column(name = "PROTECTION", nullable = false, precision = 1)
  private int protection;

  /** The malus. */
  @Column(name = "MALUS", nullable = false, precision = 1)
  private int malus;

  /**
   * Append the properties for the to string builder.
   *
   * @param builder
   *          the builder
   * @see AbstractItem#appendItem(ToStringBuilder)
   */
  @Override
  protected final void appendItem(final ToStringBuilder builder) {
    builder.append("protection", this.protection).append("malus", this.malus);
  }

}
