package com.hm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public static final String INNER_DOMAIN = "mmbiz.qpic.cn";
    public static final String INNER_STYLE_TAG = "http://www.w3.org";

    private static final Logger LOGGER = LoggerFactory.getLogger(MainV2.class);

    public static final Pattern URL_PATTERN = Pattern.compile("((http|https)://)?(www\\.)?"
            + "[a-zA-Z0-9@:%._+~#?&/=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._+~#?&/=]*)");

    public static void main(String[] args) throws IOException {
        fetchDocLinkAndTitle();
    }

    private static void fetchDocLinkAndTitle() throws IOException {
        Matcher matcher = URL_PATTERN.matcher(readContentFromHtml());
        List<String> links = new ArrayList<>();
        while (matcher.find()) {
            links.add(matcher.group());
        }

        // 过滤需要的文章和标题
        for (String link : links) {
            if (link.contains(INNER_DOMAIN) || link.contains(INNER_STYLE_TAG)) {
                LOGGER.info("Ignore Link={}", link);
                continue;
            }
            Document document = Jsoup.connect(link)
                    .ignoreHttpErrors(true)
                    .timeout(2000)
                    // 方便抓取https内容
                    .sslSocketFactory(SSLHelper.socketFactory())
                    .get();
            String title = document.title();
            LOGGER.info("Fetch Link={}; Title={}", link, title);
        }
    }
}
