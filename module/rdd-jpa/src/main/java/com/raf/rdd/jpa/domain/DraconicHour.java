package com.raf.rdd.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractNamedEntity;
import com.raf.rdd.jpa.enums.DraconicHourConverter;
import com.raf.rdd.jpa.enums.DraconicHourEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the DRACONIC HOUR database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "DRACONIC_HOUR", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class DraconicHour extends AbstractNamedEntity {

  /** Serial UID. */
  private static final long serialVersionUID = -1456973384751826619L;

  /** Enum value. */
  @Convert(converter = DraconicHourConverter.class)
  @Column(name = "NAME", insertable = false, updatable = false)
  private DraconicHourEnum draconicHour;

  /** The description code for internationalisation. */
  @Column(name = "DESCRIPTION_CODE", nullable = false, length = 40)
  private String descriptionCode;

  /** The icon. */
  @Column(name = "ICON", nullable = false, length = 30)
  private String icon;

  /**
   * Append object values for the toString.
   *
   * @param builder
   *          the to string builder
   * @see AbstractNamedEntity#appendNamed(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    builder.append("hour", this.draconicHour).append("descriptionCode", this.descriptionCode).append("icon", this.icon);

  }

}
