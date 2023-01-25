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
	Texture tCeruledge, tMimikyu, tWooper;
	Texture bg;
	ArrayList<Texture> imgs = new ArrayList<Texture>();
	String[] assets;
	ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>();
	int selector = 0;

	public void loadTextures(){
		tCeruledge = new Texture("pokemon/Ceruledge.png");
		tMimikyu = new Texture("pokemon/Mimikyu.png");
		tWooper = new Texture("pokemon/Wooper.png");
	}

	@Override
	public void create () {
		loadTextures();
		bgBatch = new SpriteBatch();

		bg = new Texture("textures/grass2.png");
		bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.MirroredRepeat);

		pokeBatch = new SpriteBatch();

		Pokemon ceruledge = new Ceruledge("Ceruledge", 60, new Sprite(tCeruledge), new float[]{500f,500f});
		Pokemon mimikyu = new Mimikyu("Mimikyu", 35, new Sprite(tMimikyu), new float[]{900f,900f});
		Pokemon wooper = new Wooper("Wooper", 15, new Sprite(tWooper), new float[]{200f,200f});

		pokeList.add(ceruledge);
		pokeList.add(mimikyu);
		pokeList.add(wooper);
		System.out.println(String.format("Now controlling: %s", pokeList.get(selector).getName()));

		pokeBatch.begin();
		for (Pokemon p : pokeList) {
			p.getSprite().setPosition(p.getXpos(),p.getYpos());
			pokeBatch.draw(p.getSprite(), p.getXpos(),p.getYpos());
		}
		pokeBatch.end();
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
			pokeBatch.draw(p.getSprite(), p.getSprite().getX(),p.getSprite().getY());
		}

		pokeBatch.end();
	}
	
	@Override
	public void dispose () {
		pokeBatch.dispose();
		bgBatch.dispose();
		for (Texture sprite : imgs) {
			sprite.dispose();
		}
	}
}
