package model;

import controller.Controller;

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
