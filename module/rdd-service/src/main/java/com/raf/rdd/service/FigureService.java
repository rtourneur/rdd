package com.raf.rdd.service;

import java.util.List;

import com.raf.fwk.service.ServiceException;
import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.character.Figure;

/**
 * Service interface for managing {@link Figure}.
 *
 * @author RAF
 */
public interface FigureService {

  /**
   * Return the list of figures corresponding to the name.
   * 
   * @param name
   *          the searched name
   * @return the list of figures
   */
  List<Figure> find(String name);

  /**
   * Return the figure corresponding to the name.
   * 
   * @param name
   *          the searched name
   * @return the figure
   * @throws ServiceException
   *           when the figure is not found
   */
  Figure get(String name) throws ServiceException;

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
