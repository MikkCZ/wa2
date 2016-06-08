package cz.cvut.fel.stankmic.wa2.rabbit;

import lombok.Getter;

import java.io.UnsupportedEncodingException;

public class MyMessage {

    private static final String DELIMITER = ",";

    @Getter
    private final int id;

    @Getter
    private final String payload;

    public MyMessage(int id, String payload) {
        this.id = id;
        this.payload = payload;
    }

    public MyMessage(byte[] bytes) throws UnsupportedEncodingException {
        String[] s = new String(bytes, "UTF-8").split(DELIMITER);
        this.id = Integer.parseInt(s[0]);
        this.payload = s[1];
    }

    public byte[] getBytes() {
        return this.toString().getBytes();
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, ""+this.id, this.payload);
    }

}
