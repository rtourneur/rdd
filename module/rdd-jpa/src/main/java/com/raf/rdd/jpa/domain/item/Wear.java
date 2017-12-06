package com.raf.rdd.jpa.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the WEAR database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "WEAR", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Wear extends AbstractItem {

  /** Serial UID. */
  private static final long serialVersionUID = -4149598086198685883L;

  /** The armor indicator. */
  @Column(name = "ARMOR", nullable = false)
  private boolean armor;

  /** The encumbrance count indicator when wearing. */
  @Column(name = "ENC_COUNT", nullable = false)
  private boolean encCount;

  /** The localization for wearing items. */
  @Column(name = "LOCALIZATION", nullable = false, precision = 2)
  private int localization;

  /**
   * Append the properties for the to string builder.
   *
   * @param builder
   *          the builder
   * @see AbstractItem#appendItem(ToStringBuilder)
   */
  @Override
  protected final void appendItem(final ToStringBuilder builder) {
    builder.append("armor", this.armor).append("encCount", this.encCount).append("localization", this.localization);
  }

}
