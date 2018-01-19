package com.raf.rdd.service;

import com.raf.rdd.jpa.domain.DraconicHour;
import com.raf.rdd.jpa.enums.DraconicHourEnum;

/**
 * Service interface for managing {@link DraconicHour}.
 *
 * @author RAF
 */
public interface DraconicHourService {

  /**
   * Return the draconic hour corresponding to the enum.
   * 
   * @param draconicHourEnum
   *          the searched enum for the draconic hour
   * @return the draconic hour
   */
  DraconicHour get(DraconicHourEnum draconicHourEnum);
}
