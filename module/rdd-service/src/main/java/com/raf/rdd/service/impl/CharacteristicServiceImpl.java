package com.raf.rdd.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.raf.fwk.service.AbstractService;
import com.raf.fwk.service.ServiceException;
import com.raf.fwk.util.aop.Loggable;
import com.raf.rdd.jpa.dao.CharacteristicDao;
import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.domain.character.CharValue;
import com.raf.rdd.jpa.enums.CharacteristicEnum;
import com.raf.rdd.service.CharacteristicService;

/**
 * Service implementation for {@link CharacteristicService}.
 * 
 * @author RAF
 */
@Service
public final class CharacteristicServiceImpl extends AbstractService<CharacteristicDao, Characteristic, String>
    implements CharacteristicService {

  /** The base value for characteristics. */
  private static final int BASE_VALUE = 6;

  /**
   * Initialise the set of characteristics for a character.
   * 
   * @return the set of characteristics
   * @see CharacteristicService#initCharacteristic()
   */
  @Override
  @Loggable
  public Set<CharValue> initCharacteristic() {
    final Set<CharValue> values = new LinkedHashSet<>();
    CharValue charValue;
    Characteristic characteristic;
    for (final CharacteristicEnum charEnum : CharacteristicEnum.values()) {
      if (charEnum.isCharacter() && !charEnum.isDerived()) {
        characteristic = getEntityDao().getById(charEnum.getCode());
        charValue = create(characteristic);
        values.add(charValue);
      }
    }
    return values;
  }

  /**
   * Return the cost of the level for the value.
   * 
   * @param value
   *          the value
   * @return the cost
   * @throws ServiceException
   *           when the value is out of range
   */
  public int getLevelCost(final int value) throws ServiceException {
    if (value <= BASE_VALUE) {
      throw new ServiceException("error.value.toolow", Integer.valueOf(value));
    }

    final int cost;
    switch (value) {
      case 7:
      case 8:
        cost = 6;
        break;
      case 9:
      case 10:
        cost = 7;
        break;
      case 11:
      case 12:
        cost = 8;
        break;
      case 13:
      case 14:
        cost = 9;
        break;
      case 15:
        cost = 10;
        break;
      case 16:
        cost = 20;
        break;
      case 17:
        cost = 30;
        break;
      case 18:
        cost = 40;
        break;
      default:
        cost = 50;
        break;
    }
    return cost;
  }

  /**
   * Create a new chararteristic value whith base value.
   * 
   * @param characteristic
   *          the characteristic
   * @return the created chararteristic value
   */
  private CharValue create(final Characteristic characteristic) {
    final CharValue charValue = new CharValue();
    charValue.setCharacteristic(characteristic);
    charValue.setValue(BASE_VALUE);
    return charValue;
  }

}
