package com.raf.rdd.jpa.dao.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.raf.fwk.jpa.dao.AbstractNamedDao;
import com.raf.rdd.jpa.dao.SkillDao;
import com.raf.rdd.jpa.domain.Skill;

/**
 * Implementation DAO for {@link Skill}.
 * 
 * @author RAF
 */
@Repository
public final class SkillDaoImpl extends AbstractNamedDao<Skill>
    implements SkillDao {

  /**
   * Constructor.
   */
  public SkillDaoImpl() {
    super(Skill.class);
  }

  /**
   * Append the predicates for the findByExample request.
   * 
   * @param predicatesList
   *          the list of predicates
   * @param root
   *          the root type
   * @param example
   *          the example
   * @see AbstractNamedDao#addPredicates(List, Root, Skill)
   */
  @Override
  protected void addPredicates(final List<Predicate> predicatesList, final Root<Skill> root, final Skill example) {
    if (example.getSkillType() != null) {
      predicatesList.add(getEquals(root, "skillType", example.getSkillType()));
    }
  }

}
