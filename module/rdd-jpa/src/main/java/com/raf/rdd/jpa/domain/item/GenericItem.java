package com.raf.rdd.jpa.domain.item;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Gereric infos for items.
 *
 * @author RAF
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class GenericItem implements Serializable {

  /** Serial UID. */
  private static final long serialVersionUID = -2666426619359765191L;

  /** Encumbrance of the item, 1 pt => 2 kg. Can be null. */
  @Column(name = "ENC", precision = 3, scale = 1)
  private BigDecimal encumbrance;

  /** Min price of the item (or default price) in "deniers". Can be null. */
  @Column(name = "PRIX_MIN", precision = 6)
  private Integer minPrice;

  /** Max price of the item (if any) in "deniers". Often null. */
  @Column(name = "PRIX_MAX", precision = 6)
  private Integer maxPrice;

  /**
   * Return the string representation for this object.
   *
   * @see Object#toString()
   */
  @Override
  public String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    builder.append("encumbrance", notNull(this.encumbrance)).append("minPrice", notNull(this.minPrice));
    if (this.maxPrice != null) {
      builder.append("maxPrice", this.maxPrice);
    }
    return builder.toString();
  }

  private Object notNull(final Object value) {
    final Object notNull;
    if (value == null) {
      notNull = "-";
    } else {
      notNull = value;
    }
    return notNull;
  }

}
