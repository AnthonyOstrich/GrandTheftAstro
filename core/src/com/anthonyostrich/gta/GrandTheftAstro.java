package com.anthonyostrich.gta;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class GrandTheftAstro extends Game {

    SpriteBatch batch;
    BitmapFont font;

	@Override
	public void create() {
        batch = new SpriteBatch();
        Area area = new Area(new Player());
		this.setScreen(area);
        Gdx.input.setInputProcessor(area);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/book.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        generator.dispose();

        area.spawnShip("falcon", new Vector2(0,0), true);
        area.spawnShip("triangle", new Vector2(3,3),4, false);
    }

	@Override
	public void render() {
        super.render();
        batch.begin();
        font.draw(batch, Gdx.graphics.getFramesPerSecond() + " FPS", 5 , 55);
        batch.end();
	}
}
