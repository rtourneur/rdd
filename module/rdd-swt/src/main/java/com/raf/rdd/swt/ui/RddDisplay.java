package com.raf.rdd.swt.ui;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.raf.fwk.service.ServiceException;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.service.FigureService;
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

  /** The figure folder. */
  @Resource
  private transient FigureFolder figureFolder;

  /** The recent figure service. */
  @Resource
  private transient RecentService recentService;

  /** The figure service. */
  @Resource
  private transient FigureService figureService;

  /** The shell. */
  private transient Shell shell;

  /** The folder. */
  private transient CTabFolder folder;

  /**
   * Init the display of the application.
   * 
   * @return the display
   */
  public Display init() {
    final Display display = new Display();
    shell = new Shell(display);
    shell.setText(getMessageService().getMessage("main.title"));
    shell.setLayout(new GridLayout(1, true));
    createMenuBar();
    folder = new CTabFolder(shell, SWT.CLOSE);
    folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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
        log.warn(e.getMessage(), e);
        showErrorDialog(e, shell);
      }
    }
    display.dispose();
  }

  /**
   * Display the figure folder.
   * 
   * @param figure
   *          the figure
   * @param folderState
   *          the folder state
   */
  public void showFigure(final Figure figure, final FolderState folderState) {
    Display.getDefault().asyncExec(() -> {
      figureFolder.display(folder, folderState, figure);
    });
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
      searchFolder.display(folder);
    });

  }

  private void newFigure() {
    final Figure figure = this.figureService.create();
    showFigure(figure, FolderState.CREATE);
  }

  private void loadFigure(final String name) {
    try {
      final Figure figure = this.figureService.get(name);
      showFigure(figure, FolderState.READ);
    } catch (ServiceException e) {
      showErrorDialog(e, shell);
    }
  }

}
