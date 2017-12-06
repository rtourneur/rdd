package com.raf.rdd.jpa.dao.impl;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.dao.AbstractNamedDao;
import com.raf.rdd.jpa.dao.SkillTypeDao;
import com.raf.rdd.jpa.domain.skill.SkillType;

/**
 * Implementation DAO for {@link SkillType}.
 *
 * @author RAF
 */
@Repository
public final class SkillTypeDaoImpl extends AbstractNamedDao<SkillType> implements SkillTypeDao {

  /**
   * Constructor.
   */
  public SkillTypeDaoImpl() {
    super(SkillType.class);
  }

}
