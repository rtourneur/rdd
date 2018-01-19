package com.raf.rdd.swt.service.impl;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.raf.rdd.swt.service.MessageService;

import lombok.NoArgsConstructor;

/**
 * Service implementation for {@link MessageService}.
 * 
 * @author RAF
 */
@Service
@NoArgsConstructor
public class MessageServiceImpl implements MessageService {

  /** The message source. */
  @Resource
  private transient MessageSource messageSource;

  /** The label source. */
  @Resource
  private transient MessageSource labelSource;

  /** The error source. */
  @Resource
  private transient MessageSource errorSource;

  /**
   * Return the i18n message from the property file.
   * 
   * @param code
   *          the code of the message
   * @return the i18n message * @see MessageService#getMessage(String)
   */
  @Override
  public String getMessage(final String code) {
    return getText(messageSource, code, (String[]) null);
  }

  /**
   * Return the i18n message from the property file.
   * 
   * @param code
   *          the code of the message
   * @param args
   *          the args for the message
   * @return the i18n message
   * @see MessageService#getMessage(String, String[])
   */
  @Override
  public String getMessage(final String code, final String... args) {
    return getText(messageSource, code, args);
  }

  /**
   * Return the i18n label from the property file.
   * 
   * @param code
   *          the code of the label
   * @return the i18n label
   * @see MessageService#getLabel(String)
   */
  @Override
  public String getLabel(final String code) {
    return getText(labelSource, code, (String[]) null);
  }

  /**
   * Return the i18n error from the property file.
   * 
   * @param code
   *          the code of the error
   * @return the i18n error
   * @see MessageService#getError(String)
   */
  @Override
  public String getError(final String code) {
    return getText(errorSource, code, (String[]) null);
  }

  /**
   * Return the i18n error from the property file.
   * 
   * @param code
   *          the code of the error
   * @param args
   *          the args for the error
   * @return the i18n error
   * @see MessageService#getError(String, String[])
   */
  @Override
  public String getError(final String code, final String... args) {
    return getText(errorSource, code, args);
  }

  private String getText(final MessageSource source, final String code, final String... args) {
    return source.getMessage(code, args, Locale.getDefault());
  }
}
