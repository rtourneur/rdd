package com.raf.rdd.jpa.domain;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.rdd.jpa.domain.skill.Skill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The embeddable class for the SKILL_VALUE database table.
 *
 * @author RAF
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class SkillValue implements Serializable {

  /** Serial UID. */
  private static final long serialVersionUID = 6407723377668612038L;

  /** The characteristic. */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "SKILL", nullable = false)
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
   * Return the string representation for this object.
   *
   * @see Object#toString()
   */
  @Override
  public final String toString() {
    final ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
    if (this.skill != null && Skill.class.equals(this.skill.getClass())) {
      builder.append("skill", this.skill);
    }
    builder.append("value", this.value).append("experience", this.experience).append("spell", this.spell);
    return builder.toString();
  }

}
