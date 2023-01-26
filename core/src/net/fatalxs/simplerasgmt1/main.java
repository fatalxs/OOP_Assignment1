package net.fatalxs.simplerasgmt1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class main extends ApplicationAdapter {
	final int MOVEUP = Input.Keys.W;
	final int MOVEDOWN = Input.Keys.S;
	final int MOVELEFT = Input.Keys.A;
	final int MOVERIGHT = Input.Keys.D;
	final int SWAP = Input.Keys.G;
	final int RSPECIAL = Input.Keys.SPACE;
	final int[] controls = {MOVELEFT,MOVERIGHT,MOVEUP,MOVEDOWN};

	OrthographicCamera cam;
	Viewport viewport;
	SpriteBatch pokeBatch, bgBatch;
	Texture tCeruledge, tMimikyu, tWooper;
	Texture bg;
	ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>();
	ShapeRenderer sr;
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
			p.getSprite().setPosition(p.getDefPos()[0],p.getDefPos()[1]);
			pokeBatch.draw(p.getSprite(), p.getDefPos()[0],p.getDefPos()[1]);
		}
		pokeBatch.end();
	}

	@Override
	public void render () {
		int sWidth = Gdx.graphics.getWidth();
		int sHeight = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 0);

		viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), cam);
		viewport.update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		bgBatch.setProjectionMatrix(cam.projection);
		bgBatch.setTransformMatrix(cam.view);
		pokeBatch.setProjectionMatrix(cam.projection);
		pokeBatch.setTransformMatrix(cam.view);

		cam.update();
		viewport.update(sWidth,sHeight);

		if (sWidth != Gdx.graphics.getWidth() || sHeight != Gdx.graphics.getHeight()){
			System.out.println(String.format("Resolution Updated!\nOld Res:\t%s x %s\nNew Res:\t %s x %s",sWidth
			,sHeight, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
		}

		Gdx.gl.glViewport(0, 0,sWidth, sHeight);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		bgBatch.begin();
		int sourceX = 0;
		sourceX += bg.getWidth()-5;
		bgBatch.draw(bg,0,0,sourceX, 0, sWidth, sHeight);
		bgBatch.end();

		if (Gdx.input.isKeyJustPressed(SWAP)){
			selector++;
			if (selector == pokeList.size()){
				selector = 0;
			}
			System.out.println(String.format("Now controlling: %s", pokeList.get(selector).getName()));
		}

		pokeList.get(selector).update(pokeList, selector, controls);

		// Hitbox Viewer
		/*for (Pokemon p : pokeList) {
			sr = new ShapeRenderer();
			sr.setProjectionMatrix(cam.combined);
			sr.begin(ShapeRenderer.ShapeType.Line);
			Rectangle temp = p.getSprite().getBoundingRectangle();
			sr.setColor(Color.RED);
			sr.rect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
			sr.end();
		}*/

		pokeBatch.begin();


		for (Pokemon p : pokeList) {
			pokeBatch.draw(p.getSprite(), p.getSprite().getX(),p.getSprite().getY());
		}

		pokeBatch.end();

	}
	
	@Override
	public void dispose () {
		pokeBatch.dispose();
		bgBatch.dispose();
	}
}
