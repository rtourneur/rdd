package com.raf.rdd.swt.ui;

import javax.annotation.Resource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.enums.DraconicHourEnum;
import com.raf.rdd.jpa.enums.GenderEnum;
import com.raf.rdd.service.DraconicHourService;

import lombok.NoArgsConstructor;

/**
 * Folder for displaying figures.
 * 
 * @author RAF
 */
@Component
@NoArgsConstructor
public class FigureFolder extends AbstractUi {

  /** The draconic hour service. */
  @Resource
  private transient DraconicHourService draconicHourService;

  /**
   * Display the search folder.
   * 
   * @param parent
   *          the parent composite
   */
  public void display(final CTabFolder parent, final FolderState folderState, final Figure figure) {
    final CTabItem tabItem = new CTabItem(parent, SWT.NONE);
    final String titleCode;
    switch (folderState) {
    case CREATE:
      titleCode = "folder.figure.title.create";
      break;
    case UPDATE:
      titleCode = "folder.figure.title.update";
      break;
    default:
      titleCode = "folder.figure.title.read";
      break;
    }
    tabItem.setText(getMessage(titleCode));

    final Composite mainPanel = new Composite(parent, SWT.BORDER);
    mainPanel.setLayout(new GridLayout());
    tabItem.setControl(mainPanel);

    // final Composite identity =
    createIdentity(mainPanel, folderState, figure);

    parent.setSelection(tabItem);
    parent.layout();
  }

  private Composite createIdentity(final Composite parent, final FolderState folderState, final Figure figure) {
    final Composite identity = new Composite(parent, SWT.NONE);
    identity.setLayout(new GridLayout(8, false));

    final Text name = new Text(identity, SWT.NONE);
    setText(name, figure.getName());
    name.setTextLimit(50);
    name.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 8, 1));
    name.setEditable(folderState.isEditable());
    name.addListener(SWT.DefaultSelection, event -> {
      figure.setName(name.getText());
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.age"));
    final Text age = new Text(identity, SWT.NONE);
    age.setText(String.valueOf(figure.getPerson().getAge()));
    age.setTextLimit(2);
    age.setEditable(folderState.isEditable());
    age.addListener(SWT.DefaultSelection, event -> {
      figure.getPerson().setAge(Integer.parseInt(age.getText()));
    });
    age.addListener(SWT.Verify, event -> {
      checkInteger(event);
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.gender"));
    final Combo gender = new Combo(identity, SWT.READ_ONLY);
    if (folderState.isEditable()) {
      for (final GenderEnum genderEnum : GenderEnum.values()) {
        gender.add(genderEnum.getCode());
      }
      if (figure.getPerson().getGender() != null) {
        gender.select(figure.getPerson().getGender().ordinal());
        gender.addListener(SWT.DefaultSelection, event -> {
          figure.getPerson().setGender(GenderEnum.get(gender.getItem(gender.getSelectionIndex())));
        });
      }
    } else {
      gender.add(figure.getPerson().getGender().getCode());
      gender.select(0);
    }

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.weight"));
    final Text weight = new Text(identity, SWT.NONE);
    weight.setText(String.valueOf(figure.getPerson().getWeight()));
    weight.setTextLimit(3);
    weight.setEditable(folderState.isEditable());
    weight.addListener(SWT.DefaultSelection, event -> {
      figure.getPerson().setWeight(Integer.parseInt(weight.getText()));
    });
    weight.addListener(SWT.Verify, event -> {
      checkInteger(event);
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.size"));
    final Text size = new Text(identity, SWT.NONE);
    size.setText(String.valueOf(figure.getPerson().getSize()));
    size.setTextLimit(3);
    size.setEditable(folderState.isEditable());
    size.addListener(SWT.DefaultSelection, event -> {
      figure.getPerson().setSize(Integer.parseInt(size.getText()));
    });
    size.addListener(SWT.Verify, event -> {
      checkInteger(event);
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.beauty"));
    final Text beauty = new Text(identity, SWT.NONE);
    beauty.setText(String.valueOf(figure.getPerson().getBeauty()));
    beauty.setTextLimit(2);
    beauty.setEditable(folderState.isEditable());
    beauty.addListener(SWT.DefaultSelection, event -> {
      figure.getPerson().setBeauty(Integer.parseInt(beauty.getText()));
    });
    beauty.addListener(SWT.Verify, event -> {
      checkInteger(event);
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.eyes"));
    final Text eyes = new Text(identity, SWT.NONE);
    setText(eyes, figure.getPerson().getEyes());
    eyes.setTextLimit(50);
    eyes.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
    eyes.setEditable(folderState.isEditable());
    eyes.addListener(SWT.DefaultSelection, event -> {
      figure.getPerson().setEyes(eyes.getText());
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.hairs"));
    final Text hairs = new Text(identity, SWT.NONE);
    setText(hairs, figure.getPerson().getHairs());
    hairs.setTextLimit(50);
    hairs.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
    hairs.setEditable(folderState.isEditable());
    hairs.addListener(SWT.DefaultSelection, event -> {
      figure.getPerson().setHairs(hairs.getText());
    });

    final Label hourLabel = new Label(identity, SWT.NONE);
    hourLabel.setText(getMessage("action.figure.label.draconichour"));
    hourLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 3, 1));
    final Combo hour = new Combo(identity, SWT.READ_ONLY);
    hour.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 5, 1));
    if (folderState.isEditable()) {
      for (final DraconicHourEnum draconicHourEnum : DraconicHourEnum.values()) {
        hour.add(getMessage("label.draconichour." + draconicHourEnum.name()));
      }
      if (figure.getPerson().getBirthTime() != null) {
        hour.select(figure.getPerson().getBirthTime().getDraconicHour().ordinal());
        hour.addListener(SWT.DefaultSelection, event -> {
          final DraconicHourEnum draconicHourEnum = DraconicHourEnum.get(hour.getItem(hour.getSelectionIndex()));
          figure.getPerson().setBirthTime(this.draconicHourService.get(draconicHourEnum));
        });
      }
    } else {
      final DraconicHourEnum draconicHourEnum = figure.getPerson().getBirthTime().getDraconicHour();
      hour.add(getMessage("label.draconichour." + draconicHourEnum.name()));
      hour.select(0);
    }

    return identity;
  }

  private void setText(final Text text, final String value) {
    if (value != null) {
      text.setText(value);
    }
  }

}
