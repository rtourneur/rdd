package com.raf.rdd.jpa.dao.impl;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.dao.AbstractNamedDao;
import com.raf.rdd.jpa.dao.DraconicHourDao;
import com.raf.rdd.jpa.domain.DraconicHour;

/**
 * Implementation DAO for {@link DraconicHour}.
 * 
 * @author RAF
 */
@Repository
public final class DraconicHourDaoImpl extends AbstractNamedDao<DraconicHour>
    implements DraconicHourDao {

  /**
   * Constructor.
   */
  public DraconicHourDaoImpl() {
    super(DraconicHour.class);
  }

}
