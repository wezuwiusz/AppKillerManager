package com.thelittlefireman.appkillermanager.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Encoding {
    /**
     * Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the
     * Unicode character set
     */
    public static final Charset US_ASCII = StandardCharsets.US_ASCII;
    /**
     * ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
     */
    public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
    /**
     * Eight-bit UCS Transformation Format
     */
    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    /**
     * Sixteen-bit UCS Transformation Format, big-endian byte order
     */
    public static final Charset UTF_16BE = StandardCharsets.UTF_16BE;
    /**
     * Sixteen-bit UCS Transformation Format, little-endian byte order
     */
    public static final Charset UTF_16LE = StandardCharsets.UTF_16LE;
    /**
     * Sixteen-bit UCS Transformation Format, byte order identified by an
     * optional byte-order mark
     */
    public static final Charset UTF_16 = StandardCharsets.UTF_16;
}
