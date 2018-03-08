package com.example.customer.controller;

import java.io.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.FileCopyUtils;
import org.w3c.dom.Document;

public class CustomerController {
//
//    public static void main(String[] args) throws Throwable {
//        PoiWord07ToHtml();
//    }

    public static void PoiWord03ToHtml() throws Throwable{
        final String path = "C:\\";
        final String file = "U圈U点城市代理商后台系统需求说明书V0.2.doc";
        InputStream input = new FileInputStream(path + file);
        HWPFDocument  wordDocument = new HWPFDocument(input);


        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            @Override
            public String savePicture(byte[] bytes, PictureType pictureType, String s, float v, float v1) {
                return s;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {
                    pic.writeImageContent(new FileOutputStream(path
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toByteArray());
        FileCopyUtils.copy(content.getBytes(),new File(path, "word\\1.html"));
    }

    public static void PoiWord07ToHtml () throws IOException{

        String path= "C:\\";
        String file = "C:\\U圈U点城市代理商后台系统需求说明书V0.2.docx";
        String file2 ="C:\\1.html";
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {
                //读取文档内容
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

                File imageFolderFile = new File(path);
                //加载html页面时图片路径
                XHTMLOptions options = XHTMLOptions.create().URIResolver( new BasicURIResolver("./"));
                //图片保存文件夹路径
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                OutputStream out = new FileOutputStream(new File(file2));
                XHTMLConverter.getInstance().convert(document, out, options);
                out.close();
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }

    }
}
