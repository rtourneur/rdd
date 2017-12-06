package com.raf.rdd.jpa.dao.impl;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.dao.AbstractNamedDao;
import com.raf.rdd.jpa.dao.CharacteristicDao;
import com.raf.rdd.jpa.domain.Characteristic;

/**
 * Implementation DAO for {@link Characteristic}.
 *
 * @author RAF
 */
@Repository
public final class CharacteristicDaoImpl extends AbstractNamedDao<Characteristic> implements CharacteristicDao {

  /**
   * Constructor.
   */
  public CharacteristicDaoImpl() {
    super(Characteristic.class);
  }

}
