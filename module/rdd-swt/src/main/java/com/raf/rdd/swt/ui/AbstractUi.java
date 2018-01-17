package com.raf.rdd.swt.ui;

import javax.annotation.Resource;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import com.raf.rdd.swt.service.MessageService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Abstact superclass for UI.
 * 
 * @author RAF
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractUi {

  /** The message service. */
  @Resource
  @Getter(value = AccessLevel.PROTECTED)
  private transient MessageService messageService;

  /**
   * Display the exception in a message dialog.
   * 
   * @param exception
   *          the exception
   * @param shell
   *          the parent shell
   */
  protected final void showErrorDialog(final Throwable exception, final Shell shell) {
    final Status error = new Status(IStatus.ERROR, "RDD", exception.getMessage());
    ErrorDialog.openError(shell, "RDD", messageService.getError("error.occured"), error);
  }

  /**
   * Delagating method for getting i18n message.
   * 
   * @param code
   *          the message code
   * @return the message
   */
  protected final String getMessage(final String code) {
    return this.messageService.getMessage(code);
  }

  /**
   * Check if the text of the event is numeric.
   * 
   * @param event
   *          the event
   */
  protected final void checkInteger(final Event event) {
    final char[] chars = event.text.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] < '0' || chars[i] > '9') {
        event.doit = false;
        return;
      }
    }
  }

}
