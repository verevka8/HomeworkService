package org.example.fileanalysis.infrastructure.web.dto;

public class WordCLoudRequest {
    private String format;
    private int width;
    private int height;
    private String fontFamily;
    private int fontScale;
    private String scale;
    private String text;

    private WordCLoudRequest(String format, int width, int height, String fontFamily, int fontScale, String scale, String text) {
        this.format = format;
        this.width = width;
        this.height = height;
        this.fontFamily = fontFamily;
        this.fontScale = fontScale;
        this.text = text;
        this.scale = scale;
    }

    public static WordCLoudRequest createRequest(String text){
        return new WordCLoudRequest("png", 1000, 1000,"sans-serif", 15,"linear", text);
    }

    public String getFormat() {
        return format;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontScale() {
        return fontScale;
    }

    public String getScale() {
        return scale;
    }

    public String getText() {
        return text;
    }
}
