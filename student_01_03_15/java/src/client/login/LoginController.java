package client.login;

import client.base.*;
import client.misc.*;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import facade.MasterManager;

/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;

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
		boolean success = MasterManager.getInstance().login(username, password);

		// TODO: log in user

		// If log in succeeded
		if(success)
		{
			getLoginView().closeModal();
			loginAction.execute();
		}
		else
		{
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
		boolean success = false;
		if(password.contentEquals(password2))
		{
			success = MasterManager.getInstance().register(username, password2);
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
}
