import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import javax.swing.*;

public class MiniHTTP {

	static String sharedResponse;

	public static void main(String[] args) {
		createGUI();
	}

	public static void startSERVER() {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000),
					0);
			server.createContext("/results", new DisplayHandler());
			server.createContext("/sendresults", new PostHandler());
			server.setExecutor(null);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createGUI() {
		JFrame frame = new JFrame("HTTP POST SERVER");
		JPanel panel = new JPanel(new FlowLayout());

		final JButton b1 = new JButton("START SERVER");
		final JButton b2 = new JButton("TERMINATE SERVER & EXIT");

		b1.setBackground(Color.GREEN);
		b2.setBackground(Color.RED);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSERVER();
				b1.setEnabled(false);
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panel.add(b1);
		panel.add(b2);
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(400, 80);
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 80);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	static class DisplayHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			String response;
			response = sharedResponse;
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	static class PostHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			sharedResponse = "";
			InputStream inputStr = t.getRequestBody();
			OutputStream os = t.getResponseBody();
			StringBuilder sb = new StringBuilder();
			int nextChar = inputStr.read();
			while (nextChar > -1) {
				sb = sb.append((char) nextChar);
				nextChar = inputStr.read();
			}
			sharedResponse = sharedResponse + sb.toString();
			String postResponse = "ROGER XML RECEIVED";
			t.sendResponseHeaders(300, postResponse.length());
			os.write(postResponse.getBytes());
			os.close();
		}
	}
}
