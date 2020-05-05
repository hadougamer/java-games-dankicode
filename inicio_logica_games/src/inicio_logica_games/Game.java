package inicio_logica_games;

import java.util.ArrayList;

public class Game implements Runnable {
	private boolean isRunning;
	private Thread thread;
	private ArrayList<Entidade> entidades = new ArrayList<>();
	
	public Game() {
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		
		for(int i=0; i<entidades.size(); i++ ) {
			//Entidade e = entidades.get(i);
			System.out.println("Hey");
		}
	}
	
	public static void main ( String[] args ) {
		Game game = new Game();
		game.start();
	}
	
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	// Update every loop cycle
	public void tick() {
		//System.out.println("tick ...");
	}
	
	// Display things
	public void render() {
		//System.out.println("render  ...");
	}
	
	@Override
	public void run() {
		while( isRunning ) {
			tick();
			render();
			/*
			// 60 fps
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
		}
	}
}
