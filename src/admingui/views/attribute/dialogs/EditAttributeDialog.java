package admingui.views.attribute.dialogs;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import admingui.models.Attribute;
import admingui.utils.AdminGuiStrings;

import net.miginfocom.swing.MigLayout;

public class EditAttributeDialog {

	private JDialog mainDialog;
	private JPanel mainPanel;

	private JLabel name;
	private JLabel type;

	private JTextField attributeName;
	private JComboBox<String> attributesTypes;

	private JButton ok;
	private JButton cancel;

	public EditAttributeDialog(Attribute attribute) {
		initComponents();
	}

	protected void initComponents() {
		initLabels();
		initTexts();
		initButtons();
		initMainPanel();
		initMainDialog();
	}

	protected void initLabels() {
		name = new JLabel("Name:");
		type = new JLabel("Type:");
	}

	protected void initTexts() {
		attributeName = new JTextField(12);
		attributesTypes = new JComboBox<String>(AdminGuiStrings.attributeValuesTypes);
		// attributesTypes.setSelectedItem(attribute.getType().toString());
	}

	protected void initButtons() {
		ok = new JButton("OK");
		ok.setActionCommand("Edit Attribute");
		cancel = new JButton("Cancel");
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("wrap 1", "[][]push[]", "[][]"));
		mainPanel.add(createComponentsPanel(), "cell 2 0,alignx center");
		mainPanel.add(createButtonsPanel(), "cell 2 1 , alignx center");
	}

	protected void initMainDialog() {
		mainDialog = new JDialog();
		mainDialog.setTitle("Edit Selected Attribute");
		mainDialog.setBounds(525, 275, 200, 150);
		mainDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainDialog.setModal(true);
		mainDialog.getContentPane().add(mainPanel);
	}

	protected JPanel createComponentsPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 2", "[][]", "[][]"));
		panel.add(name);
		panel.add(attributeName);
		panel.add(type);
		panel.add(attributesTypes);
		return panel;
	}

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[][]", "push[]"));
		panel.add(ok);
		panel.add(cancel);
		return panel;
	}

	public void setFieldData(Attribute attribute) {
		attributeName.setText(attribute.getName());
		attributesTypes.setSelectedItem(attribute.getType());
	}

	public void addSubmmitListener(ActionListener e) {
		ok.addActionListener(e);
	}

	public Attribute createAttribute() {
		Attribute attribute = new Attribute();
		attribute.setName(attributeName.getText());
		attribute.setType(attributesTypes.getSelectedItem().toString());
		return attribute;
	}

	public boolean isEmptyFieldData() {
		return attributeName.getText().isEmpty();
	}

	public JButton getOk() {
		return ok;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void show() {
		mainDialog.setVisible(true);
	}

	public void dispose() {
		mainDialog.dispose();
	}
}
