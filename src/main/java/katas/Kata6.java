package katas;

import java.util.List;

import model.BoxArt;
import model.Movie;
import util.DataKataUtil;
import util.DataUtil;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {

	public static String execute() {
		List<Movie> movies = DataUtil.getMovies();
		return movies.stream().flatMap(movie -> movie.getBoxarts().stream()).reduce(DataKataUtil::largestBoxArt).map(BoxArt::getUrl).orElse("");
	}
}
