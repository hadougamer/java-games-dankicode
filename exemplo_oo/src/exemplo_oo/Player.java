package exemplo_oo;

public class Player {
	public int life = 100;
	
	public void atacarInimigo( Inimigo inimigo ) {
		inimigo.life--;
	}
}
