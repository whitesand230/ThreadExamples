package com.intheory.threads;

public class NoSynchronizationDemo 
{
	public static void main(String[] args){
		FinTrans_NO ft = new FinTrans_NO();
		TransThread_NO tt1 = new TransThread_NO(ft, "Deposit Thread");
		TransThread_NO tt2 = new TransThread_NO(ft, "Withdrawal Thread");
		tt1.start();
		tt2.start();
	}
}

class FinTrans_NO
{
	public static String transName;
	public static double amount;
}
class TransThread_NO extends Thread
{
	private FinTrans_NO ft;
	TransThread_NO(FinTrans_NO ft, String name)
	{
		super(name);
		this.ft = ft;
	}
	public void run()
	{
		for(int i = 0; i < 20; i++){
			if(getName().equals("Deposit Thread"))
			{
				synchronized(this)
				{
					ft.transName = "Deposit";
					try{
						Thread.sleep((int)(Math.random() * 1000));
					}
					catch (InterruptedException e){}
					ft.amount = 2000.0;
					System.out.println(ft.transName + " " + ft.amount);
				}
			}
			else{
				synchronized(this){
					ft.transName = "Withdrawal";
					try{
						Thread.sleep((int)(Math.random()*1000));
					}
					catch(InterruptedException e){}
					ft.amount = 250.0;
					System.out.println(ft.transName + " " + ft.amount);
				}
			}
		}
	}
}