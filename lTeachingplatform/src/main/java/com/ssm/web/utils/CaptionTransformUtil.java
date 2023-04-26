package com.ssm.web.utils;

import fr.noop.subtitle.model.SubtitleParsingException;
import fr.noop.subtitle.srt.SrtObject;
import fr.noop.subtitle.srt.SrtParser;
import fr.noop.subtitle.vtt.VttWriter;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.lang.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class CaptionTransformUtil {
    public static void srt2vtt(File srtFile, File vttFile,Charset charset, ByteOrderMark byteOrderMark) {
        BOMInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new BOMInputStream(new FileInputStream(srtFile), byteOrderMark);

            SrtParser parser = new SrtParser(charset.name());
            SrtObject subtitle = parser.parse(fileInputStream);

            VttWriter writer = new VttWriter(charset.name());
            fileOutputStream = new FileOutputStream(vttFile);
            writer.write(subtitle, fileOutputStream);
        } catch (IOException | SubtitleParsingException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
