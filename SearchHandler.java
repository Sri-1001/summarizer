package com.search.summary;

//package com.search.summary.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class SearchHandler
 */
public class SearchHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Pattern patternDomainName;
	private Matcher matcher;
	private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	static {
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
	}

	/**
	 * Default constructor.
	 */
	public SearchHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.print("hello");
		int i = 0, count =0;
		// QueryWord obj = new QueryWord();
		String query = request.getParameter("query");
		Set<String> result = getDataFromGoogle(query);
		String summ=null;
		for (String temp : result) {
//			System.out.println(temp);
			try
			{
				readWebsite(temp);
			}
			catch(Exception e) {
				System.out.println(e);
			}
			Extract(i);
//			WebSiteReader(temp);
//			// if(i!=3)
			count++;
			if (count == 10)
				break;
		}
		
		System.out.println(result.size());
		try {
			summ=Summary();
			System.out.println("@"+summ);
		} catch (Exception e) {
			System.out.println(e);
		}
//		request.getSession().setAttribute("summary",summ);
//		request.getRequestDispatcher("results.jsp").forward(request, response);
		response.setContentType( "text/html" );
	    response.sendRedirect( "results.jsp?summary="+summ );

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public String getDomainName(String url) {

		String domainName = "";
		matcher = patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;

	}

	private Set<String> getDataFromGoogle(String query) {

		Set<String> result = new HashSet<String>();
		String request = "https://www.google.com/search?q=" + query + "&num=20";
		System.out.println("Sending request..." + request);

		try {

			// need http protocol, set this as a Google bot agent :)
			Document doc = Jsoup.connect(request)
					.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").timeout(25000)
					.get();

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				String temp = link.attr("href");
				//System.out.println(temp);
				if (temp.startsWith("/url?q=")) {
					// use regex to get domain name
					if (!temp.contains("https://www.youtube.com")) {// || !temp.contains("https://support.google.com")
																	// || !temp.contains("https://accounts.google.com"))
																	// {
						// if(temp.contains(query)) {
						if (!temp.startsWith("https://policies.google.com")) {
							result.add(temp.substring(7, temp.indexOf('&')));
							//System.out.println("#" + temp.substring(7, temp.indexOf('&')));
						}
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
public void readWebsite(String url) throws Exception {
	FileWriter fileWriter = new FileWriter("../Sam.txt"); //Set true for append mode
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.println(get(url));  //New line
    printWriter.close();
}
public String get(String url) throws Exception {
	   StringBuilder sb = new StringBuilder();
//	   FileWriter fw = new FileWriter("Content.txt", true);
//	   PrintWriter pw = new PrintWriter(in);
	   for(Scanner sc = new Scanner(new URL(url).openStream()); sc.hasNext(); ) {
		   String text=sc.nextLine();
		  // if(text.contains("<p>")) {
//			   fw.write(text);
			  // String s1 =text.substring(2,text.indexOf("</p>"));
			   sb.append(text).append('\n');
		   //}
			   
	   }
//	      fw.close();
	   return sb.toString();
	}
	public void Extract(int i) {

		FileInputStream in;
		FileOutputStream out;
		File f;
		i=0;
		try {
			String filename;
			FileWriter fileWriter = new FileWriter("../file1.txt", true); //Set true for append mode
		    PrintWriter printWriter = new PrintWriter(fileWriter);
//		    printWriter.println(get(url));  //New line
//		    printWriter.close();
//			filename = "../file" + i + ".txt";// JOptionPane.showInputDialog("Enter the file name");
			in = new FileInputStream("../Sam.txt");
//			f = new File("./", filename);
//			out = new FileOutputStream(f);
//			PrintWriter pw = new PrintWriter(out, true);
			int b;
			String s = "";
			String text = "";
			char ch;
			while ((b = in.read()) != -1) {
				//System.out.println(b);
				ch = (char) b;
				if (ch == '<') {
					s = "";
					s = s + ch;
					while ((ch = (char) in.read()) != '>')
						s = s + ch;
					s = s + ch;
					if (s.equals("<p>")) {
						text = "";

						while ((ch = (char) in.read()) != '<') {
							text = text + ch;

						}
						if (text != "") {
							//pw.println(text);
							System.out.println(text);
							printWriter.println(text);
							i++;
							System.out.println("#"+i);
						}
						if( i >= 10)
							break;
					}
				}
				if( i >= 10)
					break;
				
			}
			in.close();
			printWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	public void WebSiteReader(String ul) {
//
//		String nextLine;
//		URL url = null;
//		URLConnection urlConn = null;
//		InputStreamReader inStream = null;
//		BufferedReader buff = null;
//		FileOutputStream in;
//		try {
//			// Create the URL obect that points
//			// at the default file index.html
//			in = new FileOutputStream("Content.txt", true);
//			PrintWriter pw = new PrintWriter(in);
//			// String ul=JOptionPane.showInputDialog("Enter the URL");
//			url = new URL(ul);
//			urlConn = url.openConnection();
//			inStream = new InputStreamReader(urlConn.getInputStream());
//			buff = new BufferedReader(inStream);
//
//			// Read and print the lines
//			while (true) {
//				nextLine = buff.readLine();
//				if (nextLine != null) {
//					// System.out.println(nextLine);
//					pw.println(nextLine);
//				}
//
//				else {
//					break;
//				}
//			}
//			in.close();
//		} catch (MalformedURLException e) {
//			System.out.println("Please check the URL:" + e.toString());
//		} catch (IOException e1) {
//			System.out.println("Can't read  from the Internet: " + e1.toString());
//		}
//	}

	public String Summary() throws IOException {
		
		String s1="";
		try {
			int i=0;
			
			System.out.println("Summary");
	        FileInputStream fstream = new FileInputStream("../file1.txt");
	        BufferedReader infile = new BufferedReader(new InputStreamReader(
	                fstream));
	        String data = new String();
	        while ((data = infile.readLine()) != null) { // use if for reading just 1 line
	            if( data.length() > 200 ) {
	        	System.out.println(data);
	        	s1=s1+data+"\n";
	            i++;
	            }
	            if(i== 5)
	            	break;
	        }
	        //Files.deleteIfExists(Paths.get("../file1.txt"));
	        clearFile();
	    } catch (IOException e) {
	        // Error
	    }
		return s1;
		// File []list=new File[10];
//		FileOutputStream sum;
// 	    FileInputStream in = null;
//		PrintWriter pw;
//		String nextline = "";
//
//		int b;
//		char ch;
//
//		String dir = "../";
//		File f1 = new File(dir);
//		sum = new FileOutputStream("sum.txt");
//		pw = new PrintWriter(sum);
//
//		if (f1.isDirectory()) {
//
//			String list[] = f1.list();
//			for (int i = 1; i < list.length; i++) {
//				System.out.println("\n" + list[i]);
//				in = new FileInputStream("../" + list[i]);
//				while ((ch = (char) in.read()) != '.') {
//					System.out.println("\n" + ch);
//					nextline = nextline + ch;
//					sum.write(ch);
//				}
//				// pw.println(nextline);
//			}
//			sum.close();
//			in.close();
//			pw.close();
//		}
	}
	public static void clearFile()

	{ 

	    try{

	    FileWriter fw = new FileWriter("../file1.txt", false);

	    PrintWriter pw = new PrintWriter(fw, false);

	    pw.flush();

	    pw.close();

	    fw.close();

	    }catch(Exception exception){

	        System.out.println("Exception have been caught");

	    }

	}

}
