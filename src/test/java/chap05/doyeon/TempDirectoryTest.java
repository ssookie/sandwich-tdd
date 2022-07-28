package chap05.doyeon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

public class TempDirectoryTest {

    // 어노테이션 위치 1. 필드에 적용
    @TempDir
    File tempDirectory; // 테스트마다 생성됨.

    @Test
    void test1() {
        System.out.println(tempDirectory.getAbsolutePath());
    }

    // 어노테이션위치 2. 테스트 메서드 파라미터에 적용

    @Test
    void test2(@TempDir Path tempPath) {
        System.out.println(tempPath);
    }

    @Test
    void test3() {
        System.out.println(tempDirectory.getAbsolutePath());
    }
}
