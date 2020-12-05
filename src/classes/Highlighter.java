package classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Highlighter {
    private final Document document;
    private int line = 1;

    public Highlighter() {
        String html = "<html><head><title>Highlighter</title></head>"
                + "<body style=\"background-color:#8a8a8a\">"
                + "<div id='tokens'> <p>" + "<span style=" + "\"color:lightgrey\">" + line + "</span>" + "&nbsp;" + "</p>"
                + "</body></html>";
        document = Jsoup.parse(html);
    }

    public Document getDocument() {
        return document;
    }

    public void addHtmlText(String text, Type type) {
        Element p = document.getElementsByTag("p").first();
        Element span = new Element("span");
        span.attr("style", getStyle(type));
        span.append(getRightTextFormat(text));
        p.append(span.outerHtml());
    }

    private String newLineWithGreyStyle(int line) {
        return "<br><span style=" + "\"color:lightgrey\">" + line + "</span>";
    }

    private String getRightTextFormat(String text) {
        text = text.replaceAll(" ", "&nbsp;")
                .replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
                .replaceAll("\r", "");
        while (text.contains("\n")) {
            text = text.replaceFirst("\n", newLineWithGreyStyle(++line));
        }
        return text;
    }

    private String getStyle(Type type) {
        String color = "color:";
        String font = "font-weight:";
        switch (type) {
            case BOOLEAN:
            case RESERVED_KEYWORD:
                color += "blue";
                font += "bold";
                break;
            case IDENTIFIER:
                color += "violet";
                font += "normal";
                break;
            case HEX:
            case INTEGER_NUMBER:
                color += "orange";
                font += "normal";
                break;
            case SCIENTIFIC_NOTATION:
            case REAL_NUMBER:
                color += "orange";
                font = "font-style:italic";
                break;
            case STRING:
                color += "green";
                font += "normal";
                break;
            case SPECIAL_CHAR:
                color += "green";
                font = "font-style:italic";
                break;
            case COMMENT:
                color += "yellow";
                font += "normal";
                break;
            case WHITESPACE:
            case OPERATOR_AND_PUNCTUATION:
                color += "black";
                font += "normal";
                break;
            case UNDEFINED:
            default:
                color += "red";
                font += "normal";
                break;
        }
        return color + ";" + font;
    }

    public void writeToHtml(String html, String path) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), StandardCharsets.UTF_8))) {
            writer.write(html);
        }
    }
}
