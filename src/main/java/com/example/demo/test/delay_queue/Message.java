package com.example.demo.test.delay_queue;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.Data;

@Data
public class Message implements Delayed {

	private int id;
	private String message;
	private long delay;
	
	public Message(int id, String message, long delay) {
		super();
		this.id = id;
		this.message = message;
		this.delay = System.currentTimeMillis() + delay;
	}
	
	void doSomething(){
		System.out.println("--> " + LocalDateTime.now());
	}

	@Override
	public int compareTo(Delayed o) {
		Message msg = (Message) o;
		return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
				: (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
	}

	/**
	 * 剩余到期时间
	 */
	@Override
	public long getDelay(TimeUnit unit) {
//		return unit.convert(this.delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		return this.delay - System.currentTimeMillis();
	}
	
	public static void main(String[] args) {
		System.out.println("--> " + LocalDateTime.now());
		DelayQueue<Message> queue = new DelayQueue<>();
        queue.put(new Message(1, "1st", 3000));
        queue.put(new Message(2, "2st", 6000));
        queue.put(new Message(3, "3st", 9000));
        ExecutorService exec = Executors.newFixedThreadPool(1);  
        exec.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Message message = queue.take();
						if(message != null) {
							message.doSomething();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}); 
        exec.shutdown(); 
	}

}
