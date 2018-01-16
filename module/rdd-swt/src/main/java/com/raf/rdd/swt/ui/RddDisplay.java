package com.raf.rdd.swt.ui;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.raf.fwk.service.ServiceException;
import com.raf.rdd.swt.service.RecentService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Main display class.
 * 
 * @author RAF
 */
@Component
@NoArgsConstructor
@Slf4j
public class RddDisplay extends AbstractUi {

  /** The search folder. */
  @Resource
  private transient SearchFolder searchFolder; 

  /** The recent figure service. */
  @Resource
  private transient RecentService recentService;

  private Shell shell;

  public Display init() {
    final Display display = new Display();
    shell = new Shell(display);
    shell.setText(getMessageService().getMessage("main.title"));
    shell.setLayout(new FillLayout(SWT.VERTICAL));
    createMenuBar();

    return display;
  }

  /**
   * Run the application.
   * 
   * @param display
   *          the display
   */
  public void run(final Display display) {
    shell.open();
    while (!shell.isDisposed()) {
      try {
        if (!display.readAndDispatch()) {
          display.sleep();
        }
      } catch (SWTException e) {
        log.warn(e.getMessage());
        showErrorDialog(e, shell);
      }
    }
    display.dispose();
  }

  private void createMenuBar() {
    final Menu bar = new Menu(shell, SWT.BAR);
    shell.setMenuBar(bar);

    final MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
    fileItem.setText(getMessageService().getMessage("menu.figure.label"));
    fileItem.setMenu(createFigureMenu(bar));

  }

  private Menu createFigureMenu(final Menu bar) {
    final Menu menu = new Menu(bar);
    menu.addListener(SWT.Show, showEvent -> {
      for (final MenuItem menuItem : menu.getItems()) {
        menuItem.dispose();
      }

      // New
      MenuItem item = new MenuItem(menu, SWT.PUSH);
      item.setText(getMessageService().getMessage("menu.figure.new.label"));
      item.addListener(SWT.Selection, event -> {
        newFigure();
      });

      // Search
      item = new MenuItem(menu, SWT.PUSH);
      item.setText(getMessageService().getMessage("menu.figure.search.label"));
      item.addListener(SWT.Selection, event -> {
        searchFigure();
      });

      // Exit
      item = new MenuItem(menu, SWT.PUSH);
      item.setText(getMessageService().getMessage("menu.figure.exit.label"));
      item.addListener(SWT.Selection, event -> shell.close());

      try {
        final List<String> recent = recentService.getRecentFigures();
        if (!recent.isEmpty()) {
          // Separator
          new MenuItem(menu, SWT.SEPARATOR);

          for (int index = 0; index < recent.size(); index++) {
            loadRecent(menu, recent, index);
          }
        }
      } catch (ServiceException e) {
        showErrorDialog(e, shell);
      }
    });

    return menu;
  }

  private void loadRecent(final Menu menu, final List<String> recent, final int index) {
    final MenuItem item = new MenuItem(menu, SWT.PUSH);
    final String name = recent.get(index);
    final StringBuilder text = new StringBuilder().append(index + 1).append(" : ").append(name);
    item.setText(text.toString());
    item.addListener(SWT.Selection, event -> {
      loadFigure(name);
    });
  }

  private void searchFigure() {
    Display.getDefault().asyncExec(() -> {
      searchFolder.display(shell);
    });

  }

  private void newFigure() {
    // TODO Auto-generated method stub

  }

  private void loadFigure(final String name) {
    // TODO Auto-generated method stub

  }

}
