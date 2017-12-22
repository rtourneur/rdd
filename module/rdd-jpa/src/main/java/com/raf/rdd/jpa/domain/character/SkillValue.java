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
import com.raf.rdd.jpa.domain.skill.Skill;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the SKILL_VALUE database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "SKILL_VALUE", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "identifier", callSuper = false)
public class SkillValue extends AbstractEntity implements DomainEntity<SkillValuePk> {

  /** Serial UID. */
  private static final long serialVersionUID = 6407723377668612038L;

  /** The identifier. */
  @EmbeddedId
  private SkillValuePk identifier;

  /** The character. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FIGURE_ID", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_SKILL_VALUE_FIGURE"))
  private Figure character;

  /** The characteristic. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SKILL", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_SKILL_VALUE_SKILL"))
  private Skill skill;

  /** The value. */
  @Column(name = "VALUE", nullable = false, precision = 2)
  private int value;

  /** The experience points. */
  @Column(name = "EXPERIENCE", nullable = false, precision = 3)
  private int experience;

  /** The spell experience points. */
  @Column(name = "SPELL", nullable = false, precision = 3)
  private int spell;

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
    if (this.skill != null && Skill.class.equals(this.skill.getClass())) {
      builder.append("skill", this.skill);
    }
    builder.append("value", this.value).append("experience", this.experience).append("spell", this.spell);
  }

}
