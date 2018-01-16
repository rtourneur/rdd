package com.raf.rdd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raf.fwk.service.AbstractService;
import com.raf.fwk.util.aop.Loggable;
import com.raf.rdd.jpa.dao.FigureDao;
import com.raf.rdd.jpa.domain.breed.Breed;
import com.raf.rdd.jpa.domain.breed.BreedCharac;
import com.raf.rdd.jpa.domain.character.CharValue;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.enums.CharacteristicEnum;
import com.raf.rdd.service.CharacteristicService;
import com.raf.rdd.service.FigureService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for {@link FigureService}.
 *
 * @author RAF
 */
@Slf4j
@Service
@NoArgsConstructor
public final class FigureServiceImpl extends AbstractService<FigureDao, Figure, Integer> implements FigureService {

  /** The Characteristic Service. */
  @Resource
  private transient CharacteristicService characService;

  /**
   * Return the list of figures corresponding to the name.
   * 
   * @param name
   *          the searched name
   * @return the list of figures
   * @see FigureService#find(String)
   */
  @Override
  public List<Figure> find(final String name) {
    final Figure example = new Figure();
    example.setName(name);
    return getEntityDao().findByExample(example);
  }

  /**
   * Create a new figure with base value for characteristics, and no breed.
   *
   * @return the new figure
   * @see FigureService#create()
   */
  @Override
  @Transactional
  @Loggable
  public Figure create() {
    final Figure figure = new Figure();
    figure.setCharValues(this.characService.initCharacteristics(figure));
    return figure;
  }

  /**
   * Set the breed of the figure, and modify the characteristics values.
   *
   * @param figure
   *          the figure
   * @param breed
   *          the breed
   * @see FigureService#setBreed(Figure, Breed)
   */
  @Override
  @Transactional
  @Loggable
  public void setBreed(final Figure figure, final Breed breed) {
    figure.setBreed(breed);
    figure.setBreedName(breed.getIdentifier());
    final Map<CharacteristicEnum, BreedCharac> map = new HashMap<>(breed.getCharBreeds().size());
    breed.getCharBreeds().forEach(breedCharac -> {
      map.put(breedCharac.getCharacteristic().getCharacteristic(), breedCharac);
    });
    int stature = 0;
    for (final CharValue charValue : figure.getCharValues()) {
      final CharacteristicEnum characteristic = charValue.getCharacteristic().getCharacteristic();
      if (map.containsKey(characteristic)) {
        final BreedCharac breedCharac = map.get(characteristic);
        int current = charValue.getCurrent() + breedCharac.getModifier();
        if (breedCharac.getLimit() != null) {
          current = Math.min(current, breedCharac.getLimit().intValue());
        }
        log.debug("Update characteristic {} for breed {} previous {} current {}", characteristic.getCode(),
            breed.getName(), charValue.getCurrent(), current);
        charValue.setCurrent(current);
      }
      switch (characteristic) {
      case STATURE:
        stature = charValue.getCurrent();
        break;
      case STRENGTH:
        int current = charValue.getCurrent();
        if (stature > 0 && current > stature + 4) {
          current = stature + 4;
          log.debug("Limit characteristic {} for breed {} previous {} current {}", characteristic.getCode(),
              breed.getName(), charValue.getCurrent(), current);
          charValue.setCurrent(current);
        }
        break;
      default:
        break;
      }
    }
  }

}
