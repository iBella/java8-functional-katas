package katas;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import model.MovieList;
import util.DataKataUtil;
import util.DataUtil;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
	public static List<Map> execute() {
		List<MovieList> movieLists = DataUtil.getMovieLists();
		return movieLists.stream().flatMap(list -> list.getVideos().stream()).map(movie -> ImmutableMap.of("id", movie.getId(), "title", movie.getTitle(), "boxart", DataKataUtil.smallestBoxArt(movie))).collect(Collectors.toList());

	}
}
