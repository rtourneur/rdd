package com.raf.rdd.service.impl;

import org.springframework.stereotype.Service;

import com.raf.fwk.service.AbstractService;
import com.raf.rdd.jpa.dao.DraconicHourDao;
import com.raf.rdd.jpa.domain.DraconicHour;
import com.raf.rdd.jpa.enums.DraconicHourEnum;
import com.raf.rdd.service.DraconicHourService;
import com.raf.rdd.service.FigureService;

import lombok.NoArgsConstructor;

/**
 * Service implementation for {@link FigureService}.
 *
 * @author RAF
 */
@Service
@NoArgsConstructor
public final class DraconicHourServiceImpl extends AbstractService<DraconicHourDao, DraconicHour, String>
    implements DraconicHourService {

  /**
   * Return the draconic hour corresponding to the enum.
   * 
   * @param draconicHourEnum
   *          the searched enum for the draconic hour
   * @return the draconic hour
   * @see DraconicHourService#get(DraconicHourEnum)
   */
  @Override
  public DraconicHour get(final DraconicHourEnum draconicHourEnum) {
    return getEntityDao().getById(draconicHourEnum.getCode());
  }

}
