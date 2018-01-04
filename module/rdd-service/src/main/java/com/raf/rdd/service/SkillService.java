package com.raf.rdd.service;

import java.util.Set;

import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.domain.character.SkillValue;
import com.raf.rdd.jpa.domain.skill.Skill;

/**
 * Service interface for managing {@link Skill}.
 *
 * @author RAF
 */
public interface SkillService {

  /**
   * Initialise the set of skills for a character with a breed.
   *
   * @return the set of skills
   */
  Set<SkillValue> initSkills(Figure figure);
}
