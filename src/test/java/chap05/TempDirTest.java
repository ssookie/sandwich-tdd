package chap05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

public class TempDirTest {

    // 어노테이션 위치 1. 필드에 적용
    // 테스트 메서드를 실행하기 전에 임시 폴더를 생성하고 그 폴더 정보를 tempFolder 필드에 할당한다.
    @TempDir
    File tempDirectory;

    @Test
    void test1() {
        System.out.println(tempDirectory.getAbsolutePath());
    }

    // 어노테이션 위치 2. 테스트 메서드 파라미터에 적용
    // 필드에 적용하면 각 테스트 메서드를 실행할 때마다 임시 폴더를 생성한다.
    @Test
    void test2(@TempDir Path tempPath) {
        System.out.println(tempPath);
    }

    @Test
    void test3() {
        System.out.println(tempDirectory.getAbsolutePath());
    }
}
