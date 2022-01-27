package ru.stqa.trening.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("SvetlanaCheslavleva", "java_training")).commits();
    for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
