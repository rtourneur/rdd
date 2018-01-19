package com.raf.rdd.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.service.AbstractService;
import com.raf.fwk.util.aop.Loggable;
import com.raf.rdd.jpa.dao.CharacteristicDao;
import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.breed.BreedCharac;
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

  /** The max default value. */
  private static final int MAX_VALUE = 15;

  /** The min default value. */
  private static final int MIN_VALUE = 6;

  /** The beauty threshold. */
  private static final int BEAUTY_THRESHOLD = 10;

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
   * Increment if possible the value of the characteristic.
   * 
   * @param figure
   *          the figure
   * @param characteristic
   *          the characteristic
   * @param value
   *          the value
   * @return the new value
   * @see CharacteristicService#increment(Figure, Characteristic, int)
   */
  @Override
  public int increment(final Figure figure, final Characteristic characteristic, final int value) {
    final int maxValue;
    if (figure.getBreed() == null) {
      maxValue = MAX_VALUE;
    } else {
      final BreedCharac breedCharac = getBreedCharac(figure.getBreed().getCharBreeds(), characteristic);
      if (breedCharac == null) {
        maxValue = MAX_VALUE;
      } else if (breedCharac.getLimit() == null) {
        maxValue = MAX_VALUE + breedCharac.getModifier();
      } else {
        maxValue = breedCharac.getLimit().intValue();
      }
    }
    final int newValue;
    if (value < maxValue) {
      newValue = value + 1;
    } else {
      newValue = value;
    }
    return newValue;
  }

  /**
   * Decrement if possible the value of the characteristic.
   * 
   * @param figure
   *          the figure
   * @param characteristic
   *          the characteristic
   * @param value
   *          the value
   * @return the new value
   * @see CharacteristicService#decrement(Figure, Characteristic, int)
   */
  @Override
  public int decrement(final Figure figure, final Characteristic characteristic, final int value) {
    final int minValue;
    if (figure.getBreed() == null) {
      minValue = MIN_VALUE;
    } else {
      final BreedCharac breedCharac = getBreedCharac(figure.getBreed().getCharBreeds(), characteristic);
      if (breedCharac == null) {
        minValue = MIN_VALUE;
      } else {
        minValue = MIN_VALUE + breedCharac.getModifier();
      }
    }
    final int newValue;
    if (value > minValue) {
      newValue = value - 1;
    } else {
      newValue = value;
    }
    return newValue;
  }

  @Override
  public int getInitialCost(final Figure figure) {
    final int total;
    if (figure.getCharValues() == null) {
      total = 0;
    } else {
      total = computeInitialCost(figure.getCharValues(), figure.getBreed(), figure.getPerson().getBeauty());
    }
    return total;
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

  private BreedCharac getBreedCharac(final Set<BreedCharac> charBreeds, final Characteristic characteristic) {
    BreedCharac found = null;
    for (final BreedCharac breedCharac : charBreeds) {
      if (breedCharac.getCharacteristic().getCharacteristic().equals(characteristic.getCharacteristic())) {
        found = breedCharac;
        break;
      }
    }
    return found;
  }

  private int computeInitialCost(final Set<CharValue> charValues, final Breed breed, final int beauty) {
    int total = 0;
    for (final CharValue charValue : charValues) {
      total += charValue.getValue();
    }
    if (breed != null) {
      for (final BreedCharac breedCharac : breed.getCharBreeds()) {
        total -= breedCharac.getModifier();
      }
    }
    if (beauty > BEAUTY_THRESHOLD) {
      total += beauty - BEAUTY_THRESHOLD;
    }
    return total;
  }

}
