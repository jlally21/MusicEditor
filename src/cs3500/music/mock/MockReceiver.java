package cs3500.music.mock;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * A mock receiver. Construct with a stringbuilder to test contents.
 */
public class MockReceiver implements Receiver {
  private StringBuilder stringBuilder;

  /**
   * Constructor
   *
   * @param stringBuilder the stringBuilder to append to
   */
  public MockReceiver(StringBuilder stringBuilder) {
    this.stringBuilder = stringBuilder;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    try {
      ShortMessage shortMessage = (ShortMessage) message;

      String thisMessage = shortMessage.getCommand() + " " + shortMessage.getChannel
              () + " " + shortMessage.getData1() + " " + shortMessage.getData2() +
              " " + timeStamp + "\n";

      this.stringBuilder.append(thisMessage);
    } catch (Exception e) {
      throw new IllegalArgumentException("Bad message.");
    }
  }

  @Override
  public void close() {
    throw new IllegalArgumentException("Mock receiver");
  }
}
