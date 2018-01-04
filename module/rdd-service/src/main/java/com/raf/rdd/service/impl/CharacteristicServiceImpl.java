package com.raf.rdd.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.service.AbstractService;
import com.raf.fwk.util.aop.Loggable;
import com.raf.rdd.jpa.dao.CharacteristicDao;
import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.domain.character.CharValue;
import com.raf.rdd.jpa.domain.character.CharValuePk;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.enums.CharacteristicEnum;
import com.raf.rdd.service.CharacteristicService;

import lombok.NoArgsConstructor;

/**
 * Service implementation for {@link CharacteristicService}.
 *
 * @author RAF
 */
@Service
@NoArgsConstructor
public final class CharacteristicServiceImpl extends AbstractService<CharacteristicDao, Characteristic, String>
    implements CharacteristicService {

  /**
   * Initialise the set of characteristics for a figure.
   *
   * @param figure
   *          the figure
   * @return the set of characteristics
   * @see CharacteristicService#initCharacteristic()
   */
  @Override
  @Transactional
  @Loggable
  public Set<CharValue> initCharacteristics(final Figure figure) {
    final Set<CharValue> values = new LinkedHashSet<>();
    CharValue charValue;
    Characteristic characteristic;
    for (final CharacteristicEnum charEnum : CharacteristicEnum.values()) {
      if (charEnum.isCharacter() && !charEnum.isDerived()) {
        characteristic = getEntityDao().getById(charEnum.getCode());
        charValue = create(characteristic, figure);
        values.add(charValue);
      }
    }
    return values;
  }

  /**
   * Create a new chararteristic value whith base value.
   *
   * @param characteristic
   *          the characteristic
   * @param figure
   *          the figure
   * @return the created chararteristic value
   */
  private CharValue create(final Characteristic characteristic, final Figure figure) {
    final CharValuePk charValuePk = new CharValuePk(figure.getIdentifier(), characteristic.getIdentifier());
    final CharValue charValue = new CharValue();
    charValue.setIdentifier(charValuePk);
    charValue.setCharacteristic(characteristic);
    charValue.setFigure(figure);
    charValue.setValue(BASE_VALUE);
    return charValue;
  }

}
