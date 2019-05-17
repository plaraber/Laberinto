package control;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class ControladorAudio {

	public final static String CHOMP = ".\\sonidos\\gs_chomp.wav";
	public final static String COMIDO = ".\\sonidos\\gs_pacmandies.wav";
	public final static String GANO_RATON = ".\\sonidos\\gs_eatfruit.wav";
	public final static String START = ".\\sonidos\\mario-bros game over.wav";
	
	
	public Clip sonido;

	public ControladorAudio() {
		// Creo el clip de audio
		try {
			this.sonido = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void reproducirAudio (String ficheroAudio) {
	      try {
	          File a = new File(ficheroAudio);
	          this.sonido.open(AudioSystem.getAudioInputStream(a));
	          this.sonido.start();
	       } catch (Exception tipoError) {
	          System.out.println("" + tipoError);
	       }
	}

	public void reproducirLoopAudio (String ficheroAudio) {
	      try {
	          File a = new File(ficheroAudio);
	          this.sonido.open(AudioSystem.getAudioInputStream(a));
	          this.sonido.loop(Clip.LOOP_CONTINUOUSLY);
	       } catch (Exception tipoError) {
	          System.out.println("" + tipoError);
	       }
	}

	public void pararAudio () {
	      try {
	    	  this.sonido.close();
	       } catch (Exception tipoError) {
	          System.out.println("" + tipoError);
	       }
	}


}
