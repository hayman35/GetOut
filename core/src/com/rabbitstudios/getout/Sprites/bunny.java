package com.rabbitstudios.getout.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rabbitstudios.getout.GetOut;

public class bunny extends Sprite {

    public World world;
    public Body b2body;

    public bunny (World world){
        this.world = world;
        defineBunny();
    }
    public  void defineBunny(){
        //Shaping our object which will be the bunny later
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ GetOut.PPM,32/ GetOut.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/ GetOut.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);



    }

}
