package gui;

import javax.swing.*;
import java.awt.*;

public class FieldBuilder {

    private int width = 400;
    private int height = 30;
    private String font = "Helvetica";
    private int fontSize = 20;
    private int fontType = Font.PLAIN;

    public FieldBuilder() {
    }

    public FieldBuilder width(int w) {
        this.width = w;
        return this;
    }

    public FieldBuilder height(int h) {
        this.height = h;
        return this;
    }

    public FieldBuilder font(String f) {
        this.font = f;
        return this;
    }

    public FieldBuilder fontType(int ft) {
        this.fontType = ft;
        return this;
    }

    public FieldBuilder fontSize(int fs) {
        this.fontSize = fs;
        return this;
    }

    public JTextField build() {
        JTextField field = new JTextField();
        field.setFont(new Font(font, fontType, fontSize));
        field.setPreferredSize(new Dimension(width, height));
        field.setMargin(new Insets(2,2,2,2));
        return field;
    }

}
