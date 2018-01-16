package com.raf.rdd.swt.ui;

import javax.annotation.Resource;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
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

}
