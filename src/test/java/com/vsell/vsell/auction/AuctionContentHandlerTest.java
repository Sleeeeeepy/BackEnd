package com.vsell.vsell.auction;

import com.vsell.vsell.auction.domain.AuctionContentHandler;
import com.vsell.vsell.auction.domain.ImageType;
import com.vsell.vsell.auction.domain.exception.AuctionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuctionContentHandlerTest {
    private final AuctionContentHandler auctionContentHandler;
    private final List<String> imgPath = new ArrayList<>();
    private final List<String> invalidPath = new ArrayList<>();

    private String ret;

    @Value("${vsell.auction.img.path}")
    private String CHANGE_IMG_PATH;

    public AuctionContentHandlerTest(@Autowired AuctionContentHandler auctionContentHandler) {
        this.auctionContentHandler = auctionContentHandler;
        imgPath.add("D:/Desktop/12345.PNG");
        imgPath.add("D:/Desktop/사진.jpg");

        invalidPath.add("D:/Desktop/자기소개서.pdf");
    }

    @AfterEach
    public void deleteFile() {
        if (ret == null) {
            return;
        }
        List<String> paths = getHtmlImgAttr(ret);
        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    @DisplayName("정상적으로 처리하는 지 테스트")
    public void test() throws IOException {
        List<MultipartFile> imgs = getFiles(imgPath);

        String content = "<h1>\"asdf\"</h1><img src=\"1.png\"><img src=\"2.jpg\">";
        List<String> originalAttr = getHtmlImgAttr(content);

        ret = auctionContentHandler.processContent(content, imgs);
        List<String> afterAttr = getHtmlImgAttr(ret);


        for (int i = 0; i < originalAttr.size(); i++) {
            System.out.printf("%s -> %s%n", originalAttr.get(i), afterAttr.get(i));
            File changedFile = new File(CHANGE_IMG_PATH + "/" + afterAttr.get(i));
            assertTrue(changedFile.exists());
            assertNotEquals(originalAttr.get(i), afterAttr.get(i));
        }
    }

    @Test
    @DisplayName("content의 이미지 개수와 실제 파일의 이미지 개수 다른 경우")
    public void differentFile() throws IOException {
        List<MultipartFile> imgs = getFiles(imgPath);

        String content = "<img src=\"1\">";


        assertThrows(AuctionException.class, () -> auctionContentHandler.processContent(content, imgs));

    }

    @Test
    @DisplayName("이미지 파일이 아닌 다른 파일이 들어올 경우 테스트")
    public void invalidFile() throws IOException {
        String content = "<img src=\"1\">";


        for (String fileName : invalidPath) {
            assertThrows(AuctionException.class, () -> ImageType.findByValue(fileName));
        }
    }

    @Test
    @DisplayName("XSS 방지 테스트")
    public void XSSTest() throws IOException {
        List<MultipartFile> imgs = getFiles(imgPath);
        String content = "<script>console.log(\"hi\");</script></h1><img src=\"1.png\"><img src=\"2.jpg\">";
        assertThrows(AuctionException.class,()->auctionContentHandler.processContent(content,imgs));
    }


    private List<MultipartFile> getFiles(List<String> files) throws IOException {
        List<MultipartFile> ret = new ArrayList<>();
        for (String path : files) {
            File file = new File(path);
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", path, getExtensionFromPath(path).getMediaType().toString(), input);
            ret.add(multipartFile);
        }

        return ret;
    }

    private ImageType getExtensionFromPath(String path) {
        StringBuilder extension = new StringBuilder(path.substring(path.lastIndexOf(".") + 1));

        return ImageType.findByValue(extension.toString());
    }

    private List<String> getHtmlImgAttr(String content) {
        List<String> ret = new ArrayList<>();

        Document doc = Jsoup.parse(content);
        Elements elements = doc.getElementsByTag("img");

        for (Element element : elements) {
            ret.add(element.attr("src"));
        }
        return ret;
    }


}
