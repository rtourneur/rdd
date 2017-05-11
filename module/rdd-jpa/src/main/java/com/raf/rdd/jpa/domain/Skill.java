package com.raf.rdd.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractNamedEntity;
import com.raf.rdd.jpa.enums.SkillTypeConverter;
import com.raf.rdd.jpa.enums.SkillTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the SKILL database table.
 * 
 * @author RAF
 */
@Entity
@Table(name = "SKILL", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Skill extends AbstractNamedEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 354787711505124943L;

  /** The description code for internationalisation. */
  @Column(name = "DESCRIPTION_CODE", nullable = false, length = 40)
  private String descriptionCode;

  /** The skill type enum. */
  @Column(name = "SKILL_TYPE", length = 30, nullable = false)
  @Convert(converter = SkillTypeConverter.class)
  private SkillTypeEnum skillTypeEnum;

  /** The rule. */
  @Column(name = "RULE", length = 30)
  private String rule;

  /** The group. */
  @Column(name = "TRUNK", length = 30)
  private String group;

  /** The skill type. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SKILL_TYPE", insertable = false, updatable = false)
  private SkillType skillType;

  /**
   * Append the properties for the to string builder.
   * 
   * @param builder
   *          the builder
   * 
   * @see AbstractNamedEntity#appendNamed(ToStringBuilder)
   */
  @Override
  protected final void appendNamed(final ToStringBuilder builder) {
    builder.append("descriptionCode", this.descriptionCode).append("skillTypeEnum", this.skillTypeEnum)
        .append("rule", this.rule).append("group", this.group);
    if (this.skillType != null && SkillType.class.equals(this.skillType.getClass())) {
      builder.append("skillType", this.skillType);
    }
  }

}
