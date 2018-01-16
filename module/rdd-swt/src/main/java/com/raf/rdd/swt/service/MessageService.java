package com.raf.rdd.swt.service;

/**
 * Service interface for managing I18N messages.
 *
 * @author RAF
 */
public interface MessageService {

  /**
   * Return the i18n message label from the property file.
   * 
   * @param code
   *          the code of the message
   * @return the i18n message label
   */
  String getMessage(String code);

  /**
   * Return the i18n message label from the property file.
   * 
   * @param code
   *          the code of the message
   * @param args
   *          the args for the message
   * @return the i18n message label
   */
  String getMessage(String code, String... args);

  /**
   * Return the i18n error label from the property file.
   * 
   * @param code
   *          the code of the error
   * @return the i18n error label
   */
  String getError(String code);

  /**
   * Return the i18n error label from the property file.
   * 
   * @param code
   *          the code of the error
   * @param args
   *          the args for the error
   * @return the i18n error label
   */
  String getError(String code, String... args);

}
