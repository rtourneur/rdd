package com.raf.rdd.jpa.domain.skill;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractNamedEntity;
import com.raf.rdd.jpa.enums.SkillTypeConverter;
import com.raf.rdd.jpa.enums.SkillTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the SKILL TYPE database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "SKILL_TYPE", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class SkillType extends AbstractNamedEntity {

  /** Serial UID. */
  private static final long serialVersionUID = -1456973384751826619L;

  /** Enum value. */
  @Convert(converter = SkillTypeConverter.class)
  @Column(name = "NAME", insertable = false, updatable = false)
  private SkillTypeEnum skillType;

  /**
   * Append object values for the toString.
   *
   * @param builder
   *          the to string builder
   * @see AbstractNamedEntity#appendNamed(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    builder.append("SkillType", this.skillType);

  }

}
