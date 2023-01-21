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
	int selector = 0;

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

		Pokemon ceruledge = new Ceruledge("Ceruledge", 60, new Texture("pokemon/Ceruledge.png"), new float[]{500f,500f});
		Pokemon mimikyu = new Mimikyu("Mimikyu", 35, new Texture("pokemon/Mimikyu.png"), new float[]{900f,900f});
		Pokemon wooper = new Wooper("Wooper", 15, new Texture("pokemon/Wooper.png"), new float[]{200f,200f});

		pokeList.add(ceruledge);
		pokeList.add(mimikyu);
		pokeList.add(wooper);
		System.out.println(String.format("Now controlling: %s", pokeList.get(selector).getName()));
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

		if (Gdx.input.isKeyJustPressed(SWAP)){
			selector++;
			if (selector == pokeList.size()){
				selector = 0;
			}
			System.out.println(String.format("Now controlling: %s", pokeList.get(selector).getName()));
		}

		pokeList.get(selector).update(pokeList.get(selector),controls);

		for (Pokemon p : pokeList) {
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
