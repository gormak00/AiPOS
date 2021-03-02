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
            if (request.startsWith("GET")) {
                getRequest(request, buf);
            } else if (request.startsWith("POST")) {
                postRequest(request, buf);
            } else if (request.startsWith("OPTIONS")) {
                optionsRequest();
            }

            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // для вывода лога в файл
    private void log(String response) {
        try (FileOutputStream fos = new FileOutputStream("./log.txt", true)) {

            byte[] buffer = response.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    private void optionsRequest() throws IOException {
        OutputStream os = s.getOutputStream();
        String response = "HTTP/1.1 200 OK\n";

        DateFormat df = DateFormat.getTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        response = response + "Date: " + df.format(new Date()) + "\n";

        response = response
                + "Connection: close\n"
                + "Server: WebServer\n"
                + "Access-Control-Allow-Origin: localhost\n"
                + "Access-Control-Allow-Methods: GET, POST, OPTIONS\n\n\n";

        os.write(response.getBytes());
        log(response);

        s.close();
    }

    private void saveFileFromPost(Socket s, String filename, long contentLength, String fileBytes) throws IOException {
        /*DataInputStream dis = new DataInputStream(s.getInputStream());
        FileOutputStream fos = new FileOutputStream(filename, false);
        byte[] buffer = new byte[64 * 1024];

        int filesize = (int) contentLength; // Send file size in separate msg
        int read = 0;
        int totalRead = 0;
        int remaining = filesize;
        while((read = dis.read(buffer)) > 0) {
            totalRead += read;
            System.out.println("read " + totalRead + " bytes.");
            fos.write(buffer, 0, read);
        }

        fos.close();
        dis.close();*/
        /*InputStream in = s.getInputStream();
        OutputStream out = null;
        try {
            out = new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }
c
        byte[] bytes = new byte[64*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();*/
        /*InputStream in = s.getInputStream();
        OutputStream out = new FileOutputStream(filename);
        byte[] buffer = new byte[64 * 1024];
        //FileInputStream fis = new FileInputStream(path);
        int countOfData = 1;
        while (countOfData > 0) {
            countOfData = in.read(buffer);*//*(int) contentLength; //fis.read(buf);*//*
            if (countOfData > 0) out.write(buffer, 0, countOfData);
        }
        //fis.close();
        out.close();
        in.close();*/

        FileWriter writer = new FileWriter(filename, false);
        writer.write(fileBytes);

    }

    private void postRequest(String request, byte buf[]) throws IOException {
        String filename = /*"./resources/" + */getFilenameFromRequest(request);
        String fileBytes = getFileBytesFromRequest(request);
        long contentLength = getContentLengthFromRequest(request);

        saveFileFromPost(s, filename, contentLength, fileBytes);
        OutputStream os = s.getOutputStream();

        String path = getPath(request);
    }

    private String getFileBytesFromRequest(String request) {
        int indexForStartPart = request.indexOf("filename");
        String startPart = request.substring(indexForStartPart + 10);
        int index = startPart.indexOf("Content-Type:");
        String otherPart = startPart.substring(index);
        int indexStart = otherPart.indexOf("\r") + 4;
        String fileBytes = otherPart.substring(indexStart);
        int indexForTIRE = fileBytes.indexOf("----------------------------");
        fileBytes = fileBytes.substring(0, indexForTIRE);
        return fileBytes;
    }

    private String getFilenameFromRequest(String request) {
        int index = request.indexOf("filename");
        String otherPart = request.substring(index + 10);
        int indexEnd = otherPart.indexOf("\"");
        String filename = otherPart.substring(0, indexEnd);
        return filename;
    }

    private long getContentLengthFromRequest(String request) {
        int index = request.indexOf("Content-Length");
        String otherPart = request.substring(index + 16);
        int indexEnd = otherPart.indexOf("\r");
        long contentLength = Long.parseLong(otherPart.substring(0, indexEnd));
        return contentLength;
    }

    private String createResponse(String codeStatus, String contentType, long fileLastModified, long fileLength) {
        String response = "HTTP/1.1 " + codeStatus + "\n";

        DateFormat df = DateFormat.getTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        response = response + "Date: " + df.format(new Date()) + "\n";

        if (codeStatus.equals("200 OK")) {
            response = response + "Last-Modified: " + df.format(new Date(fileLastModified)) + "\n";

            response = response + "Content-Length: " + fileLength + "\n";
        }

        response = response
                + "Content-Type: " + contentType + "\n"
                + "Connection: close\n"
                + "Server: WebServer\n"
                + "Pragma: no-cache\n\n";
        log(response);
        return response;
    }

    private void getRequest(String request, byte buf[]) throws IOException {

        String codeStatus = null;
        OutputStream os = s.getOutputStream();

        String path = getPath(request);

        if (path == null) {
            codeStatus = "400 Bad Request";
            os.write(createResponse(codeStatus, "null", 0, 0).getBytes());

            s.close();
            return;
        }

        File f = new File(path);
        boolean flag = !f.exists();
        if (!flag) if (f.isDirectory()) {
            codeStatus = "400 Bad Request";
            os.write(createResponse(codeStatus, "null", 0, 0).getBytes());
            s.close();
            return;
        }

        if (flag) {
            codeStatus = "404 Not Found";
            os.write(createResponse(codeStatus, "text/plain", 0, 0).getBytes());

            s.close();
            return;
        }

        // определяем MIME файла по расширению
        // MIME по умолчанию - "text/plain"
        String mime = "text/plain";

        int countOfData = path.lastIndexOf(".");
        if (countOfData > 0) {
            String ext = path.substring(countOfData + 1);
            if (ext.equalsIgnoreCase("html"))
                mime = "text/html";
            else if (ext.equalsIgnoreCase("htm"))
                mime = "text/html";
            else if (ext.equalsIgnoreCase("png"))
                mime = "image/png";
            else if (ext.equalsIgnoreCase("jpg"))
                mime = "image/jpeg";
            else if (ext.equalsIgnoreCase("jpeg"))
                mime = "image/jpeg";
            else if (ext.equalsIgnoreCase("bmp"))
                mime = "image/x-xbitmap";
        }

        codeStatus = "200 OK";
        os.write(createResponse(codeStatus, mime, f.lastModified(), f.length()).getBytes());

        FileInputStream fis = new FileInputStream(path);
        countOfData = 1;
        while (countOfData > 0) {
            countOfData = fis.read(buf);
            if (countOfData > 0) os.write(buf, 0, countOfData);
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
