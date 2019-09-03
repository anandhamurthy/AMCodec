package amcodec.com.amendecoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class EnDecoder {

    public static void ConvertToBits(StringBuilder sb, byte b) {
        for (int i = 0; i < Byte.SIZE; i++) {
            sb.append('0' + ((b >> (Byte.SIZE - i - 1))) & 1);
        }
    }

    public static String AMEncode(String s) {
        byte[] sdata = s.getBytes(US_ASCII);
        StringBuilder sb = new StringBuilder(sdata.length * (Byte.SIZE + 1));
        for (int i = 0; i < sdata.length; i++) {
            if (i != 0) {
                sb.append(' ');
            }
            byte b = sdata[i];
            ConvertToBits(sb, b);
        }
        return sb.toString();
    }

    public static String AMDecode(String bs) {
        byte[] sdata = new byte[(bs.length() + 1) / (Byte.SIZE + 1)];
        Pattern bytegets = Pattern.compile("([01]{8})(?: |$)");
        Matcher bytegetsFinder = bytegets.matcher(bs);
        int offset = 0, i = 0;
        while (bytegetsFinder.find()) {
            if (bytegetsFinder.start() != offset) {
                throw new IllegalArgumentException();
            }
            sdata[i++] = (byte) Integer.parseInt(bytegetsFinder.group(1), 2);

            offset = bytegetsFinder.end();
        }
        if (offset != bs.length()) {
            throw new IllegalArgumentException();
        }
        return new String(sdata, US_ASCII);
    }

}