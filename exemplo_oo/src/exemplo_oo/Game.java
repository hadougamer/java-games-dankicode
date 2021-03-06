package exemplo_oo;

public class Game {
	private Player player;
	private Inimigo inimigo;
	
	public Game() {
		//player = new Player();
		if ( player == null ) {
			System.out.println("Player não inicializado!");
			player = new Player();
		}
		
		if ( player instanceof Player ) {
			System.out.println("Player é um Player.");
		}
		
		inimigo = new Inimigo();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Inimigo getInimigo() {
		return inimigo;
	}
	
	public static void main( String[] args ) {
		Game game = new Game();
		Player player = game.getPlayer();
		Inimigo inimigo = game.getInimigo();
		
		System.out.println(inimigo.life); 
		player.atacarInimigo(inimigo);
		System.out.println(inimigo.life);
	}
}
