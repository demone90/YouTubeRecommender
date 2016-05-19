package sem_tech_servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;

/**
 * Servlet implementation class YouTubeMusicRecommenderSecond
 */
@WebServlet("/music_recommendation")
public class YouTubeMusicRecommenderSecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YouTubeMusicRecommenderSecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String music_of = request.getParameter("music_of");
		
		System.out.println(music_of);
		
		ArrayList<String> tracks = recommendMusic(music_of);
		request.setAttribute("tracks",tracks);
		
		getServletContext().getRequestDispatcher("/recommended.jsp").forward(request,response);
		
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public ArrayList<String> recommendMusic(String music_of) {
		
		//first query returning some 
		/*SELECT ?tracks 
				where { 
				 ?tracks dbo:musicalArtist <http://dbpedia.org/resource/Eminem> .
				}*/
		 String queryString=
			        "PREFIX dbp: <http://dbpedia.org/property/> "+
			        "PREFIX dbo: <http://dbpedia.org/ontology/> "+
			        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
			        "SELECT DISTINCT ?tracks "+
			        "WHERE {"+
			            "{ ?tracks dbo:musicalArtist <"+ music_of + "> .} "+
			            
			        "}";
		 System.out.println(queryString);
	        // now creating query object

	     Query query = QueryFactory.create(queryString);

	     System.out.println("Querying DBpedia...");
	     QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
	     System.out.println("sei qui almeno");
	     ResultSet results = null;
	     ArrayList<String> music = new ArrayList<String>();	
	     
	        try {
	            results = qexec.execSelect();
	            while(results.hasNext()) { 
	            	QuerySolution binding = results.nextSolution();
	            	Resource subj = (Resource) binding.get("tracks");
	            	System.out.println(subj.toString());
	            	music.add(subj.toString());
	            }
	            //ResultSetFormatter.out(System.out, results, query);
	            for (; results.hasNext();) {

	                // Result processing is done here.
	            }
	        }catch(Exception e) { 
	        	e.printStackTrace();
	        }
	        finally {
	            qexec.close();
	        }
	        return music;
	}
	
}
