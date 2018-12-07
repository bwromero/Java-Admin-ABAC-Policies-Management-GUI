package admingui.views.user;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import admingui.models.Attribute;
import admingui.models.User;
import admingui.views.NavigationButtonsPanel;
import admingui.views.attribute.shared.AdditionalAttributesPanel;
import admingui.views.user.dialogs.AddUserDialog;
import admingui.views.user.dialogs.AssignClientsDialog;
import admingui.views.user.dialogs.AssignedClientsDialog;
import admingui.views.user.dialogs.EditUserDialog;
import admingui.views.user.dialogs.SearchUserDialog;
import net.miginfocom.swing.MigLayout;

public class UserView {

	private User user;

	@SuppressWarnings("unused")
	private List<Attribute> attributes;

	private JPanel mainPanel;

	private AdditionalAttributesPanel attributesPanel;
	private NavigationButtonsPanel navPanel;

	private JLabel id;
	private JLabel name;
	private JLabel surname;
	private JLabel role;

	private JLabel userId;
	private JLabel userName;
	private JLabel userSurname;
	private JLabel userRole;

	private JButton assignedClients;
	private JButton assignClients;
	private JButton edit;
	private JButton search;

	private AddUserDialog dialogAddUser;
	private EditUserDialog dialogEditUser;
	private SearchUserDialog dialogSearchUser;
	private AssignedClientsDialog dialogAssignedClients;
	private AssignClientsDialog dialogAssignClients;

	public UserView() {
		initComponents();
	}

	protected void initComponents() {
		initPanels();
		initLabels();
		initButtons();
		initDialogs();
		initMainPanel();
	}

	protected void initPanels() {
		attributesPanel = new AdditionalAttributesPanel();
		navPanel = new NavigationButtonsPanel();
	}

	protected void initLabels() {
		id = new JLabel("ID:");
		name = new JLabel("Name:");
		surname = new JLabel("Surname:");
		role = new JLabel("Role:");
		setBold();

		userId = new JLabel();
		userName = new JLabel();
		userSurname = new JLabel();
		userRole = new JLabel();
	}

	protected void initButtons() {
		assignedClients = new JButton("Clients");
		assignClients = new JButton("Assign Clients...");
		search = new JButton("Search...");
		edit = new JButton("Edit...");
	}

	protected void initDialogs() {
		dialogAddUser = new AddUserDialog();
		dialogEditUser = new EditUserDialog();
		dialogSearchUser = new SearchUserDialog();
		dialogAssignedClients = new AssignedClientsDialog();
		dialogAssignClients = new AssignClientsDialog();
	}

	protected void initMainPanel() {
		mainPanel = new JPanel(new MigLayout("", "[][]", "[][grow]push[]"));
		mainPanel.add(createFixedAttributesPanel());
		mainPanel.add(createButtonsPanel(), "wrap");
		mainPanel.add(attributesPanel.getScrollPane(), "span");
		mainPanel.add(navPanel.getMainPanel(), " south");
		mainPanel.setVisible(true);
	}

	protected JPanel createFixedAttributesPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 4", "[][]25[][]", "[][][][]"));
		panel.setBorder(BorderFactory.createTitledBorder("Fixed Attributes"));
		panel.add(id, "cell 0 0,wrap,alignx right");
		panel.add(userId, "cell 1 0");
		panel.add(name, "cell 0 1,alignx right");
		panel.add(userName);
		panel.add(surname, "alignx right");
		panel.add(userSurname);
		panel.add(role, "alignx right");
		panel.add(userRole);
		panel.add(assignedClients, "cell 3 4");
		return panel;
	}

	protected JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new MigLayout("wrap 1", "[]", "[][][][]"));
		panel.add(assignClients, "cell 0 0,aligny top");
		panel.add(search, "cell 0 2");
		panel.add(edit, "cell 0 3");
		return panel;
	}

	protected void setBold() {
		id.setFont(new Font("Tahoma", Font.BOLD, 11));
		name.setFont(new Font("Tahoma", Font.BOLD, 11));
		surname.setFont(new Font("Tahoma", Font.BOLD, 11));
		role.setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	public User getFieldData() {
		User user = new User();
		user.setId(Integer.valueOf(userId.getText()));
		user.setName(userName.getText());
		user.setSurname(userSurname.getText());
		user.setAdmin(userRole.getText());
		return user;
	}

	public void setFixedAttributesData(User user) {
		userId.setText(String.valueOf(user.getId()));
		userName.setText(user.getName());
		userSurname.setText(user.getSurname());
		userRole.setText(user.getRole());
	}

	public void addUserViewButtonsListener(ActionListener e) {
		assignedClients.addActionListener(e);
		assignClients.addActionListener(e);
		search.addActionListener(e);
		edit.addActionListener(e);
		navPanel.getDeleteButton().addActionListener(e);
		navPanel.getAddButton().addActionListener(e);
	}

	public void addNavigationListener(ActionListener e) {
		navPanel.getFirstButton().addActionListener(e);
		navPanel.getPreviosuButton().addActionListener(e);
		navPanel.getNextButton().addActionListener(e);
		navPanel.getLastButton().addActionListener(e);
	}

	public void addCancelListener(ActionListener e) {
		dialogAddUser.addListener(e);
		dialogEditUser.addListener(e);
		dialogAssignedClients.addCancelListener(e);
		// dialogAssignClients.addListener(e);
	}

	public void addDialogsListener(ActionListener e) {
		dialogAddUser.getOk().addActionListener(e);
		dialogEditUser.getOk().addActionListener(e);
		dialogSearchUser.getSearch().addActionListener(e);
		dialogSearchUser.getAll().addActionListener(e);
		dialogSearchUser.getEdit().addActionListener(e);
		dialogSearchUser.getDelete().addActionListener(e);
		dialogSearchUser.getCancel().addActionListener(e);
	}

	public JComponent getMainPanel() {
		return this.mainPanel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return Integer.valueOf(user.getId());
	}

	public String getUserPassword() {
		return user.getPassword();
	}

	public AddUserDialog getDialogAddUser() {
		return dialogAddUser;
	}

	public EditUserDialog getDialogEditUser() {
		return dialogEditUser;
	}

	public SearchUserDialog getDialogSearchUser() {
		return dialogSearchUser;
	}

	public AssignedClientsDialog getDialogAssignedClients() {
		return dialogAssignedClients;
	}

	public AssignClientsDialog getDialogAssignClients() {
		return dialogAssignClients;
	}

	public NavigationButtonsPanel getNavButtonsPanel() {
		return this.navPanel;
	}

	public AdditionalAttributesPanel getAdditionalAttributesPanel() {
		return this.attributesPanel;
	}
}