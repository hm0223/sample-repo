package com.hm.junit5.samples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit5TempDirTest.
 *
 * @author huwenfeng
 */
class Junit5TempDirTest {

    @Test
    void writeItemsToFile(@TempDir Path tempDir) throws IOException {
        // 创建一个临时文件
        Path tempFile = tempDir.resolve("temp.txt");

        // 将字符串写入临时文件
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            writer.write("Hello, Junit5!");
        }

        // 读取临时文件的内容并验证其内容是否正确
        String content = new String(Files.readAllBytes(tempFile));
        assertEquals("Hello, Junit5!", content);
    }
}
