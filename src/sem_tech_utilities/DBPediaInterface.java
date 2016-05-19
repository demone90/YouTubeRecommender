package sem_tech_utilities;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;


/**
 * Created by Beppe on 27/10/2015.
 */

public class DBPediaInterface {

    public String videoArtist = null;

    public DBPediaInterface(String videoArtist) {
        this.videoArtist = videoArtist;
    }

    public ArrayList<String> dbPediaRoutine() {

        String realArtist = "'" + this.videoArtist + "'";

        /*Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(realArtist);
        boolean found = matcher.find();
        if(found) { System.out.println("whitespace found");} else { System.out.println("whitespace not found"); }*/

         String queryString=
        "PREFIX dbp: <http://dbpedia.org/property/> "+
        "PREFIX dbo: <http://dbpedia.org/ontology/> "+
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
        "SELECT DISTINCT ?similarArtist "+
        "WHERE {"+
            "{ ?person dbp:name "+realArtist+"@en "+ ".} "+
            "UNION { ?person rdfs:label " +realArtist+"@en } ." +
            " { ?person dbo:associatedMusicalArtist ?similarArtist .}"+
            "UNION { ?similarArtist dbo:associatedMusicalArtist ?person}." +
        "}";
        System.out.println(queryString);
        // now creating query object

        Query query = QueryFactory.create(queryString);

        System.out.println("Querying DBpedia...");
        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
        System.out.println("sei qui almeno");
        ResultSet results = null;
        ArrayList<String> relatedArtists = new ArrayList<String>();	
        
        
        try {
            results = qexec.execSelect();
            while(results.hasNext()) { 
            	QuerySolution binding = results.nextSolution();
            	Resource subj = (Resource) binding.get("similarArtist");
            	System.out.println(subj.toString());
            	relatedArtists.add(subj.toString());
            }
            //ResultSetFormatter.out(System.out, results, query);
            /*for (; results.hasNext();) {

                // Result processing is done here.
            }*/
        }catch(Exception e) { 
        	e.printStackTrace();
        }
        finally {
            qexec.close();
        }
        return relatedArtists;
    }

}
