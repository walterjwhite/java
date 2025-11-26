package com.walterjwhite.citrix;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.htmlunit.html.HtmlPage;

@Slf4j
public class MouseWiggleExtension extends AbstractCitrixExtension {
    private static final String MOUSE_JAVASCRIPT = """
        var event = new MouseEvent('mousemove', {
        clientX: %d, clientY: %d, bubbles: true });
        document.dispatchEvent(event);
    """;

    public MouseWiggleExtension(CitrixSession citrixSession, int iterationDelay, int iterationCount) {
        super(citrixSession, iterationDelay, iterationCount);
    }

    @SneakyThrows
    @Override
    public void doRun() {

        moveMouse(citrixSession, 100, 100);
        Thread.sleep(500);

        moveMouse(citrixSession, 200, 100);
        Thread.sleep(500);

        moveMouse(citrixSession, 200, 200);
        Thread.sleep(500);

        moveMouse(citrixSession, 100, 200);
    }

    private void moveMouse(final CitrixSession citrixSession, final int x, final int y) {
        LOGGER.info("moving mouse: {}, {}", x, y);
        final String script = String.format(MOUSE_JAVASCRIPT, x, y);
        ((HtmlPage)data).executeJavaScript(script);
    }
}
