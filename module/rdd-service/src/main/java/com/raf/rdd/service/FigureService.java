package com.raf.rdd.service;

import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.character.Figure;

/**
 * Service interface for managing {@link Figure}.
 *
 * @author RAF
 */
public interface FigureService {

  /**
   * Create a new figure with base value for characteristics, and no breed.
   *
   * @return the new figure
   */
  Figure create();

  /**
   * Set the breed of the figure, and modify the characteristics values.
   *
   * @param figure
   *          the figure
   * @param breed
   *          the breed
   */
  void setBreed(Figure figure, Breed breed);
}
