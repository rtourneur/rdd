package com.raf.rdd.jpa.domain;

import java.io.Serializable;
import java.util.Map;

import com.raf.rdd.jpa.enums.CharacteristicEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for thresholds.
 * 
 * @author RAF
 *
 */
@Getter
@Setter
@NoArgsConstructor
public final class Threshold implements Serializable {

  /** The health value. */
  private int health;

  /** The stamina value. */
  private int stamina;
  
  /** The constitution threshold. */
  private int constThreshold;

  /** The sustentation threshold. */
  private int sustThreshold;
  
  /** The damage bonus. */
  private int bonusDom;

  public void caculate(final Map<CharacteristicEnum, Integer> values) {
    final int stature = getValue(values.get(CharacteristicEnum.STATURE));
    final int constitution = getValue(values.get(CharacteristicEnum.CONSTITUTION));
    final int willpower = getValue(values.get(CharacteristicEnum.WILLPOWER));
    final int strength = getValue(values.get(CharacteristicEnum.STRENGTH));
    this.health = (constitution + stature + 1) / 2;
    this.stamina = Math.max(constitution + stature, this.health + willpower);
    this.constThreshold = constitution / 3;
    this.sustThreshold = (stature + 2) / 4;
    this.bonusDom = (stature + strength) / 8 - 2;

  }

  private int getValue(final Integer integer) {
    int value = 0;
    if (integer != null) {
      value = integer.intValue();
    }
    return value;
  }
}
