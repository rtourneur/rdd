package com.raf.rdd.jpa.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractIdEntity;

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
@NoArgsConstructor
public class AbstractItem extends AbstractIdEntity {

  /** Serial UID. */
  private static final long serialVersionUID = -313154647426569768L;

  /** The name. */
  @Column(name = "NAME", nullable = false, length = 30)
  private String name;

  /** The message code. */
  @Column(name = "MESSAGE_CODE", nullable = false, length = 40)
  private String messageCode;

  /** The name of the item type. */
  @Column(name = "ITEM_TYPE", nullable = false, length = 30)
  private String itemTypeName;

  /** The item type. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NAME", insertable = false, updatable = false)
  private ItemType itemType;

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   * @see AbstractIdEntity#appendId(ToStringBuilder)
   */
  @Override
  protected final void appendId(final ToStringBuilder builder) {
    builder.append("name", this.name).append("messageCode", this.messageCode).append("itemTypeName", this.itemTypeName);
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
