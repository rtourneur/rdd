package com.raf.rdd.jpa.domain.character;

import static com.raf.fwk.util.CalculUtils.average;

import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.raf.fwk.jpa.domain.AbstractIdEntity;
import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.enums.CharacteristicEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the FIGURE database table.
 *
 * @author RAF
 */
@Entity
@Table(name = "FIGURE", schema = "RDD")
@Getter
@Setter
@NoArgsConstructor
public class Figure extends AbstractIdEntity {

  /** Serial UID. */
  private static final long serialVersionUID = 928198246094267685L;

  /** The name. */
  @Column(name = "NAME", length = 50, nullable = false)
  private String name;

  /** The High-Dreamer indicator. */
  @Column(name = "DREAMER", nullable = false)
  private boolean dreamer;

  /** The person. */
  @Embedded
  private Person person;

  /** The breed name. */
  @Column(name = "BREED", length = 30, nullable = false)
  private String breedName;

  /** The breed. */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BREED", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_FIGURE_BREED"))
  private Breed breed;

  /** The characteristics. */
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "FIGURE_ID", referencedColumnName = "ID")
  private Set<CharValue> charValues;

  /** The skills. */
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "FIGURE_ID", referencedColumnName = "ID")
  private Set<SkillValue> skillValues;

  /** The wealth in deniers. */
  @Column(name = "WEALTH", nullable = false, precision = 6)
  private int wealth;

  /** The derived characteristics. */
  @Transient
  private Set<DerivedValue> derivedValue;

  /** The threshold. */
  @Transient
  private Threshold threshold;

  /**
   * Calculate the values post loading.
   */
  @PostLoad
  public void calculateValues() {
    this.derivedValue = new LinkedHashSet<>();
    this.threshold = new Threshold();
    final Map<CharacteristicEnum, Integer> values = new EnumMap<>(CharacteristicEnum.class);

    this.charValues.forEach(charValue -> {
      final Characteristic characteristic = charValue.getCharacteristic();
      if (characteristic != null) {
        values.put(characteristic.getCharacteristic(), Integer.valueOf(charValue.getValue()));
      }
    });
    caculateDerived(values);
    // Health
    this.threshold.caculate(values);
  }

  /**
   * Append the object values for the to string builder.
   *
   * @param builder
   *          the to string builder
   * @see AbstractIdEntity#appendId(ToStringBuilder)
   */
  @Override
  protected final void appendId(final ToStringBuilder builder) {
    builder.append("name", this.name).append("breedName", this.breedName).append("dreamer", this.dreamer)
        .append(this.person).append("characteristics", this.charValues).append("derived", this.derivedValue)
        .append("threshold", this.threshold).append("skills", this.skillValues).append("wealth", this.wealth);
  }

  /**
   * Calculates the derived characteristics.
   *
   * @param values
   *          the map of characteristics
   */
  private void caculateDerived(final Map<CharacteristicEnum, Integer> values) {
    // MELEE
    final DerivedValue melee = new DerivedValue();
    melee.setCharacteristic(CharacteristicEnum.MELEE);
    melee.setValue(average(values.get(CharacteristicEnum.STRENGTH), values.get(CharacteristicEnum.AGILITY)));
    this.derivedValue.add(melee);
    // SHOOT
    final DerivedValue shoot = new DerivedValue();
    shoot.setCharacteristic(CharacteristicEnum.SHOOT);
    shoot.setValue(average(values.get(CharacteristicEnum.SIGHT), values.get(CharacteristicEnum.DEXTERITY)));
    this.derivedValue.add(shoot);
    // THROW
    final DerivedValue throwing = new DerivedValue();
    throwing.setCharacteristic(CharacteristicEnum.THROW);
    throwing.setValue(average(shoot.getValue(), values.get(CharacteristicEnum.STRENGTH)));
    this.derivedValue.add(throwing);
    // STEALTH
    final DerivedValue stealth = new DerivedValue();
    stealth.setCharacteristic(CharacteristicEnum.STEALTH);
    final Integer stature = values.get(CharacteristicEnum.STATURE);
    if (stature != null) {
      stealth.setValue(average(21 - stature.intValue(), values.get(CharacteristicEnum.AGILITY)));
    }
    this.derivedValue.add(stealth);
  }

}
