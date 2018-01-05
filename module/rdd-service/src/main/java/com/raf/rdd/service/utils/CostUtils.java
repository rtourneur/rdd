package com.raf.rdd.service.utils;

import com.raf.fwk.service.ServiceException;
import com.raf.rdd.service.CharacteristicService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class for experience costs.
 *
 * @author tourneur
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CostUtils {

  /**
   * Return the experience cost of the level for the characteristic value.
   *
   * @param value
   *          the value
   * @return the cost
   * @throws ServiceException
   *           when the value is out of range
   */
  public static int getCharacLevelCost(final int value) throws ServiceException {
    if (value <= CharacteristicService.BASE_VALUE) {
      throw new ServiceException("error.value.toolow", Integer.valueOf(value));
    }

    final int cost;
    switch (value) {
    case 7:
    case 8:
      cost = 6;
      break;
    case 9:
    case 10:
      cost = 7;
      break;
    case 11:
    case 12:
      cost = 8;
      break;
    case 13:
    case 14:
      cost = 9;
      break;
    default:
      cost = (value - 14) * 10;
      break;
    }
    return cost;
  }

  /**
   * Return the experience cost of the level for the skill value.
   *
   * @param current
   *          the current value of the skill
   * @param newValue
   *          the new value of the skill
   * @return the cost
   * @throws ServiceException
   *           when the value is out of range
   */
  public static int getSkillLevelCost(final int current, final int newValue) throws ServiceException {
    if (newValue <= current) {
      throw new ServiceException("error.value.toolow", Integer.valueOf(newValue));
    }
    int cost = 0;
    for (int index = current + 1; index <= newValue; index++) {
      cost += getSkillLevelCost(index);
    }

    return cost;
  }

  /**
   * Return the experience cost of the level for the skill value.
   *
   * @param value
   *          the value
   * @return the cost
   */
  public static int getSkillLevelCost(final int value) {
    final int cost;
    if (value <= -8) {
      cost = 5;
    } else if (value <= -4) {
      cost = 10;
    } else if (value <= 0) {
      cost = 15;
    } else if (value <= 4) {
      cost = 20;
    } else if (value <= 6) {
      cost = 30;
    } else if (value <= 8) {
      cost = 40;
    } else if (value <= 10) {
      cost = 60;
    } else {
      cost = 100;
    }
    return cost;
  }

}
