package day18;

class MyThread2 implements Runnable{

	@Override
	public void run() {
		// 반드시 run() 메서드를 구현해야 함.
		for(int i=0; i<200; i++) {
			System.out.println(i+"번째 "+Thread.currentThread().getName());
		}
	}
	
}

public class ThreadEx02 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Runnable을 구현하는 방법
		MyThread2 mth = new MyThread2();
		Thread th1 = new Thread(mth);
		th1.start();
		
		th1.join(); //기다려
		
		Thread th2 = new Thread(new MyThread2());
		th2.start();
		
		th2.join();
		
		System.out.println("main End");
	}

}
