package com.sttx.bookmanager.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;

import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.service.IEBookService;
import com.sttx.bookmanager.util.file.NfsFileUtils;

@Controller
@RequestMapping("/view")
public class FileOnlineViewController {
    @Autowired
    private IEBookService eBookService;

    @RequestMapping("/pdfview/{ebookId}")
    public ModelAndView pdfView(@PathVariable("ebookId") String ebookId, Model model, HttpServletResponse response,
            HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView modelAndView = null;
        EBook eBook = eBookService.selectByPrimaryKey(ebookId);
        /**
         * 判断书籍的格式
         */
        String ebookType = eBook.getEbookType();
        if ("doc".equalsIgnoreCase(ebookType)) {
            modelAndView = new ModelAndView("forward:/view/doc2html?eBookFilePath=" + eBook.getEbookPath());
            modelAndView.addObject("eBookFilePath", eBook.getEbookPath());
            modelAndView.addObject("eBook", eBook);
            return modelAndView;
        }
        if ("pdf".equalsIgnoreCase(ebookType)) {
            modelAndView = new ModelAndView("doc_view/tz");
            String urlPath = NfsFileUtils.getNfsUrl();
            int serverPort = request.getServerPort();
            String serverName = request.getServerName();
            urlPath = MessageFormat.format(urlPath, serverName + ":" + serverPort);// 替换{0}
            String encode = URLEncoder.encode(eBook.getEbookPath(), "UTF-8").replaceAll("%2F", "/");
            modelAndView = new ModelAndView("doc_view/tz");
            model.addAttribute("msg", "跳转中请稍后....");
            model.addAttribute("url", urlPath + encode);
            return modelAndView;
        }
        if ("xls".equalsIgnoreCase(ebookType)) {
            modelAndView = new ModelAndView("forward:/view/xls2html?eBookFilePath=" + eBook.getEbookPath());
            modelAndView.addObject("eBookFilePath", eBook.getEbookPath());
            return modelAndView;
        }
        int serverPort = request.getServerPort();
        String serverName = request.getServerName();
        request.setAttribute("serverHost", serverName + ":" + serverPort);
        request.setAttribute("tz", "ebook/selectEBookPages");
        modelAndView = new ModelAndView("doc_view/msg");
        model.addAttribute("msg", "暂不支持该格式在线预览,目前只支持03版doc、xls、pdf");
        return modelAndView;
    }

    @RequestMapping(value = { "xls2html" }, method = { RequestMethod.GET })
    public String xls2html(@RequestParam(value = "eBookFilePath", required = true) final String eBookFilePath,
            HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        String filePath = NfsFileUtils.getNfsUrl() + eBookFilePath;
        String encode = URLEncoder.encode(filePath + eBookFilePath, "UTF-8");
        model.addAttribute("title", eBookFilePath.substring(eBookFilePath.lastIndexOf("-") + 1));
        return "forward:/WEB-INF/jsp/doc_view/xlsview.jsp?filename=" + encode;
        // return modelAndView;
    }

    /**
     * 
     * @Title: doc2html
     * @Description: word文档的解析
     * @param eBook
     * @param eBookFilePath
     * @param response
     * @return
     * @throws Exception
     * @return: ModelAndView
     */
    @RequestMapping(value = { "doc2html" }, method = { RequestMethod.GET })
    public ModelAndView doc2html(@ModelAttribute("eBook") EBook eBook,
            @RequestParam(value = "eBookFilePath", required = true) final String eBookFilePath,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("doc_view/docview");
        String filePath = NfsFileUtils.getNfsUrl() + eBookFilePath;
        InputStream input = NfsFileUtils.readNfsFile2Stream(filePath);
        //
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter;
        wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
                    float heightInches) {
                return "http://demo.javaniu.com/doc2html/" + eBookFilePath + "_" + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {
                    pic.writeImageContent(
                            new FileOutputStream(filePath + eBookFilePath + "_" + pic.suggestFullFileName()));
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
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();

        String content = new String(outStream.toByteArray(), "UTF-8");
        modelAndView.addObject("content", content);
        modelAndView.addObject("title", eBookFilePath.substring(eBookFilePath.lastIndexOf("-") + 1));
        return modelAndView;
    }
}
