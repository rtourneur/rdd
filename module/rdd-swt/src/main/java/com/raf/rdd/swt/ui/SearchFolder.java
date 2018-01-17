package com.raf.rdd.swt.ui;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.service.FigureService;

import lombok.NoArgsConstructor;

/**
 * Folder for searching figures.
 * 
 * @author RAF
 */
@Component
@NoArgsConstructor
public class SearchFolder extends AbstractUi {

  /** The main display. */
  @Resource
  private transient RddDisplay rddDisplay;

  /** The figure service. */
  @Resource
  private transient FigureService figureService;

  private transient Composite search;

  /**
   * Display the search folder.
   * 
   * @param parent
   *          the parent composite
   */
  public void display(final Composite parent) {
    search = new Composite(parent, SWT.BORDER);
    search.setLayout(new GridLayout());

    final Composite action = new Composite(search, SWT.NONE);
    action.setLayout(new GridLayout(4, false));

    final Label label = new Label(action, SWT.NONE);
    label.setText(getMessage("action.search.label"));

    final Text text = new Text(action, SWT.SINGLE | SWT.BORDER);

    final Table table = new Table(search, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
    table.setItemCount(30);
    table.setHeaderVisible(true);

    TableColumn column = new TableColumn(table, SWT.LEFT);
    column.setText(getMessage("action.search.result.name"));
    column.setWidth(100);
    column = new TableColumn(table, SWT.LEFT);
    column.setText(getMessage("action.search.result.breed"));
    column.setWidth(100);

    final Button submit = new Button(action, SWT.PUSH);

    submit.setText(getMessage("action.search.submit"));
    submit.addListener(SWT.Selection, event -> {
      showResult(table, text);
      parent.layout();
    });

    final Button open = new Button(action, SWT.PUSH);
    open.setEnabled(false);
    open.setText(getMessage("action.search.open"));
    open.addListener(SWT.Selection, event -> {
      showFigure(table);
    });

    table.addListener(SWT.Selection, event -> {
      open.setEnabled(table.getSelectionIndex() >= 0);
    });

    parent.layout();
  }

  /**
   * Hide the folder.
   */
  public void hide() {
    Display.getDefault().asyncExec(() -> {
      this.search.dispose();
    });
  }

  private void showFigure(final Table table) {
    final int index = table.getSelectionIndex();
    final TableItem tableItem = table.getItem(index);
    final Figure figure = (Figure) tableItem.getData();
    this.rddDisplay.showFigure(figure, FolderState.READ);
    hide();
  }

  private void showResult(final Table table, final Text text) {
    table.setRedraw(false);
    table.clearAll();
    final List<Figure> figures = figureService.find(text.getText());
    table.setItemCount(figures.size());
    for (final Figure figure : figures) {
      createLine(table, figure);
    }
    table.setRedraw(true);
    table.layout();
  }

  private void createLine(final Table table, final Figure figure) {
    final TableItem tableItem = new TableItem(table, SWT.NONE);
    tableItem.setText(0, figure.getName());
    tableItem.setText(1, figure.getBreedName());
    tableItem.setData(figure);
  }
}
