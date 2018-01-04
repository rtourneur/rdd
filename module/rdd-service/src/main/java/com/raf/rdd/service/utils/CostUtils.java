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
    case 15:
      cost = 10;
      break;
    case 16:
      cost = 20;
      break;
    case 17:
      cost = 30;
      break;
    case 18:
      cost = 40;
      break;
    default:
      cost = 50;
      break;
    }
    return cost;
  }

}
