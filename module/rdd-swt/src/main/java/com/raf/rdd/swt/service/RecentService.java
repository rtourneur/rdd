package com.raf.rdd.swt.service;

import java.util.List;

import com.raf.fwk.service.ServiceException;

/**
 * Service interface for loading and saving recent figures.
 * 
 * @author RAF
 */
public interface RecentService {

  /** The number of recent entries. */
  int MAX_RECENT = 4;

  /**
   * Return the list of recent figures names.
   * 
   * @return the list of recent figures names
   * @throws ServiceException
   *           when exception occurs while loading ini file
   */
  List<String> getRecentFigures() throws ServiceException;

  /**
   * Add a recent figure name to the list.
   * 
   * @param name
   *          the name of the figure
   * @throws ServiceException
   *           when exception occurs while loading ini file
   */
  void addRecentFigure(String name) throws ServiceException;
}
