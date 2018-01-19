package com.raf.rdd.service;

import java.util.Set;

import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.domain.character.CharValue;
import com.raf.rdd.jpa.domain.character.Figure;

/**
 * Service interface for managing {@link Characteristic}.
 *
 * @author RAF
 */
public interface CharacteristicService {

  /** The base value for characteristics. */
  int BASE_VALUE = 6;

  /**
   * Initialise the set of characteristics for a figure.
   *
   * @param figure
   *          the figure
   * @return the set of characteristics
   */
  Set<CharValue> initCharacteristics(Figure figure);

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
   */
  int increment(Figure figure, Characteristic characteristic, int value);

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
   */
  int decrement(Figure figure, Characteristic characteristic, int value);

  /**
   * Return the total cost points of the figure's characteristics.
   * 
   * @param figure
   *          the figure
   * @return the total cost points
   */
  int getInitialCost(Figure figure);
}
