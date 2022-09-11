package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("sphere")
                .startsWith("S")
                .endsWith("e")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("tetrahedron")
                .startsWith("Te")
                .endsWith("on")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void numberOfVerticesFour() {
        Box box = new Box(4, 10);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isPositive()
                .isEven()
                .isGreaterThan(3)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void numberOfVerticesTwelve() {
        Box box = new Box(8, 20);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isPositive()
                .isEven()
                .isGreaterThan(7)
                .isLessThan(9)
                .isEqualTo(8);
    }

    @Test
    void isExistFigureWithFiveVertex() {
        Box box = new Box(5, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void isExistFigureWithEightVertex() {
        Box box = new Box(8, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void checkDoubleAreaSphereWithEdgeTen() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.637d, withPrecision(0.001d))
                .isCloseTo(1256.637d, withPrecision(0.01d))
                .isCloseTo(1256.637d, Percentage.withPercentage(1.0d))
                .isGreaterThan(1256.636d)
                .isLessThan(1256.638d);
    }

    @Test
    void checkDoubleAreaTetrahedronWithEdgeSix() {
        Box box = new Box(4, 6);
        double result = box.getArea();
        assertThat(result).isEqualTo(62.354d, withPrecision(0.001d))
                .isCloseTo(62.354d, withPrecision(0.01d))
                .isCloseTo(62.354d, Percentage.withPercentage(1.0d))
                .isGreaterThan(62.353d)
                .isLessThan(62.355d);
    }
}