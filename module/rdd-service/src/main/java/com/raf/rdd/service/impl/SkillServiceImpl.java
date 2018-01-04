package com.raf.rdd.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.service.AbstractService;
import com.raf.fwk.util.aop.Loggable;
import com.raf.rdd.jpa.dao.SkillDao;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.domain.character.SkillValue;
import com.raf.rdd.jpa.domain.character.SkillValuePk;
import com.raf.rdd.jpa.domain.skill.Skill;
import com.raf.rdd.service.SkillService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for {@link SkillService}.
 *
 * @author RAF
 */
@Slf4j
@Service
@NoArgsConstructor
public final class SkillServiceImpl extends AbstractService<SkillDao, Skill, String> implements SkillService {

  /**
   * Initialise the set of Skills for a figure.
   *
   * @param figure
   *          the figure
   * @return the set of Skills
   * @see SkillService#initSkill()
   */
  @Override
  @Transactional
  @Loggable
  public Set<SkillValue> initSkills(final Figure figure) {
    final Set<SkillValue> values = new LinkedHashSet<>();
    final List<Skill> skills = getEntityDao().listAll();
    final List<Skill> forbiddens = new ArrayList<>();
    if (figure.getBreed() != null) {
      forbiddens.addAll(figure.getBreed().getForbiddens());
    }
    SkillValue skillValue;
    int base;
    for (final Skill skill : skills) {
      if (forbiddens.contains(skill)) {
        log.debug("Skill {} is forbidden", skill.getIdentifier());
        base = -11;
      } else {
        base = skill.getSkillTypeEnum().getBase();
      }
      skillValue = create(skill, figure, base);
      values.add(skillValue);
    }
    return values;
  }

  /**
   * Create a new chararteristic value whith base value.
   *
   * @param Skill
   *          the Skill
   * @param figure
   *          the figure
   * @param base
   *          the base value
   * @return the created chararteristic value
   */
  private SkillValue create(final Skill skill, final Figure figure, final int base) {
    final SkillValuePk skillValuePk = new SkillValuePk(figure.getIdentifier(), skill.getIdentifier());
    final SkillValue skillValue = new SkillValue();
    skillValue.setIdentifier(skillValuePk);
    skillValue.setSkill(skill);
    skillValue.setFigure(figure);
    skillValue.setValue(base);
    return skillValue;
  }

}
