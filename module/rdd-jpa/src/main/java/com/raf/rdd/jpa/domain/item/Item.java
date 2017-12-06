package com.raf.rdd.jpa.domain.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the ITEM database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "ITEM", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractItem {

  /** Serial UID. */
  private static final long serialVersionUID = 133907495438038214L;

  /** The container indicator. */
  @Column(name = "CONTAINER", nullable = false)
  private boolean container;

  /** The scalable indicator. */
  @Column(name = "SCALABLE", nullable = false)
  private boolean scalable;

  /**
   * Append the properties for the to string builder.
   *
   * @param builder
   *          the builder
   * @see AbstractItem#appendItem(ToStringBuilder)
   */
  @Override
  protected final void appendItem(final ToStringBuilder builder) {
    builder.append("container", this.container).append("scalable", this.scalable);
  }

}
