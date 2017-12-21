package com.raf.rdd.service;

import java.util.Set;

import com.raf.rdd.jpa.domain.Characteristic;
import com.raf.rdd.jpa.domain.character.CharValue;

/**
 * Service interface for managing {@link Characteristic}.
 * 
 * @author RAF
 */
public interface CharacteristicService {

  /**
   * Initialise the set of characteristics for a character.
   * 
   * @return the set of characteristics
   */
  Set<CharValue> initCharacteristic();
}
