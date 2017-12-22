package com.raf.rdd.jpa.domain.item;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.rdd.jpa.domain.skill.Skill;
import com.raf.rdd.jpa.enums.HandWeaponConverter;
import com.raf.rdd.jpa.enums.HandWeaponEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the WEAPON database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "WEAPON", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Weapon extends AbstractItem {

  /** Serial UID. */
  private static final long serialVersionUID = -845226188421409546L;

  /** The skill name for 1 hand. */
  @Column(name = "SKILL_1", length = 30)
  private String skillName1;

  /** The skill name for 2 hands. */
  @Column(name = "SKILL_2", length = 30)
  private String skillName2;

  /** The hands. */
  @Convert(converter = HandWeaponConverter.class)
  @Column(name = "HAND", nullable = false, length = 12)
  private HandWeaponEnum handWeapon;

  /** The resistance. */
  @Column(name = "RES", precision = 2)
  private Integer res;

  /** The damage bonus for 1 hand. */
  @Column(name = "BONUS_DOM_1", precision = 1)
  private Integer bonusDom1;

  /** The damage bonus for 2 hand. */
  @Column(name = "BONUS_DOM_2", precision = 1)
  private Integer bonusDom2;

  /** The minimum strength for 1 hand. */
  @Column(name = "STRENGTH_1", precision = 2)
  private Integer strength1;

  /** The minimum strength for 2 hand. */
  @Column(name = "STRENGTH_2", precision = 2)
  private Integer strength2;

  /** The non lethal indicator. */
  @Column(name = "NON_LETHAL", nullable = false)
  private boolean nonlethal;

  /** The skill for 1 hand. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SKILL_1", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_WEAPON_SKILL_1"))
  private Skill skill1;

  /** The skill for 2 hands. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SKILL_2", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_WEAPON_SKILL_2"))
  private Skill skill2;

  /**
   * Append the properties for the to string builder.
   *
   * @param builder
   *          the builder
   * @see AbstractItem#appendItem(ToStringBuilder)
   */
  @Override
  protected final void appendItem(final ToStringBuilder builder) {
    builder.append("skillName1", this.skillName1).append("skillName2", this.skillName2)
        .append("handWeapon", this.handWeapon).append("res", this.res).append("bonusDom1", this.bonusDom1)
        .append("bonusDom2", this.bonusDom2).append("strength1", this.strength1).append("strength2", this.strength2)
        .append("nonlethal", this.nonlethal);
    if (this.skill1 != null && Skill.class.equals(this.skill1.getClass())) {
      builder.append("skill1", this.skill1);
    }
    if (this.skill2 != null && Skill.class.equals(this.skill2.getClass())) {
      builder.append("skill2", this.skill2);
    }
  }

}
