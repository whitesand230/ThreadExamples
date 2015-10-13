package com.intheory.threads;

public class SynchronizationDemo2 {

	public static void main(String[] args){
		FinTrans_2 ft = new FinTrans_2();
		TransThread_2 tt1 = new TransThread_2(ft, "Deposit Thread");
		TransThread_2 tt2 = new TransThread_2(ft, "Withdrawal Thread");
		tt1.start();
		tt2.start();
	}
}

class FinTrans_2{
	private String transName;
	private double amount;
	synchronized void update(String transName, double amount)
	{
		this.transName = transName;
		this.amount = amount;
		System.out.println(this.transName + " " + this.amount);
	}
}

class TransThread_2 extends Thread
{
	private FinTrans_2 ft;
	TransThread_2(FinTrans_2 ft, String name){
		super(name);
		this.ft = ft;
	}
	public void run(){
		for(int i = 0; i < 20; i++)
		{
			if(getName().equals("Deposit Thread"))
				ft.update("Deposit", 2000.0);
			else
				ft.update("Withdrawal", 250.0);
		}
	}
}
