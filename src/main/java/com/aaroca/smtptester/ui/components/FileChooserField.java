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

public class FileChooserField extends JComponent implements ActionListener {

  private JLabel label;
  private JTextField path;
  private JButton selectButton;
  private JFileChooser fileBrowser;
  private File selectedFile;

  public FileChooserField(String label) {
    init();
    buildComponents(label);
    addComponents();
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    label.setEnabled(enabled);
    selectButton.setEnabled(enabled);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == selectButton) {
      selectAttachment();
    }
  }

  public void clear() {
    path.setText(StringUtils.EMPTY);
  }

  private void init() {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    setAlignmentX(Component.LEFT_ALIGNMENT);
  }

  private void buildComponents(String label) {
    this.label = new JLabel(label);
    this.path = new JTextField(DEFAULT_SIZE);
    this.path.setEnabled(false);
    this.selectButton = new JButton("Select");
    this.selectButton.addActionListener(this);
    this.fileBrowser = new JFileChooser();
    this.fileBrowser.setFileSelectionMode(JFileChooser.FILES_ONLY);
  }

  private void addComponents() {
    if (StringUtils.isNotEmpty(label.getText())) {
      add(label);
    }

    add(path);
    add(selectButton);
  }

  private void selectAttachment() {
    if (fileBrowser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileBrowser.getSelectedFile();
      path.setText(selectedFile.getAbsolutePath());
    }
  }

  public File getFile() {
    return selectedFile;
  }
}
