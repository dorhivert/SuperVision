package model;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import presenter.CommunicationProperties;

public class MyModel implements Model{


	public MyModel(TCPIPServer serv) 
	{
		serv = new TCPIPServer(5400, ch);
		final Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				serv.startServer(numOfClients);
			}
		});
		t.start();
		
	}

	@Override
	public void setController(Controller c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
