package com.raf.rdd.jpa.domain.item;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractIdEntity;
import com.raf.fwk.jpa.domain.AbstractNamedEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract super class for all item entities.
 *
 * @author RAF
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractItem extends AbstractNamedEntity {

  /** Serial UID. */
  private static final long serialVersionUID = -313154647426569768L;

  /** The name of the item type. */
  @Column(name = "ITEM_TYPE", nullable = false, length = 30)
  private String itemTypeName;

  /** The embeddable generic item. */
  @Embedded
  private GenericItem genericItem;

  /** The item type. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ITEM_TYPE", insertable = false, updatable = false)
  private ItemType itemType;

  /**
   * Append the properties for the to string builder.
   *
   * @param builder
   *          the builder
   * @see AbstractIdEntity#appendId(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    builder.append("itemTypeName", this.itemTypeName).append(this.genericItem);
    appendItem(builder);
    if (this.itemType != null && ItemType.class.equals(this.itemType.getClass())) {
      builder.append("itemType", this.itemType);
    }
  }

  /**
   * Append the properties for the to string builder.
   *
   * @param builder
   *          the builder
   */
  protected void appendItem(final ToStringBuilder builder) {
    // RAS
  }
}
