package eu.andycraftz.flappybirdremake.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    public static final Sound hit = new Sound("hit.wav");
    public static final Sound click = new Sound("wing.wav");
    public static final Sound point = new Sound("punkt.wav");
    public static final Sound death = new Sound("tod.wav");
    public static final Sound swooshing = new Sound("swooshing.wav");

    private final AudioClip clip;

    private Sound(String name) {
        clip = Applet.newAudioClip(getClass().getClassLoader().getResource(name));
    }

    public void play() {
        clip.play();
    }
}