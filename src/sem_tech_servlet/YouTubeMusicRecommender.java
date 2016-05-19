package sem_tech_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.ResultSet;

import sem_tech_utilities.DBPediaInterface;
import sem_tech_utilities.YouTubeQuery;

/**
 * Servlet implementation class YouTubeMusicRecommender
 */
@WebServlet(value="/process")
public class YouTubeMusicRecommender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YouTubeMusicRecommender() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		String youtube_link = request.getParameter("youtube_link");
		
		YouTubeQuery ytquery = new YouTubeQuery(youtube_link);
		ytquery.extractVideoID(youtube_link);
		String artist = "";
		try {
			artist = ytquery.query();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBPediaInterface dbpedia = new DBPediaInterface(artist);
		ArrayList<String> relatedArtists = dbpedia.dbPediaRoutine();
		request.setAttribute("results",relatedArtists);
		request.setAttribute("artist",artist);
		
		getServletContext().getRequestDispatcher("/results.jsp").forward(request,response);
		
		// Actual logic goes here.
		//PrintWriter out = response.getWriter();
		//out.println("<h1>" + artist  + "</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



