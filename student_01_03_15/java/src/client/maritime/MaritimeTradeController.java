package client.maritime;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.*;
import client.base.*;
import facade.IMasterManager;
import facade.MasterManager;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, Observer {

	private IMaritimeTradeOverlay tradeOverlay;
	private IMasterManager master;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay)
	{
		
		super(tradeView);
		this.master = MasterManager.getInstance();
		this.master.getModelManager().addObserver(this);
		setTradeOverlay(tradeOverlay);
	}
	
	public IMaritimeTradeView getTradeView() 
	{
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay()
	{
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay)
	{
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade()
	{
		
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade()
	{

		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade()
	{

		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource)
	{

	}

	@Override
	public void setGiveResource(ResourceType resource) 
	{

	}

	@Override
	public void unsetGetValue()
	{

	}

	@Override
	public void unsetGiveValue() 
	{

	}

	@Override
	public void update(Observable o, Object arg) 
	{
		
	}

}

