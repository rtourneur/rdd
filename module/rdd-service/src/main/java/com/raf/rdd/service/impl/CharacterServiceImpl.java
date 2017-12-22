package com.raf.rdd.service.impl;

import org.springframework.stereotype.Service;

import com.raf.fwk.service.AbstractService;
import com.raf.rdd.jpa.dao.FigureDao;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.service.CharacterService;

/**
 * Service implementation for {@link CharacterService}.
 * 
 * @author RAF
 *
 */
@Service
public class CharacterServiceImpl extends AbstractService<FigureDao, Figure, Integer>
    implements CharacterService {
  // TODO
}
