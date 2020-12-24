package gui;

import javax.swing.*;
import java.awt.*;

public class LabelBuilder {

    private String text;
    private int width = 100;
    private int height = 30;
    private String font = "Helvetica";
    private int fontSize = 20;
    private int fontType = Font.PLAIN;
    private int alignment = JLabel.TRAILING;

    public LabelBuilder(String label) {
        this.text = label;
    }

    public LabelBuilder width(int w) {
        this.width = w;
        return this;
    }

    public LabelBuilder height(int h) {
        this.height = h;
        return this;
    }

    public LabelBuilder font(String f) {
        this.font = f;
        return this;
    }

    public LabelBuilder fontType(int ft) {
        this.fontType = ft;
        return this;
    }

    public LabelBuilder fontSize(int fs) {
        this.fontSize = fs;
        return this;
    }

    public LabelBuilder alignment(int a) {
        this.alignment = a;
        return this;
    }


    public JLabel build() {
        JLabel label = new JLabel(this.text);
        label.setFont(new Font(font, fontType, fontSize));
        label.setPreferredSize(new Dimension(width, height));
        label.setHorizontalAlignment(alignment);
        return label;
    }

}
