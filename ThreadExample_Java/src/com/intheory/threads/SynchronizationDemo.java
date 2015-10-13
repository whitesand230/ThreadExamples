package com.intheory.threads;

public class SynchronizationDemo {

	public static void main(String[] args){
		FinTrans ft = new FinTrans();
		TransThread tt1 = new TransThread(ft, "Deposit Thread");
		TransThread tt2 = new TransThread(ft, "Withdrawal Thread");
		tt1.start();
		tt2.start();
	}
}

class FinTrans{
	public static String transName;
	public static double amount;
	public static int dcount = 0;
	public static int wcount = 0;
}

class TransThread extends Thread{
	private FinTrans ft;
	TransThread (FinTrans ft, String name){
		super (name);
		this.ft = ft;
	}
	
	public void run(){
		for (int i = 0; i < 20; i++){
			if(getName().equals("Deposit Thread")){
				synchronized(ft){
					ft.transName="Deposit";
					try{
						Thread.sleep((int) (Math.random() * 1000));
					}catch (InterruptedException e){}
					ft.amount = 2000.0;
					ft.dcount++;
					System.out.println(ft.transName + " " + ft.amount);
					System.out.println("Deposit count is: " + ft.dcount);
				}
			}
			else {
				synchronized(ft){
					ft.transName = "Withdrawal";
					try{
						Thread.sleep((int) (Math.random() * 1000));
					}catch (InterruptedException e) {}
					ft.amount = 250.0;
					ft.wcount++;
					System.out.println(ft.transName + " " + ft.amount);
					System.out.println("Withdrawal count is: " + ft.wcount);
				}
			}
		}
	}
}