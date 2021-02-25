package com.company;

import java.io.File;
import java.io.InputStream;
import java.net.Socket;

public class WebServer extends Thread {
    private Socket s;

    public WebServer(Socket s) {
        this.s = s;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run() {
        try {
            InputStream is = s.getInputStream();

            // буффер данных в 64 килобайта
            byte buf[] = new byte[64 * 1024];
            // читаем 64кб от клиента, результат - кол-во реально принятых данных
            int r = is.read(buf);

            // создаём строку, содержащую полученую от клиента информацию
            String request = new String(buf, 0, r + 1);

            // получаем путь до документа (см. ниже ф-ю "getPath")
            String path = getPath(request);

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPath(String header) {
        // ищем URI, указанный в HTTP запросе
        // URI ищется только для методов POST и GET, иначе возвращается null
        String URI = extract(header, "GET ", " "), path;
        if (URI == null) URI = extract(header, "POST ", " ");
        if (URI == null) return null;

        // если URI записан вместе с именем протокола
        // то удаляем протокол и имя хоста
        path = URI.toLowerCase();
        if (path.indexOf("http://", 0) == 0) {
            URI = URI.substring(7);
            URI = URI.substring(URI.indexOf("/", 0));
        } else if (path.indexOf("/", 0) == 0)
            URI = URI.substring(1); // если URI начинается с символа /, удаляем его

        // отсекаем из URI часть запроса, идущего после символов ? и #
        int i = URI.indexOf("?");
        if (i > 0) URI = URI.substring(0, i);
        i = URI.indexOf("#");
        if (i > 0) URI = URI.substring(0, i);

        // конвертируем URI в путь до документов
        // предполагается, что документы лежат там же, где и сервер
        // иначе ниже нужно переопределить path
        path = "./src/resources" + File.separator;
        char a;
        for (i = 0; i < URI.length(); i++) {
            a = URI.charAt(i);
            if (a == '/')
                path = path + File.separator;
            else
                path = path + a;
        }
        return path;
    }

    private String extract(String str, String start, String end) {
        int s = str.indexOf("\n\n", 0), e;
        if (s < 0) s = str.indexOf("\r\n\r\n", 0);
        if (s > 0) str = str.substring(0, s);
        s = str.indexOf(start, 0) + start.length();
        if (s < start.length()) return null;
        e = str.indexOf(end, s);
        if (e < 0) e = str.length();
        return (str.substring(s, e)).trim();
    }

}