package katas;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import model.MovieList;
import util.DataKataUtil;
import util.DataUtil;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
	public static List<Map> execute() {
		List<MovieList> movieLists = DataUtil.getMovieLists();

		return movieLists.stream().flatMap(movies -> movies.getVideos().stream()).map(movie -> ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "time", DataKataUtil.middleMoment(movie), "url", DataKataUtil.smallestBoxArt(movie))).collect(Collectors.toList());
	}
}
