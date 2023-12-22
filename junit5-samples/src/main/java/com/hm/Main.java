package com.hm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jsoup parser samples
 * 
 * @author huwenfeng
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 输入HTML字符串  
        //language=HTML
        String html = readContentFromHtml();
        // 使用Jsoup解析HTML  
        Document document = Jsoup.parse(html);
        // 获取所有链接元素  
        Elements linkElements = document.select("img");
        List<String> links = new ArrayList<>();
        for (Element linkElement : linkElements) {
            String src = linkElement.attr("src");
            links.add(src.substring(1, src.length() - 2));
            // System.out.println("链接: " + src.substring(2, src.length() - 2));
        }
        Elements spanElements = document.select("span");
        for (Element spanElement : spanElements) {
            String aClass = spanElement.attr("class");
            if ("errorLink".equals(aClass)) {
                links.add(spanElement.ownText());
                // System.out.println("链接: " + spanElement.ownText());
            }
        }

        // 获取所有链接元素  
        Elements linkElements2 = document.select("a");
        // 遍历链接元素，并获取每个链接对应的<head>标签  
        for (Element linkElement : linkElements2) {
            String href = linkElement.attr("abs:href");
            if (StringUtils.hasText(href)) {
                links.add(href);
                // System.out.println("链接: " + href);
            }
        }

        // 过滤需要的文章和标题
        for (String link : links) {
            if (link.contains("mmbiz.qpic.cn")) {
                System.out.println("Ignore Link=" + link);
                continue;
            }
            Document document1 = Jsoup.connect(link)
                    .ignoreHttpErrors(true)
                    .timeout(2000)
                    // 方便抓取https内容
                    .validateTLSCertificates(false)
                    .get();
            String title = document1.title();
            System.out.println("Fetch Link=" + link + "、" + "Title=" + title);
        }
    }

    public static String readContentFromHtml() {
        // 创建File对象  
        ClassPathResource classPathResource = new ClassPathResource("samples.html");
        try (
                // 创建BufferedReader对象  
                BufferedReader reader = new BufferedReader(new FileReader(classPathResource.getFile()))) {
            // 创建一个字符串来存储文件内容  
            StringBuilder content = new StringBuilder();
            String line;
            // 逐行读取文件内容并添加到字符串中  
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator()); // 添加换行符  
            }
            // 将文件内容转换为字符串并输出  
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
