package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.util.ArrayList;

class Pokemon{
	private String name;
	private String[] type;
	private int level;
	private Texture sprite;

	private float xpos;
	private float ypos;
	private float speed;
	private float base = 5f;

	public Pokemon(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Texture getSprite() {
		return sprite;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public float getXpos() {
		return xpos;
	}

	public void setXpos(float xpos) {
		this.xpos = xpos;
	}

	public float getYpos() {
		return ypos;
	}

	public void setYpos(float ypos) {
		this.ypos = ypos;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void control(int[] controls){
		if (Gdx.input.isKeyPressed(controls[0])){ // move left
			this.setXpos(this.xpos + -this.base * this.speed);
		}
		if (Gdx.input.isKeyPressed(controls[1])){ // move right
			this.setXpos(this.xpos + this.base * this.speed);
		}
		if (Gdx.input.isKeyPressed(controls[2])){ // move up
			this.setYpos(this.ypos + this.base * this.speed);
		}
		if (Gdx.input.isKeyPressed(controls[3])){ // move down
			this.setYpos(this.ypos + -this.base * this.speed);
		}
	}
}

class Ceruledge extends Pokemon{
	public Ceruledge(String n, String[] t, int lvl, Texture spr, float[] xy){
		this.setName(n);
		this.setType(t);
		this.setLevel(lvl);
		this.setSprite(spr);
		this.setSpeed(8f);
		this.setXpos(xy[0]);
		this.setYpos(xy[1]);
		System.out.println(String.format("A %s has spawned!", this.getName()));
	}

	public void ember(){
		System.out.println(String.format("&s used Ember!", this.getName()));
	}
}

class Mimikyu extends Pokemon{
	public Mimikyu(String n, String[] t, int lvl, Texture spr, float[] xy){
		this.setName(n);
		this.setType(t);
		this.setLevel(lvl);
		this.setSprite(spr);
		this.setSpeed(10f);
		this.setXpos(xy[0]);
		this.setYpos(xy[1]);
		System.out.println(String.format("A %s has spawned!", this.getName()));
	}

	public void ember(){
		System.out.println(String.format("&s used Shadow Sneak!", this.getName()));
	}
}

class Wooper extends Pokemon{
	public Wooper(String n, String[] t, int lvl, Texture spr, float[] xy){
		this.setName(n);
		this.setType(t);
		this.setLevel(lvl);
		this.setSprite(spr);
		this.setSpeed(5f);
		this.setXpos(xy[0]);
		this.setYpos(xy[1]);
		System.out.println(String.format("A %s has spawned!", this.getName()));
	}

	public void ember(){
		System.out.println(String.format("&s used Water Gun!", this.getName()));
	}
}

public class main extends ApplicationAdapter {
	final int MOVEUP = Input.Keys.W;
	final int MOVEDOWN = Input.Keys.S;
	final int MOVELEFT = Input.Keys.A;
	final int MOVERIGHT = Input.Keys.D;
	final int SWAP = Input.Keys.G;
	final int RSPECIAL = Input.Keys.SPACE;
	final int[] controls = {MOVELEFT,MOVERIGHT,MOVEUP,MOVEDOWN};

	SpriteBatch pokeBatch, bgBatch;
	Texture bg;
	ArrayList<Texture> imgs = new ArrayList<Texture>();
	String[] assets;
	ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>();

	/*public void loadTextures(){
		File folder = new File(".");
		for (int i = 0; i<folder.listFiles().length; i++) {
			imgs.add(new Texture(folder.listFiles()[i].getName()));
		}
		for (Texture s : imgs){
			System.out.println(s.toString().substring(0, s.toString().indexOf(".")));
		}
	}*/

	@Override
	public void create () {
		//loadTextures();
		bgBatch = new SpriteBatch();

		bg = new Texture("textures/grass2.png");
		bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat);

		pokeBatch = new SpriteBatch();

		Pokemon ceruledge = new Ceruledge("Ceruledge", new String[]{"Fire","Ghost"}, 60, new Texture("pokemon/Ceruledge.png"), new float[]{500f,500f});
		Pokemon mimikyu = new Mimikyu("Mimikyu", new String[]{"Ghost","Fairy"}, 35, new Texture("pokemon/Mimikyu.png"), new float[]{900f,900f});
		Pokemon wooper = new Wooper("Wooper", new String[]{"Water","Ground"}, 15, new Texture("pokemon/Wooper.png"), new float[]{200f,200f});

		pokeList.add(ceruledge);
		pokeList.add(mimikyu);
		pokeList.add(wooper);
	}

	@Override
	public void render () {
		int sWidth = Gdx.graphics.getWidth();
		int sHeight = Gdx.graphics.getHeight();
		Gdx.gl.glViewport(0, 0,sWidth, sHeight);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		bgBatch.begin();
		int sourceX = 0;
		sourceX += bg.getWidth()-5;
		bgBatch.draw(bg,0,0,sourceX, 0, sWidth, sHeight);
		bgBatch.end();

		pokeBatch.begin();
		for (Pokemon p : pokeList) {
			p.control(controls);
			pokeBatch.draw(p.getSprite(), p.getXpos(), p.getYpos());
		}

		pokeBatch.end();
	}
	
	@Override
	public void dispose () {
		pokeBatch.dispose();
		for (Texture sprite : imgs) {
			sprite.dispose();
		}
	}
}
