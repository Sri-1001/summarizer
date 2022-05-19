package com.search.summary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QueryWord {

  private static Pattern patternDomainName;
  private Matcher matcher;
  private static final String DOMAIN_NAME_PATTERN 
	= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
  static {
	patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
  }
	
  public static void main(String[] args) {
	  int i=0;
	QueryWord obj = new QueryWord();
	String query = JOptionPane .showInputDialog("Enter the keyword to search");
	Set<String> result = obj.getDataFromGoogle(query);
	for(String temp : result){
		System.out.println(temp);
		obj.WebSiteReader(temp);
		obj.Extract();
		if(++i ==3 )
			break;
	}
	System.out.println(result.size());
  }

  public String getDomainName(String url){
		
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
		Document doc = Jsoup
			.connect(request)
			.userAgent(
			  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
			.timeout(25000).get();

		// get all links
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			String temp = link.attr("href");	
			System.out.println(temp);
			if(temp.startsWith("/url?q=")){
                                //use regex to get domain name
				result.add(temp.substring(7, temp.indexOf('&')));
			}
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result;
  }
  public void Extract() {
	 	       
	FileInputStream in;
	FileOutputStream out;
	File f;
	try{
	          String filename;
	filename="file1.txt";//JOptionPane.showInputDialog("Enter the file name");
		in=new FileInputStream("Content.txt");
	f=new File("./",filename);
		out=new FileOutputStream(f);
		PrintWriter pw=new PrintWriter(out,true);
		int b;
		String s="";
	String text="";
		char ch;
	while ((b=in.read())!=-1)
		{
	            
	            ch=(char)b;
	       if(ch=='<')
		    {
					s="";
	               s=s+ch;
			 while((ch=(char)in.read())!='>')
					s=s+ch;
		s=s+ch;
	if(s.equals("<p>")||s.equals("<h1>")||s.equals("<h2>")||s.equals("<h3>")||s.equals("<h4>")||s.equals("<h5>")||s.equals("<h6>")||s.equals("<li>"))
	{
	text="";


	while((ch=(char)in.read())!='<')
		{
	text=text+ch;

		}
		if(text!="")
	pw.println(text);
	  
	}
			}
		}
		in.close();
		out.close();
	     } catch(Exception e){
	       System.out.println(e);
	     } 
	 }
  public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
	    double dotProduct = 0.0;
	    double normA = 0.0;
	    double normB = 0.0;
	    for (int i = 0; i < vectorA.length; i++) {
	        dotProduct += vectorA[i] * vectorB[i];
	        normA += Math.pow(vectorA[i], 2);
	        normB += Math.pow(vectorB[i], 2);
	    }   
	    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
//  public List<String> updatePlayerRankings(final List<Team> teams_) {
//      final List<Team> teams = sortedByScoreAndAbility(teams_);
//      calculateRanks(teams);
//      List<String> l1;
//      final double gamma = 1.0 / teams.size();
//      
//      final List<Player> updatedPlayers = new ArrayList<Player>();
//      for (final Team team : teams) {
//          double Omega = 0;
//          double Delta = 0;
//          final Ability teamAbility = team.ability();
//          
//          for (final Team opponent : teams) {
//              if (opponent == team) continue;
//              final Ability opAbility = opponent.ability();   
//              
//              final double c = Math.sqrt(teamAbility.variance() + opAbility.variance() + 2 * betaSquared);
//              final double p = 1 / (1 + Math.exp((opAbility.mean() - teamAbility.mean()) / c));
//              final double varianceToC = teamAbility.variance() / c;
//              
//              final double s = s(team, opponent);
//              
//              Omega += varianceToC * (s - p);
//              Delta += gamma * varianceToC / c * p * (1 - p);
//          }
//          
//          updatedPlayers.addAll(resultingAbilities(team, Delta, Omega));
//      }
//      
//      return l1;
//  }
  

    public void WebSiteReader(String ul) {
    
         String nextLine;
         URL url = null;
         URLConnection urlConn = null;
         InputStreamReader  inStream = null;
         BufferedReader buff = null;
  FileOutputStream in;
         try{
            // Create the URL obect that points
            // at the default file index.html
  in=new FileOutputStream("Content.txt");
  PrintWriter pw=new PrintWriter(in);
  //String ul=JOptionPane.showInputDialog("Enter the URL");
            url  = new URL(ul);
            urlConn = url.openConnection();
           inStream = new InputStreamReader( 
                             urlConn.getInputStream());
             buff= new BufferedReader(inStream);
          
         // Read and print the lines from index.html
          while (true){
              nextLine =buff.readLine();  
              if (nextLine !=null){
                  System.out.println(nextLine); 
  		pw.println(nextLine);
              }
  	
              else{
                 break;
              } 
          }
  in.close();
       } catch(MalformedURLException e){
         System.out.println("Please check the URL:" + 
                                             e.toString() );
       } catch(IOException  e1){
        System.out.println("Can't read  from the Internet: "+ 
                                            e1.toString() ); 
    }
   }
  
    
    public void Summary() throws IOException
    {
   
    //File []list=new File[10];
    FileOutputStream sum;
    FileInputStream in=null;
    PrintWriter pw;
    String nextline="";

    int b;
    char ch;

    String dir="../";
    File f1=new File(dir);
    sum=new FileOutputStream("sum.txt");
    pw=new PrintWriter(sum);

    if(f1.isDirectory())
    {

    String list[]=f1.list();
    for(int i=1;i<list.length;i++)
    {
    	
    System.out.println("\n"+list[i]);
    in=new FileInputStream("../"+list[i]);
    while((ch=(char)in.read())!='.')
    {
    System.out.println("\n"+ch);
    if(ch!='&'&& ch!='#' && ch!='@' && ch!='*' && ch!='$'&& ch!='%')
    {
    nextline=nextline+ch;
    sum.write(ch);
    }
    }
    //pw.println(nextline);
    }
    sum.close();
    in.close();
    pw.close();
    }
    }
   

}
