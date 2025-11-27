package com.walterjwhite.picovoice;

import java.io.OutputStream;

public interface OnKeywordHandler {
    void onKeyword(final String keyword, final OutputStream outputStream);
}
