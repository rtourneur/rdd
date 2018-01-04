package com.raf.rdd.jpa.domain.character;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractEntity;
import com.raf.fwk.jpa.domain.DomainEntity;
import com.raf.rdd.jpa.domain.Characteristic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the CHARACTERISTIC_VALUE database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "CHARACTERISTIC_VALUE", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "identifier", callSuper = false)
public class CharValue extends AbstractEntity implements DomainEntity<CharValuePk>, Comparable<CharValue> {

  /** Serial UID. */
  private static final long serialVersionUID = 6407723377668612038L;

  /** The identifier. */
  @EmbeddedId
  private CharValuePk identifier;

  /** The figure. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FIGURE_ID", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CHARAC_VALUE_FIGURE"))
  private Figure figure;

  /** The characteristic. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CHARACTERISTIC", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_CHARAC_VALUE_CHARACTERISTIC"))
  private Characteristic characteristic;

  /** The value. */
  @Column(name = "VALUE", nullable = false, precision = 2)
  private int value;

  /** The current value. */
  @Column(name = "CURRENT_VALUE", nullable = false, precision = 2)
  private int current;

  /** The experience points. */
  @Column(name = "EXPERIENCE", nullable = false, precision = 2, columnDefinition = "INT default 0")
  private int experience;

  /**
   * Compare the characteristic values using the rank in the characteristic.
   *
   * @param charValue
   *          the characteristic value to compare
   * @see Comparable#compareTo(Object)
   */
  @Override
  public final int compareTo(final CharValue charValue) {
    return this.characteristic.compareTo(charValue.characteristic);
  }

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   * @see AbstractEntity#append(ToStringBuilder)
   */
  @Override
  protected void append(final ToStringBuilder builder) {
    builder.append("identifier", this.identifier);
    if (this.characteristic != null && Characteristic.class.equals(this.characteristic.getClass())) {
      builder.append("characteristic", this.characteristic);
    }
    builder.append("value", this.value).append("current", this.current).append("experience", this.experience);
  }

}
