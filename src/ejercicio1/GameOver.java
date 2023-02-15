package ejercicio1;

import javax.sound.sampled.*;

// Crea un sonido.
public class GameOver {
    /* ? No nos vamos a engañar me lo ha hecho el chat GPT ? */
  public static void sound() {
    // Define the sound wave samples
    int sampleRate = 44100;
    int numSamples = sampleRate;
    double[] samples = new double[numSamples];

    // Generate the sound wave
    for (int i = 0; i < numSamples; i++) {
      samples[i] = Math.sin(2 * Math.PI * i * 440 / sampleRate);
    }

    // Apply an exponential decay to simulate a fading sound
    for (int i = 0; i < numSamples; i++) {
      samples[i] *= Math.pow(0.5, (double) i / (sampleRate / 10));
    }

    // Convert the samples to 16-bit signed integers
    byte[] generatedSound = new byte[2 * numSamples];
    int idx = 0;
    for (int i = 0; i < numSamples; i++) {
      int sample = (int) (samples[i] * 32767);
      generatedSound[idx++] = (byte) (sample & 0xFF);
      generatedSound[idx++] = (byte) ((sample >> 8) & 0xFF);
    }

    // Play the generated sound
    try {
      AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
      SourceDataLine line = AudioSystem.getSourceDataLine(format);
      line.open(format, generatedSound.length);
      line.start();
      line.write(generatedSound, 0, generatedSound.length);
      line.drain();
      line.close();
    } catch (LineUnavailableException ex) {
      ex.printStackTrace();
    }
  }
}