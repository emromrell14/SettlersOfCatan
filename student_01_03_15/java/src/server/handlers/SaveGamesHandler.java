package server.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import server.IServer;
import server.JSON.SaveGamesRequest;

public class SaveGamesHandler extends Handler
{
	public SaveGamesHandler(IServer server)
	{
		super.server = server;
	}

	/**
	 * @pre Request != null
	 * @post Saves the current state of the specified game to a file.
	 * 
	 * @param req	The Request object created from the Proxy's request.
	 * @return 		The Response created as a result of the given Request.
	 */
	@Override
	public Response processRequest(Request req)
	{
//		System.out.println("Working Directory = " +
//            System.getProperty("user.dir"));
		boolean success = true;
		String body = req.getBody();
		SaveGamesRequest s = SaveGamesRequest.fromJSON(body);
		
		Writer writer = null;

		try 
		{
			//this creates and writes to a file relative to our .jar file in the java/dist directory
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(new File("saves/"+s.getName()+".txt")), "utf-8"));
		    writer.write(server.getGameModelJSON(-1, s.getId()));
		}
		catch (FileNotFoundException e) 
		{
			success = false;
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e)
		{
			success = false;
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			success = false;
			e.printStackTrace();
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch (IOException e) 
			{
				success = false;
				e.printStackTrace();
			}
		}
		
		return success ? new Response(200,"Success") : new Response(400,"Failed - There was an error saving the file");
	}

}
