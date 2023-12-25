package com.hm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hm.Main.readContentFromHtml;

/**
 * MainV2.
 *
 * @author huwenfeng
 */
public class MainV2 {

    public static final Pattern COMPILE = Pattern.compile("(https?://[^<>\"]+)");

    public static void main(String[] args) throws IOException {
        printDocLinkAndTitle();
    }

    private static void printDocLinkAndTitle() throws IOException {
        Matcher matcher = COMPILE.matcher(readContentFromHtml());
        List<String> links = new ArrayList<>();
        while (matcher.find()) {
            links.add(matcher.group());
        }

        // 过滤需要的文章和标题
        for (String link : links) {
            if (link.contains("mmbiz.qpic.cn") || link.contains("http://www.w3.org")) {
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

}
