package com.raf.rdd.swt.ui;

import javax.annotation.Resource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.raf.rdd.jpa.domain.character.CharValue;
import com.raf.rdd.jpa.domain.character.Figure;
import com.raf.rdd.jpa.enums.DraconicHourEnum;
import com.raf.rdd.jpa.enums.GenderEnum;
import com.raf.rdd.service.CharacteristicService;
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

  /** The Characteristic service. */
  @Resource
  private CharacteristicService characteristicService;

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

    createCharacteristics(mainPanel, folderState, figure);

    parent.setSelection(tabItem);
    parent.layout();
  }

  private Composite createIdentity(final Composite parent, final FolderState folderState, final Figure figure) {
    final Composite identity = new Composite(parent, SWT.NONE);
    identity.setLayout(new GridLayout(8, false));

    final Text name = new Text(identity, SWT.NONE);
    setText(name, figure.getName());
    name.setTextLimit(50);
    name.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, true, 8, 1));
    name.setEditable(folderState.isEditable());
    name.addListener(SWT.Modify, event -> {
      figure.setName(name.getText());
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.age"));
    final Combo age = new Combo(identity, SWT.READ_ONLY);
    final int figureAge = figure.getPerson().getAge();
    if (folderState.isEditable()) {
      for (int index = 18; index <= 40; index++) {
        age.add(String.valueOf(index));
      }
      if (figureAge > 0) {
        age.select(age.indexOf(String.valueOf(figureAge)));
      }
      age.addListener(SWT.Selection, event -> {
        final String text = ((Combo) event.widget).getText();
        figure.getPerson().setAge(Integer.parseInt(text));
      });
    } else {
      age.add(String.valueOf(figureAge));
      age.select(0);
    }

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.gender"));
    final Combo gender = new Combo(identity, SWT.READ_ONLY);
    if (folderState.isEditable()) {
      for (final GenderEnum genderEnum : GenderEnum.values()) {
        gender.add(genderEnum.getCode());
      }
      if (figure.getPerson().getGender() != null) {
        gender.select(figure.getPerson().getGender().ordinal());
      }
      gender.addListener(SWT.Selection, event -> {
        final String text = ((Combo) event.widget).getText();
        figure.getPerson().setGender(GenderEnum.get(text));
      });
    } else {
      gender.add(figure.getPerson().getGender().getCode());
      gender.select(0);
    }

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.weight"));
    final Text weight = new Text(identity, SWT.NONE);
    weight.setText(String.valueOf(figure.getPerson().getWeight()));
    weight.setTextLimit(3);
    weight.setEditable(folderState.isEditable());
    weight.addListener(SWT.Modify, event -> {
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
    size.addListener(SWT.Modify, event -> {
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
    beauty.addListener(SWT.Modify, event -> {
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
    eyes.addListener(SWT.Modify, event -> {
      figure.getPerson().setEyes(eyes.getText());
    });

    new Label(identity, SWT.NONE).setText(getMessage("action.figure.label.hairs"));
    final Text hairs = new Text(identity, SWT.NONE);
    setText(hairs, figure.getPerson().getHairs());
    hairs.setTextLimit(50);
    hairs.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
    hairs.setEditable(folderState.isEditable());
    hairs.addListener(SWT.Modify, event -> {
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
        hour.addListener(SWT.Selection, event -> {
          final String text = ((Combo) event.widget).getText();
          final DraconicHourEnum draconicHourEnum = DraconicHourEnum.get(text);
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

  private void createCharacteristics(final Composite parent, final FolderState folderState, final Figure figure) {
    final Composite characPanel = new Composite(parent, SWT.NONE);
    characPanel.setLayout(new GridLayout(4, false));

    figure.getCharValues().forEach(charValue -> {
      final Label label = new Label(characPanel, SWT.NONE);
      label.setText(getLabel(charValue.getCharacteristic().getMessageCode()));
      final Label text = new Label(characPanel, SWT.BORDER);
      text.setText(String.valueOf(charValue.getValue()));
      final GridData gridData = new GridData();
      gridData.widthHint = 30;
      text.setLayoutData(gridData);
      final Button minus = new Button(characPanel, SWT.PUSH);
      minus.setText("-");
      minus.addListener(SWT.Selection, event -> {
        text.setText(decrement(figure, charValue, text.getText()));
        text.getText();
      });
      final Button plus = new Button(characPanel, SWT.PUSH);
      plus.setText("+");
      plus.addListener(SWT.Selection, event -> {
        text.setText(increment(figure, charValue, text.getText()));
        text.getText();
      });
    });

  }

  private void setText(final Text text, final String value) {
    if (value != null) {
      text.setText(value);
    }
  }

  private String increment(final Figure figure, final CharValue charValue, final String text) {
    final int value = Integer.valueOf(text);
    final int newValue = this.characteristicService.increment(figure, charValue.getCharacteristic(), value);
    charValue.setValue(newValue);
    return String.valueOf(newValue);
  }

  private String decrement(final Figure figure, final CharValue charValue, final String text) {
    final int value = Integer.valueOf(text);
    final int newValue = this.characteristicService.decrement(figure, charValue.getCharacteristic(), value);
    charValue.setValue(newValue);
    return String.valueOf(newValue);
  }
}
