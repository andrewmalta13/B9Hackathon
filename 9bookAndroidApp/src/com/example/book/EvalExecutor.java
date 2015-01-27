package com.example.book;

import java.util.concurrent.Executor;

public class EvalExecutor implements Executor{
	
	
	public void execute(Runnable r) {
	     new Thread(r).start();
    }
	
}

