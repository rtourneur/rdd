package com.raf.rdd.swt.ui;

import java.util.List;

import javax.annotation.Resource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.service.FigureService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author RAF
 */
@Component
@NoArgsConstructor
@Slf4j
public class SearchFolder extends AbstractUi {

  @Resource
  private FigureService figureService;

  public void display(final Composite parent) {
    final Composite search = new Composite(parent, SWT.BORDER);
    search.setLayout(new GridLayout());

    final Composite action = new Composite(search, SWT.NONE);
    action.setLayout(new GridLayout(3, false));

    final Label label = new Label(action, SWT.NONE);
    label.setText(getMessageService().getMessage("action.search.label"));

    final Text text = new Text(action, SWT.SINGLE | SWT.BORDER);

    final Button submit = new Button(action, SWT.PUSH);


    final Table table = new Table(search, SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
    table.setItemCount(30);
    TableColumn column = new TableColumn(table, SWT.LEFT);
    column.setText("Nom");
    column.setWidth(100);
    column = new TableColumn(table, SWT.LEFT);
    column.setText("Race");
    column.setWidth(100);
    table.setHeaderVisible(true);

    submit.setText(getMessageService().getMessage("action.search.submit"));
    submit.addListener(SWT.Selection, event -> {
      showResult(table, text);
      parent.layout();
    });
    parent.layout();
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
  }
}
