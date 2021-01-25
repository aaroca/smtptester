package com.aaroca.smtptester.ui.components;

import static com.aaroca.smtptester.utils.Constants.Ui.DEFAULT_SIZE;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.commons.lang3.StringUtils;

public class FileChooserField extends JComponent {

  private JLabel label;
  private JTextField path;
  private JButton selectButton;
  private JFileChooser browser;
  private File selectedFile;

  public FileChooserField(String label) {
    this.label = new JLabel(label);
    this.path = new JTextField(DEFAULT_SIZE);
    this.path.setEnabled(false);
    this.selectButton = new JButton("Select");
    this.selectButton.addActionListener(new FileChooserActionListener(selectButton));
    this.browser = new JFileChooser();
    this.browser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    init();
  }

  private void init() {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    setAlignmentX(Component.LEFT_ALIGNMENT);

    addFields();
  }

  private void addFields() {
    if (StringUtils.isNotEmpty(label.getText())) {
      add(label);
    }

    add(path);
    add(selectButton);
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    label.setEnabled(enabled);
    selectButton.setEnabled(enabled);
  }

  public void setLabel(String label) {
    this.label.setText(label);
  }

  public File getFile() {
    return selectedFile;
  }

  public void clear() {
    path.setText(StringUtils.EMPTY);
  }

  private class FileChooserActionListener implements ActionListener {

    private Component parent;

    private FileChooserActionListener(Component parent) {
      this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      if (browser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
        selectedFile = browser.getSelectedFile();
        path.setText(selectedFile.getAbsolutePath());
      }
    }
  }
}
