package katas;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Assert;
import org.junit.Test;

public class Kata2Test {

	@Test
	public void testExecute() {
		Assert.assertThat(Kata2.execute().size(), equalTo(2));
	}
}
