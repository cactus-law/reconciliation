package com.froad.comon.util;

import com.froad.comon.util.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketUtil {
    private static final Logger logger = Logger.getLogger(SocketUtil.class);

    public String sendMessage(String host, int port, String message) throws Exception {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        String returnMessage = "";
        try {
            socket = new Socket(host, port);
            os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os);
            out.println(message);
            out.flush();
            is = socket.getInputStream();
            byte[] bytes = new byte[4096];
            is.read(bytes);
            String str = new String(bytes);
            returnMessage = str.substring(0, str.trim().length());
        } catch (Exception e) {
            logger.error("报文发送异常：" + "host:[" + host + "] port:[" + port + "] message:[" + message + "]  " + e);
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return returnMessage;
    }
}
