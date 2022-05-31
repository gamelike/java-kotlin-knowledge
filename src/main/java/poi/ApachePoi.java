package poi;

import lombok.SneakyThrows;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * document: <a href="https://poi.apache.org/apidocs/dev/index.html">apache poi</a>
 *
 * @author violet.
 */
public class ApachePoi {

    @SneakyThrows
    @Test
    public void create_word() {
        String fileName = "/home/zhao/test.docx";

        try (XWPFDocument document = new XWPFDocument()) {
            // Write the Document in file system
            XWPFParagraph paragraph = document.createParagraph();
            // 设置段落居中
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            // 创建段落
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setItalic(true);
            run.setFontSize(22);
            run.setFontFamily("New Roman");
            run.setText("Hello World!");

            try (var out = new java.io.FileOutputStream(fileName)) {
                document.write(out);
            }

            File file = new File(fileName);
            Assert.assertTrue(file.exists());
            Assert.assertTrue(file.delete());
        }
    }

    @SneakyThrows
    @Test
    public void create_paragraph() {
        String fileName = "/home/zhao/test.docx";

        try (XWPFDocument document = new XWPFDocument()) {
            // Write the Document in file system
            XWPFParagraph paragraph = document.createParagraph();
            // 设置段落居中
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            // 创建段落
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setItalic(true);
            run.setFontSize(22);
            run.setFontFamily("New Roman");
            run.setText("Create Word in Java by Apache Poi");

            XWPFParagraph p2 = document.createParagraph();

            // 创建段落
            XWPFRun r2 = p2.createRun();
            r2.setText("This is a new paragraph that has embossed in Java by Apache Poi.");
            r2.setColor("FF0000");
            r2.setEmbossed(true);

            XWPFParagraph p3 = document.createParagraph();
            XWPFRun r3 = p3.createRun();
            // 文字上的删除线
            r3.setStrikeThrough(true);
            r3.setText("This is second paragraph that has strike through.");

            XWPFParagraph p4 = document.createParagraph();
            // 此元素指定消费者是否应通过将单词分成两行（在字符级别中断）
            // 或将单词移动到下一行（在单词级别中断）来中断超出行文本范围的拉丁文本
            p4.setWordWrapped(true);
            // new page break;
            p4.setPageBreak(true);
            // 指定父段落的附加缩进.
            p4.setIndentationFirstLine(500);

            XWPFRun r4 = p4.createRun();
            r4.setText("This is third paragraph that has new page break.");
            r4.setFontSize(20);
            // 斜体
            r4.setItalic(true);
            r4.addBreak();
            r4.setText("This is fourth paragraph that has indentation.");
            r4.addBreak();
            r4.setText("This is fifth paragraph that has indentation.");


            try (var out = new java.io.FileOutputStream(fileName)) {
                document.write(out);
            }

            File file = new File(fileName);
            Assert.assertTrue(file.exists());
            Assert.assertTrue(file.delete());
        }
    }

    @SneakyThrows
    @Test
    public void document_footer_and_header() {
        String fileName = "/home/zhao/test.docx";

        try (XWPFDocument document = new XWPFDocument()) {
            // Write the Document in file system
            XWPFParagraph p = document.createParagraph();
            XWPFRun r = p.createRun();
            r.setBold(true);
            r.setFontSize(30);
            r.setText("Create document with header and footer!");

            // next page
            XWPFParagraph p2 = document.createParagraph();
            p2.setPageBreak(true);
            p2.setWordWrapped(true);

            XWPFRun r2 = p2.createRun();
            r2.setFontSize(40);
            r2.setItalic(true);
            r2.setText("This is a new paragraph that has new page break.");

            // document header and footer
            XWPFHeader head = document.createHeader(HeaderFooterType.DEFAULT);
            head.createParagraph().createRun().setText("This is a header.");

            XWPFFooter foot = document.createFooter(HeaderFooterType.DEFAULT);
            foot.createParagraph().createRun().setText("This is a footer.");

            try (var out = new java.io.FileOutputStream(fileName)) {
                document.write(out);
            }

            File file = new File(fileName);
            Assert.assertTrue(file.exists());
            Assert.assertTrue(file.delete());
        }
    }

}
