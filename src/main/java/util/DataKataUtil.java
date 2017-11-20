//**********************************************************************
// Copyright (c) 2017 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import model.BoxArt;
import model.InterestingMoment;
import model.Movie;

public class DataKataUtil {

	public static BoxArt largestBoxArt(BoxArt boxArt1, BoxArt boxArt2) {
		return Math.multiplyExact(boxArt1.getHeight(), boxArt1.getWidth()) > Math.multiplyExact(boxArt2.getHeight(), boxArt2.getWidth()) ? boxArt1 : boxArt2;
	}

	public static String smallestBoxArt(Movie movie) {
		return movie.getBoxarts().stream().reduce((boxArt1, boxArt2) -> Math.multiplyExact(boxArt1.getHeight(), boxArt1.getWidth()) < Math.multiplyExact(boxArt2.getHeight(), boxArt2.getWidth()) ? boxArt1 : boxArt2).map(BoxArt::getUrl).orElse("");
	}

	public static Date middleMoment(Movie movie) {
		return movie.getInterestingMoments().stream().filter(interesting -> interesting.getType().equals("Middle")).map(InterestingMoment::getTime).findFirst().orElse(new Date());
	}

	public static List<Map> videosListIdTitle(Object id, List<Map> movies) {
		return movies.stream().filter(movie -> movie.get("listId").equals(id)).map(video -> ImmutableMap.of("id", video.get("id"), "title", video.get("title"))).collect(Collectors.toList());
	}

	public static List<Map> videosListIdTitleTimeBoxart(Object id, List<Map> movies, List<Map> boxArts, List<Map> bookmarkList) {
		return movies.stream().filter(movie -> movie.get("listId").equals(id)).map(video -> ImmutableMap.of("id", video.get("id"), "title", video.get("title"), "boxart", getSmallestUrlBoxArtList(id, boxArts), "time", getTimeBookmarkList(id, bookmarkList))).collect(Collectors.toList());
	}

	public static Object getSmallestUrlBoxArtList(Object id, List<Map> boxArts) {
		return boxArts.stream().filter(boxArt -> boxArt.get("videoId").equals(id)).reduce((boxArt1, boxArt2) -> Math.multiplyExact((int) boxArt1.get("width"), (int) boxArt1.get("height")) < Math.multiplyExact((int) boxArt2.get("width"), (int) boxArt2.get("height")) ? boxArt1
				: boxArt2).map(boxart -> boxart.get("url")).orElse("");
	}

	public static Object getTimeBookmarkList(Object id, List<Map> bookmarkList) {
		return bookmarkList.stream().filter(bookmark -> bookmark.get("videoId").equals(id)).map(bookmark -> bookmark.get("time"));
	}
}
