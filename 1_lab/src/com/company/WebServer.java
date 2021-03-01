package com.company;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

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
            int countOfData = is.read(buf);

            // создаём строку, содержащую полученую от клиента информацию
            String request = new String(buf, 0, countOfData + 1);
            if(request.startsWith("GET")){
                getRequest(request, countOfData, buf);
            } else if(request.startsWith("POST")){
                postRequest(request, countOfData, buf);
            } else if(request.startsWith("OPTIONS")){

            }

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postRequest(String request, int countOfData, byte buf[]) throws IOException {
        OutputStream os = s.getOutputStream();

        // получаем путь до документа (см. ниже ф-ю "getPath")
        String path = getPath(request);

        String filename = getFilenameFromRequest(request);

        // если файл существует и является директорией,
        // то ищем файл HelloDY.html
        File f = new File(path);
        boolean flag = !f.exists();
        if(!flag) if(f.isDirectory())
        {
            if(path.lastIndexOf(""+File.separator) == path.length()-1)
                path = path + "HelloDY.html";
            else
                path = path + File.separator + "HelloDY.html";
            f = new File(path);
            flag = !f.exists();
        }
    }

    private String getFileBytesFromRequest(String request){
        int index = request.indexOf("Content-Type:");
        String otherPart = request.substring(index + 10);
        int indexEnd = otherPart.indexOf("\"");
        String filename = otherPart.substring(0, indexEnd);
        return filename;
    }

    private String getFilenameFromRequest(String request){
        int index = request.indexOf("filename");
        String otherPart = request.substring(index + 10);
        int indexEnd = otherPart.indexOf("\"");
        String filename = otherPart.substring(0, indexEnd);
        return filename;
    }

    private void getRequest(String request, int countOfData, byte buf[]) throws IOException {
        OutputStream os = s.getOutputStream();

        // получаем путь до документа (см. ниже ф-ю "getPath")
        String path = getPath(request);

        // если из запроса не удалось выделить путь, то
        // возвращаем "400 Bad Request"
        if(path == null)
        {
            // первая строка ответа
            String response = "HTTP/1.1 400 Bad Request\n";

            // дата в GMT
            DateFormat df = DateFormat.getTimeInstance();
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            response = response + "Date: " + df.format(new Date()) + "\n";

            // остальные заголовки
            response = response
                    + "Connection: close\n"
                    + "Server: WebServer\n"
                    + "Pragma: no-cache\n\n";

            // выводим данные:
            os.write(response.getBytes());

            s.close();

            return;
        }

        // если файл существует и является директорией,
        // то ищем файл HelloDY.html
        File f = new File(path);
        boolean flag = !f.exists();
        if(!flag) if(f.isDirectory())
        {
            if(path.lastIndexOf(""+File.separator) == path.length()-1)
                path = path + "HelloDY.html";
            else
                path = path + File.separator + "HelloDY.html";
            f = new File(path);
            flag = !f.exists();
        }

        // если по указанному пути файл не найден
        // то выводим ошибку "404 Not Found"
        if(flag)
        {
            // первая строка ответа
            String response = "HTTP/1.1 404 Not Found\n";

            // дата в GMT
            DateFormat df = DateFormat.getTimeInstance();
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            response = response + "Date: " + df.format(new Date()) + "\n";

            // остальные заголовки
            response = response
                    + "Content-Type: text/plain\n"
                    + "Connection: close\n"
                    + "Server: WebServer\n"
                    + "Pragma: no-cache\n\n";

            // и гневное сообщение
            response = response + "File " + path + " not found!";

            // выводим данные:
            os.write(response.getBytes());

            s.close();

            return;
        }

        // определяем MIME файла по расширению
        // MIME по умолчанию - "text/plain"
        String mime = "text/plain";

        // выделяем у файла расширение (по точке)
        countOfData = path.lastIndexOf(".");
        if(countOfData > 0)
        {
            String ext = path.substring(countOfData+1);
            if(ext.equalsIgnoreCase("html"))
                mime = "text/html";
            else if(ext.equalsIgnoreCase("htm"))
                mime = "text/html";
            else if(ext.equalsIgnoreCase("png"))
                mime = "image/png";
            else if(ext.equalsIgnoreCase("jpg"))
                mime = "image/jpeg";
            else if(ext.equalsIgnoreCase("jpeg"))
                mime = "image/jpeg";
            else if(ext.equalsIgnoreCase("bmp"))
                mime = "image/x-xbitmap";
        }

        // создаём ответ

        // первая строка ответа
        String response = "HTTP/1.1 200 OK\n";

        // дата создания в GMT
        DateFormat df = DateFormat.getTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("GMT"));

        // время последней модификации файла в GMT
        response = response + "Last-Modified: " + df.format(new Date(f.lastModified())) + "\n";

        // длина файла
        response = response + "Content-Length: " + f.length() + "\n";

        // строка с MIME кодировкой
        response = response + "Content-Type: " + mime + "\n";

        // остальные заголовки
        response = response
                + "Connection: close\n"
                + "Server: WebServer\n\n";

        // выводим заголовок:
        os.write(response.getBytes());
        System.out.println(response);

        // и сам файл:
        FileInputStream fis = new FileInputStream(path);
        countOfData = 1;
        while(countOfData > 0)
        {
            countOfData = fis.read(buf);
            if(countOfData > 0) os.write(buf, 0, countOfData);
        }
        fis.close();
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
