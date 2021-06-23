package org.tenx.assignment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestVersionComparison {
    VersionComparison obj = new VersionComparison();

    @Test
    @DisplayName("Test valid strings : First string = 2.0.1, Second string = 3.0.0")
    public void testValidStrings() {
        assertEquals("3.0.0 > 2.0.1", obj.compareVersionStrings("2.0.1", "3.0.0"));
    }

    @Test
    @DisplayName("Test valid strings without patch version : First string = 2.0.1, Second string = 3.0")
    public void testValidStringsWithoutPatchVersions() {
        assertEquals("3.0 > 2.0.1", obj.compareVersionStrings("2.0.1", "3.0"));
    }

    @Test
    @DisplayName("Test valid strings without minor and patch version : First string = 2.0.1, Second string = 3")
    public void testValidStringsWithoutMinorAndPatchVersions() {
        assertEquals("3 > 2.0.1", obj.compareVersionStrings("2.0.1", "3"));
    }

    @Test
    @DisplayName("Test invalid strings with negative version numbers : First string = 2.0.1, Second string = -3.1.1")
    public void testInvalidStringsNegativeMajorVersions() {
        String output = obj.compareVersionStrings("2.0.1", "-3.1.1");
        assertTrue(output.contains("ERROR"));
    }

    @Test
    @DisplayName("Test invalid strings with negative version numbers : First string = 2.0.1, Second string = 3.-1.1")
    public void testInvalidStringsNegativeMinorVersions() {
        String output = obj.compareVersionStrings("2.0.1", "3.-1.1");
        assertTrue(output.contains("ERROR"));
    }

    @Test
    @DisplayName("Test invalid strings with negative version numbers : First string = 2.0.1, Second string = 3.1.-1")
    public void testInvalidStringsNegativePatchVersions() {
        String output = obj.compareVersionStrings("2.0.1", "3.1.-1");
        assertTrue(output.contains("ERROR"));
    }

    @Test
    @DisplayName("Test valid strings with pre-release versions : First string = 2.0.1-alpha, Second string = 3.1.1")
    public void testValidStringsWithPrereleaseVersions() {
        assertEquals("3.1.1 > 2.0.1-alpha", obj.compareVersionStrings("2.0.1-alpha", "3.1.1"));
    }

    @Test
    @DisplayName("Test valid strings with build metadata versions : First string = 2.0.1+Y6FT34, Second string = 3.1.1")
    public void testValidStringsWithBuildMetadataVersions() {
        assertEquals("3.1.1 > 2.0.1+Y6FT34", obj.compareVersionStrings("2.0.1+Y6FT34", "3.1.1"));
    }

    @Test
    @DisplayName("Test valid strings with pre-release and build metadata versions : First string = 2.0.1-alpha+Y6FT34, Second string = 3.1.1")
    public void testValidStringsWithPrereleaseAndBuildMetadataVersions() {
        assertEquals("3.1.1 > 2.0.1-alpha+Y6FT34", obj.compareVersionStrings("2.0.1-alpha+Y6FT34", "3.1.1"));
    }

    @Test
    @DisplayName("Test invalid long strings : First string = 2.0.1.9, Second string = 3.1.1")
    public void testInvalidLongStrings() {
        String output = obj.compareVersionStrings("2.0.1.9", "3.1.1");
        assertTrue(output.contains("ERROR"));
    }
}
