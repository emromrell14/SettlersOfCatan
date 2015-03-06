package client.login;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.misc.*;
import facade.IMasterManager;
import facade.MasterManager;

/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController, Observer {

	private IMessageView messageView;
	private IAction loginAction;
	private IMasterManager master;

	/**
	 * LoginController constructor
	 * 
	 * @param view
	 *            Login view
	 * @param messageView
	 *            Message view (used to display error messages that occur during
	 *            the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		this.messageView = messageView;
	}

	public ILoginView getLoginView() {

		return (ILoginView) super.getView();
	}

	public IMessageView getMessageView() {

		return messageView;
	}

	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value
	 *            The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		loginAction = value;
	}

	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {

		return loginAction;
	}

	@Override
	public void start() {

		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		String username = getLoginView().getLoginUsername();
		String password = getLoginView().getLoginPassword();
		boolean success = !username.isEmpty() && !password.isEmpty();
		if(success)
		{
			success = master.login(username, password);
		}

		// TODO: log in user

		// If log in succeeded
		if(success)
		{
//			System.out.println("login succeeded");
			getLoginView().closeModal();
			loginAction.execute();
		}
		else
		{
//			System.out.println("login failed");
			messageView.setTitle("ERROR");
			messageView.setMessage("Invalid Login Credentials");
//			getLoginView().closeModal();
			messageView.showModal();
		}
	}

	@Override
	public void register() {
		String username = getLoginView().getRegisterUsername();
		String password = getLoginView().getRegisterPassword();
		String password2 = getLoginView().getRegisterPasswordRepeat();
		boolean success = !username.isEmpty() && !password.isEmpty() && !password2.isEmpty() 
				&& password.contentEquals(password2) && isAscii(username) && isAscii(password);
		if(success)
		{
			success = master.register(username, password2);
		}
		// TODO: register new user (which, if successful, also logs them in)

		// If register succeeded
		if(success)
		{
			getLoginView().closeModal();
			loginAction.execute();
		}
		else
		{
			messageView.setTitle("ERROR");
			messageView.setMessage("Invalid Register Credentials");
//			getLoginView().closeModal();
			messageView.showModal();
		}
	}

	private boolean isAscii(String word) {
		return (word.matches("[a-zA-Z0-9<,>.?/:;\"\'\\!@#$%^&*()_~`]+"));	
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
